package com.kavki.fastfxrechargeapis.Service.ClientServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.ClientListRepo;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.RetailerListRepo;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.TransactionRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.ClientLoginRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.PaymentSummeryRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.LoadMoneyRepo;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.DTO.OnboardClientProcedure;
import com.kavki.fastfxrechargeapis.Entity.Admin.*;
import com.kavki.fastfxrechargeapis.Entity.Client.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPortalServices  {
    
  
    @Autowired
    private ClientLoginRepo loginRepo;
    @Autowired
    private OnboardClientProcedure clientProcedure;
    @Autowired
    private TransactionRepo tListRepo;
    @Autowired
    private LoadMoneyRepo loadMoneyRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ClientListRepo cListRepo;
    @Autowired
    private PaymentSummeryRepo summeryRepo;
    @Autowired
    private RetailerListRepo rListRepo;
    

    public LoginStatus verifyPassword(ClientCredentials creds) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        String Email = creds.getEmail();
        LoginStatus loginStatus = new LoginStatus();
        ClientCredentials credit = loginRepo.findByEmail(Email);
        if(credit==null){
            loginStatus.setLoginStatus("Error");
            return loginStatus;
        }
        
        String Password = creds.getPassword();
        String encryptPass = credit.getPassword();
        String salt = credit.getSalt();
        Encryptor encrypt = new Encryptor();
        String newPass = encrypt.verify(Password, salt);

        if(newPass.equalsIgnoreCase(encryptPass)){
            String clientId = credit.getClientId();           
            loginStatus.setUserId(clientId);
            loginStatus.setLoginStatus("Successfull");            
            return loginStatus;
        }else{
            loginStatus.setLoginStatus("Error");
            return loginStatus;
        }
    }

    public OnboardStatus createNewClient(OnboardClient details) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        String Email = details.getEmail();
        
        OnboardStatus status = new OnboardStatus();
        ClientCredentials creds = loginRepo.findByEmail(Email);
        if(creds!=null && Email.equalsIgnoreCase(creds.getEmail())){
            status.setOnboardStatus("Client Already Exists!");
            return status;
        }

        String password = details.getPassword();
        Encryptor encrypt = new Encryptor();
        String[] clientCredentials = new String[2];
        clientCredentials = encrypt.encrypt(password);

        details.setPassword(clientCredentials[0]); // index 0 -> encrypted pass 
        details.setSalt(clientCredentials[1]);     // index 1 -> encrypted salt
        System.out.println("DE: "+details);
        clientProcedure.callOnboadClientProcedure(details);
        
        status.setOnboardStatus("Client Successfull Onboarded!");
        return status;

    }

    public List<TransactionEntity> getclientTransactions(String clientId) {
        return tListRepo.findByClientId(clientId);
    }

    public LoadMoney loadMoneyRequest(LoadMoney loadMoney) {
        return loadMoneyRepo.save(loadMoney);
    }

    public List<LoadMoney> getSummery(String clientId) {
        return summeryRepo.findByClientIdAndRetailerId(clientId,"self");
    }

    public String getWalletBalance(String clientId) {
        String balance = cListRepo.getWalletBalance(clientId);
        return balance;
    }

    public List<RetailerEntity> getRetailersList(String clientId) {
       return  rListRepo.findByClientId(clientId);
    }

    public int getSuccess(String clientId) {
        return  tListRepo.calcSuccessForClient(clientId);
    }

    public String updateRetailerbalance(LoadMoney prefundDetails) {
            // capture the referene id & retailer Id for the specific load money request
            LoadMoney updatePrefund = summeryRepo.findByRetailerIdAndReference(prefundDetails.getRetailerId(), prefundDetails.getReference());
            String RetailerId = prefundDetails.getRetailerId();
            // retailer balance request
            Float addRetailerbalance = Float.parseFloat(updatePrefund.getAmount());
            String prefundStatus = prefundDetails.getStatus();
            System.out.println("Pre DETAILS: "+prefundDetails+"\n");
            System.out.println("DETAILS: "+updatePrefund+"\n");
            System.out.println(RetailerId+" "+addRetailerbalance+" "+prefundStatus);
            String clientId = updatePrefund.getClientId();
            Float clientBalance = Float.parseFloat(cListRepo.getWalletBalance(clientId));
           
            if(updatePrefund.getStatus().equals("pending") && prefundStatus.equals("accept")){
                if(clientBalance<addRetailerbalance){
                    return "You don't have sufficient balance to process this request";
                }
                RetailerEntity retailer = rListRepo.findById(RetailerId).orElse(null);
                System.out.println(retailer);
                //updating client Balance
                Float updatedClientBalance = clientBalance - addRetailerbalance;
                cListRepo.updateBalance(clientId, updatedClientBalance);
                // updateRetailerBalance
                float currentRetailerBalance = retailer.getBalance();
                float newRetailerBalance = currentRetailerBalance + addRetailerbalance;
                System.out.println("BAL: "+currentRetailerBalance+" "+newRetailerBalance);
                retailer.setBalance(newRetailerBalance);
                updatePrefund.setStatus("accepted");
                summeryRepo.save(updatePrefund);
                rListRepo.save(retailer);
                return "Balance Update Request Approved!";
            }
            else if(updatePrefund.getStatus().equals("pending") & prefundStatus.equals("decline")){
                updatePrefund.setStatus("rejected");
                summeryRepo.save(updatePrefund);
            return "Balance Update Request Rejected!";
            }
            else{
                return "This request cannot be processed, please connect to the admin!";
            }
        
    }

    public void sendEmail(String to, String subject, LoadMoney loadMoney){

        SimpleMailMessage email = new SimpleMailMessage();
        // creating mail body
        String clientId = loadMoney.getClientId();
        ClientEntity details = cListRepo.findById(clientId).orElse(null);
        
        String body = "ClientId: "+ clientId +"\n" + "Client: " + details.getName() +"\n" + 
                      "Mobile: "+  details.getMobileNo() +"\n" + "EmailId: " + details.getEmailId() +"\n" + "Current Balance: " + details.getBalance()+"\n\n\n" +
                      "Money Loaded by Client: "+"\n" +
                      "Amount: "+loadMoney.getAmount()+"\n" + "Type: "+ loadMoney.getType() + "\n" + "Client Bank: "+ loadMoney.getFromBank()+ "\n" +
                      "Client Account: " + loadMoney.getFromAccount() + "\n" + "Reference: " + loadMoney.getReference();      

        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        javaMailSender.send(email);

    }

}

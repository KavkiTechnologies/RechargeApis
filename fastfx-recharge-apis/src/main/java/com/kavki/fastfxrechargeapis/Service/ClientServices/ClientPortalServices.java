package com.kavki.fastfxrechargeapis.Service.ClientServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.ClientListRepo;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.TransactionRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.ClientLoginRepo;
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

    public void sendEmail(String to, String subject, LoadMoney loadMoney){

        SimpleMailMessage email = new SimpleMailMessage();
        // creating mail body
        String clientId = loadMoney.getClientId();
        ClientEntity details = cListRepo.findById(clientId).orElse(null);
        
        String body = "ClientId: "+ clientId +"\n" + "Client: " + details.getName() +"\n" + 
                      "Mobile: "+  details.getMobileNo() +"\n" + "EmailId: " + details.getEmailId() +"\n" + "Current Balance: " + details.getBalance()+"\n\n\n" +
                      "Money Loaded by Client: "+"\n" +
                      "Amount: "+loadMoney.getAmount()+"\n" + "Type: "+ loadMoney.getType() + "\n" + "Client Bank: "+ loadMoney.getClientBank()+ "\n" +
                      "Client Account: " + loadMoney.getClientAccount() + "\n" + "Reference: " + loadMoney.getReference();      

        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        javaMailSender.send(email);

    }
}

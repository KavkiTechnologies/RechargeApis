package com.kavki.fastfxrechargeapis.Service.RetailerServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.TransactionRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.LoadMoneyRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.PaymentSummeryRepo;
import com.kavki.fastfxrechargeapis.DAO.RetailerRepositories.RetailerLoginRepo;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.DTO.OnboardRetailerProcedure;
import com.kavki.fastfxrechargeapis.Entity.Admin.LoginStatus;
import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;
import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;
import com.kavki.fastfxrechargeapis.Entity.Retailer.OnboardRetailer;
import com.kavki.fastfxrechargeapis.Entity.Retailer.RetailerCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerPortalServices {

    
    @Autowired
    private RetailerLoginRepo loginRepo;
    @Autowired
    private OnboardRetailerProcedure retailerProcedure;
    @Autowired
    private TransactionRepo tListRepo;
    @Autowired
    private PaymentSummeryRepo summeryRepo;
    @Autowired
    private LoadMoneyRepo loadMoneyRepo;
    
    public OnboardStatus createNewRetailer(OnboardRetailer details)     
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        String Email = details.getEmail();
       // System.out.println("before d: "+details);
        OnboardStatus status = new OnboardStatus();
        RetailerCredentials creds = loginRepo.findByEmail(Email);
        if(creds!=null && Email.equalsIgnoreCase(creds.getEmail())){
            status.setOnboardStatus("Retailer Already Exists!");
            return status;
        }
        String password = details.getPassword();
        Encryptor encrypt = new Encryptor();
        String[] clientCredentials = new String[2];
        clientCredentials = encrypt.encrypt(password);

        details.setPassword(clientCredentials[0]); // index 0 -> encrypted pass 
        details.setSalt(clientCredentials[1]);     // index 1 -> encrypted salt
       // System.out.println("After d: "+details);
        retailerProcedure.callOnboadRetailerProcedure(details);
        
        status.setOnboardStatus("Retailer Successfull Onboarded!");
        return status;

    }

	public LoginStatus verifyPassword(RetailerCredentials creds) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
            String Email = creds.getEmail();
            LoginStatus loginStatus = new LoginStatus();
            RetailerCredentials credit = loginRepo.findByEmail(Email);
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
                String retailerId = credit.getRetailerId();      
                loginStatus.setUserId(retailerId);
                loginStatus.setLoginStatus("Successfull");            
                return loginStatus;
            }else{
                loginStatus.setLoginStatus("Error");
                return loginStatus;
            }
        }

    public List<TransactionEntity> getTransaction(String retailerId) {
        return tListRepo.findByRetailerId(retailerId);
    }

    public List<LoadMoney> getSummery(String retailerId) {
        return summeryRepo.findByRetailerId(retailerId);
    }

    public LoadMoney loadMoneyRequest(LoadMoney loadMoney){
        String clientId = loginRepo.findByRetailerId(loadMoney.getRetailerId());
        loadMoney.setClientId(clientId);
        System.out.println("calling client "+clientId);
        return loadMoneyRepo.save(loadMoney);
    }


}

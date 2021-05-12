package com.kavki.fastfxrechargeapis.Service.AdminServices;

import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.*;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.Entity.Admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPortalServices {
   
    @Autowired
    private MerchantListRepo mListRepo;
    @Autowired
    private ClientListRepo cListRepo;
    @Autowired
    private PrefundRepo prefundRepo;
    @Autowired
    private TransactionRepo tListRepo;
    @Autowired
    private AdminLoginRepo loginRepo;

    public List<MerchantEntity> getMRecords() {
        return mListRepo.findAll();
    }

    public List<TransactionEntity> getTRecords() {
        return tListRepo.findAll();
    }

    public List<ClientEntity> getCRecords() {
        return cListRepo.findAll();
    }

    public List<PrefundEntity> getPrefund(){
        return prefundRepo.findAll();
    }

    public Double getBalance() {
        return prefundRepo.calcTotal();
    }

    public int getSuccess(){
        return tListRepo.calcSuccess();
    }

    public int getFailed(){
        return tListRepo.calcFailed();
    }

    public int getPending(){
        return tListRepo.calcPending();
    }

    public LoginStatus verifyPassword(AdminCredentials credentials) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        String Email = credentials.getEmail();
        String Password = credentials.getPassword();
        AdminCredentials creds = loginRepo.findByEmail(Email);
        LoginStatus loginStatus = new LoginStatus();
        if(creds==null){
          loginStatus.setLoginStatus("Incorrect EmailId or Password!");
          return loginStatus;
        }

        String encryptPass = creds.getPassword();
        String salt = creds.getSalt();
        Encryptor encrypt = new Encryptor();
        String newPass = encrypt.verify(Password, salt); // verifying user password 
        
        if(newPass.equalsIgnoreCase(encryptPass)){
            loginStatus.setLoginStatus("Successfully Logged In!");
            return loginStatus;
        }else{
            loginStatus.setLoginStatus("Incorrect EmailId or Password!");
            return loginStatus;
        }
    }
    
}
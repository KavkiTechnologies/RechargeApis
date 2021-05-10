package com.kavki.fastfxrechargeapis.Service.AdminServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.*;
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

    public String verifyPassword(AdminCredentials creds) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        String Email = creds.getEmail();
        String Password = creds.getPassword();
        AdminCredentials credit = loginRepo.findByEmail(Email);

        String encryptPass = credit.getPassword();
        String salt = credit.getSalt();
        Encryptor encrypt = new Encryptor();
        String newPass = encrypt.verify(Password, salt);
        
        if(newPass.equalsIgnoreCase(encryptPass)){
            return "Logged In!";
        }else{
            return "Incorrect UserId or Password";
        }
    }
    
}
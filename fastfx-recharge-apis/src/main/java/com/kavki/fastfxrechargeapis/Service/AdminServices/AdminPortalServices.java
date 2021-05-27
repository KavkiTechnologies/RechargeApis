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
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.PaymentSummeryRepo;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.Entity.Admin.*;
import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;

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
    @Autowired
    private PaymentSummeryRepo summeryRepo;

    public List<MerchantEntity> getMRecords() {
        return mListRepo.findAll();
    }

    public List<TransactionEntity> getTRecords() {
        return tListRepo.findAll();
    }

    public List<ClientEntity> getCRecords() {
        return cListRepo.findAll();
    }

    // public List<PrefundEntity> getPrefund(){
    //     return prefundRepo.findAll();
    // }

    public List<LoadMoney> getRequests() {
       return summeryRepo.findByRetailerIdIsNull();
    }

    public Double getBalance() {
        //return prefundRepo.calcTotal();
        return cListRepo.calcTotalClientPrefund();
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
          loginStatus.setLoginStatus("Error");
          return loginStatus;
        }

        String encryptPass = creds.getPassword();
        String salt = creds.getSalt();
        Encryptor encrypt = new Encryptor();
        String newPass = encrypt.verify(Password, salt); // verifying user password 
        
        if(newPass.equalsIgnoreCase(encryptPass)){
            loginStatus.setLoginStatus("Successfull");
            return loginStatus;
        }else{
            loginStatus.setLoginStatus("Error");
            return loginStatus;
        }
    }

    public void updatebalance(LoadMoney prefundDetails) {
        LoadMoney updatePrefund = summeryRepo.findByClientIdAndReference(prefundDetails.getClientId(), prefundDetails.getReference());
        String ClientId = prefundDetails.getClientId();
        Float addbalance = Float.parseFloat(updatePrefund.getAmount());
        String prefundStatus = prefundDetails.getStatus();
        System.out.println("Pre DETAILS: "+prefundDetails+"\n");
        System.out.println("DETAILS: "+updatePrefund+"\n");
        System.out.println(ClientId+" "+addbalance+" "+prefundStatus);

        if(updatePrefund.getStatus()==null && prefundStatus.equals("accept")){
            ClientEntity client = cListRepo.findById(ClientId).orElse(null);
            float currentBalance = client.getBalance();
            float newBalance = currentBalance + addbalance;
            client.setBalance(newBalance);
            updatePrefund.setStatus("accepted");
            summeryRepo.save(updatePrefund);
            cListRepo.save(client);
        }
        else if(updatePrefund.getStatus()==null & prefundStatus.equals("decline")){
            updatePrefund.setStatus("rejected");
            summeryRepo.save(updatePrefund);
        }
    }

}
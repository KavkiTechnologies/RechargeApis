package com.kavki.fastfxrechargeapis.Service.ClientServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.TransactionRepo;
import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.ClientLoginRepo;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.DTO.OnboardClientProcedure;
import com.kavki.fastfxrechargeapis.Entity.Admin.LoginStatus;
import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;
import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPortalServices {
    
    @Autowired
    private ClientLoginRepo loginRepo;
    @Autowired
    private OnboardClientProcedure clientProcedure;
    @Autowired
    private TransactionRepo tListRepo;

    public LoginStatus verifyPassword(ClientCredentials creds, HttpServletRequest request) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        HttpSession session = request.getSession();
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
        session.setAttribute("client_id",credit.getClientId());
        if(newPass.equalsIgnoreCase(encryptPass)){
            String clientId = credit.getClientId();           
            loginStatus.setUserId(clientId);
            loginStatus.setLoginStatus("Successfull");
            
            String id = session.getId();
            String s = (String) session.getAttribute("client_id");
            System.out.println(s+" "+id);
            
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
        clientProcedure.callOnboadClientProcedure(details);
        
        status.setOnboardStatus("Client Successfull Onboarded!");
        return status;

    }

    public List<TransactionEntity> getclientTransactions(String clientId) {
        System.out.println("PS:"+clientId);
        return tListRepo.findByClientId(clientId);
    }
}

package com.kavki.fastfxrechargeapis.Service.ClientServices;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.ClientRepositories.ClientLoginRepo;
import com.kavki.fastfxrechargeapis.DTO.Encryptor;
import com.kavki.fastfxrechargeapis.DTO.OnboardClientProcedure;
import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPortalServices {
    
    @Autowired
    private ClientLoginRepo loginRepo;
    @Autowired
    private OnboardClientProcedure clientProcedure;

    public String verifyPassword(ClientCredentials creds) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        String Email = creds.getEmail();
        ClientCredentials credit = loginRepo.findByEmail(Email);
        if(credit==null)
            return "Incorrect UserId or Password!";
        
        String Password = creds.getPassword();
        String encryptPass = credit.getPassword();
        String salt = credit.getSalt();
        Encryptor encrypt = new Encryptor();
        String newPass = encrypt.verify(Password, salt);
        
        if(newPass.equalsIgnoreCase(encryptPass)){
            String clientId = credit.getClientId();
            return clientId+" Logged In!";
        }else{
            return "Incorrect UserId or Password";
        }
    }

    public String createNewClient(OnboardClient details) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        String Email = details.getEmail();

        ClientCredentials creds = loginRepo.findByEmail(Email);
        if(creds!=null && Email.equalsIgnoreCase(creds.getEmail()))
            return "Client Already Exists!";

        String password = details.getPassword();
        Encryptor encrypt = new Encryptor();
        String[] clientCredentials = new String[2];
        clientCredentials = encrypt.encrypt(password);

        details.setPassword(clientCredentials[0]); // index 0 -> encrypted pass 
        details.setSalt(clientCredentials[1]);     // index 1 -> encrypted salt
        clientProcedure.callOnboadClientProcedure(details);
         
        return "Client created";
    }
}

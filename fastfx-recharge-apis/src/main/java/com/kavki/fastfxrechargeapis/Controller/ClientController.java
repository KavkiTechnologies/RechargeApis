package com.kavki.fastfxrechargeapis.Controller;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kavki.fastfxrechargeapis.Entity.Admin.LoginStatus;
import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;
import com.kavki.fastfxrechargeapis.Service.ClientServices.ClientPortalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController { 
    
    @Autowired
    private ClientPortalServices portalServices;

    
    @PostMapping("/login")
    public LoginStatus clientLogin(@RequestBody ClientCredentials credentials,HttpServletRequest session) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        return portalServices.verifyPassword(credentials,session);
    }

    @PostMapping("/onboard")
    public OnboardStatus onboardClient(@RequestBody OnboardClient details) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        return portalServices.createNewClient(details);
        
    }
}

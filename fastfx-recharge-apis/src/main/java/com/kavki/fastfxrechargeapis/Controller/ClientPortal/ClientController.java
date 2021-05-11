package com.kavki.fastfxrechargeapis.Controller.ClientPortal;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;
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
    public String clientLogin(@RequestBody ClientCredentials credentials) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        return portalServices.verifyPassword(credentials);
    }

    @PostMapping("/onboard")
    public String onboardClient(@RequestBody OnboardClient details) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        String loginStatus = portalServices.createNewClient(details);
        return loginStatus;
    }
}

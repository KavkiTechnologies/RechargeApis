package com.kavki.fastfxrechargeapis.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kavki.fastfxrechargeapis.Entity.Admin.LoginStatus;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;
import com.kavki.fastfxrechargeapis.Entity.Retailer.OnboardRetailer;
import com.kavki.fastfxrechargeapis.Entity.Retailer.RetailerCredentials;
import com.kavki.fastfxrechargeapis.Service.RetailerServices.RetailerPortalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/retailer")
public class RetailerController {

      
    @Autowired
    private RetailerPortalServices portalServices;

    @PostMapping("/onboard")
    public OnboardStatus onboardRetailer(@RequestBody OnboardRetailer details) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        System.out.println("calling: "+details);
           return portalServices.createNewRetailer(details);
           
    }

    @PostMapping("/login")
    public LoginStatus retailerLogin(@RequestBody RetailerCredentials credentials) 
        throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException{
        return portalServices.verifyPassword(credentials);
    }
    
}

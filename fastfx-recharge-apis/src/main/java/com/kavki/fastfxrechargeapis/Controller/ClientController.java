package com.kavki.fastfxrechargeapis.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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
import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;
import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;
import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;
import com.kavki.fastfxrechargeapis.Service.ClientServices.ClientPortalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController { 
    
    @Autowired
    private ClientPortalServices portalServices;
    @Autowired
    private JavaMailSender emailSender;
    

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

    @GetMapping("/transactions/{clientId}")
    public List<TransactionEntity> getTransactions(@PathVariable String clientId)
    {
        return portalServices.getclientTransactions(clientId);
    }

    @PostMapping("/loadmoney")
    public String loadMoney(@RequestBody LoadMoney loadMoney){
        portalServices.loadMoneyRequest(loadMoney);
        portalServices.sendEmail("rawatchetan133@gmail.com", "Prefund Uploaded",loadMoney);
        return "Thankyou, Details Uploaded Successfully!";
    }


}

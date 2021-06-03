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
import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;
import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;
import com.kavki.fastfxrechargeapis.Entity.Client.OnboardStatus;
import com.kavki.fastfxrechargeapis.Entity.Recharge.DateGenerator;
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
    
    @GetMapping("/transactions/{retailerId}")
    public List<TransactionEntity> getRetailerTransactions(@PathVariable String retailerId){
        return portalServices.getTransaction(retailerId);
    }

    @GetMapping("/paymentsummery/{retailerId}")
    public List<LoadMoney> getPaymentSummery(@PathVariable String retailerId){
        return portalServices.getSummery(retailerId);
    }

    @PostMapping("/loadmoney")
    public String loadMoney(@RequestBody LoadMoney loadMoney){
        DateGenerator date = new DateGenerator();
        loadMoney.setReqDate(date.getTimeStamp());
        loadMoney.setStatus("pending");
       // System.out.println("Load: "+loadMoney);
        portalServices.loadMoneyRequest(loadMoney);
       // System.out.println("DONE");
        //portalServices.sendEmail("rawatchetan133@gmail.com", "Prefund Uploaded",loadMoney);
      //  return "Request Submitted, please contact your client !";
      return "true";
    }

    @GetMapping("/fastxbalance/{retailerId}")
    public String getFastfxBalance(@PathVariable String retailerId){
        return portalServices.getWalletBalance(retailerId);
    }
}

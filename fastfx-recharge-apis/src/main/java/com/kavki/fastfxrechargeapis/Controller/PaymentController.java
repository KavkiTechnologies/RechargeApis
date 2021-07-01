package com.kavki.fastfxrechargeapis.Controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.Entity.Payments.IciciCredentials;
import com.kavki.fastfxrechargeapis.Service.PaymentServices.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService payService;
    
    @PostMapping("/upi")
    public String upiTransaction(@RequestBody IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException{
        return payService.UpiPayments(iciciCreds);
    }

    @PostMapping("/refund")
    public String upiRefund(@RequestBody IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException{
        return payService.UpiPaymentsRefund(iciciCreds);
    }

    @PostMapping("/transactionstatus")
    public String upiTransactionStatus(@RequestBody IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException{
        return payService.UpiPaymentsStatus(iciciCreds);
    }
}

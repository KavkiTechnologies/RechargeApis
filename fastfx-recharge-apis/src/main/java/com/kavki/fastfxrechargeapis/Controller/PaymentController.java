package com.kavki.fastfxrechargeapis.Controller;

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
    public String upiTransaction(@RequestBody IciciCredentials iciciCreds){
        return payService.UpiPayments(iciciCreds);
    }
    @PostMapping("/refund")
    public String upiRefund(@RequestBody IciciCredentials iciciCreds){
        return payService.UpiPaymentsRefund(iciciCreds);
    }
    @PostMapping("/transactionstatus")
    public String upiTransactionStatus(@RequestBody IciciCredentials iciciCreds){
        return payService.UpiPaymentsStatus(iciciCreds);
    }
    @PostMapping("/callbackstatus")
    public String upiTransactionCallStatus(@RequestBody IciciCredentials iciciCreds){
        return payService.UpiPaymentsCallStatus(iciciCreds);
    }
}


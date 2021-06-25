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
    public void upiTransaction(@RequestBody IciciCredentials iciciCreds){
        payService.UpiPayments(iciciCreds);
    }
}

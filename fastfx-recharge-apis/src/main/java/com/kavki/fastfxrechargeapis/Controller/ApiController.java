package com.kavki.fastfxrechargeapis.Controller;

import com.kavki.fastfxrechargeapis.Entity.MobileRechargeModel;
import com.kavki.fastfxrechargeapis.Entity.MobileRechargeResponse;
import com.kavki.fastfxrechargeapis.Service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge")
public class ApiController {
    
    @Autowired
    private ApiService apiService;

    @GetMapping("/prepaid")
    public MobileRechargeResponse doPrepaidRecharge(@RequestBody MobileRechargeModel prepaidParams){
        return apiService.prepaidRecharge(prepaidParams);
    }

    @GetMapping("/postpaid")
    public MobileRechargeResponse doPostpaidRecharge(@RequestBody MobileRechargeModel postpaidParams){
        return apiService.postpaidRecharge(postpaidParams);
    }
}

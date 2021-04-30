package com.kavki.fastfxrechargeapis.Controller;

import com.kavki.fastfxrechargeapis.DAO.PrepaidDao;
import com.kavki.fastfxrechargeapis.Entity.DthRecharge;
import com.kavki.fastfxrechargeapis.Entity.DthResponse;
import com.kavki.fastfxrechargeapis.Entity.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.MobileResponse;
import com.kavki.fastfxrechargeapis.ObjectMapperRepository.PrepaidMapping;
import com.kavki.fastfxrechargeapis.Service.ApiService;
import com.kavki.fastfxrechargeapis.Service.DatabaseService;

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

    @Autowired
    private DatabaseService dbService;

    @GetMapping("/prepaid")
    public MobileResponse doPrepaidRecharge(@RequestBody MobileRecharge prepaidParams){
        MobileResponse response = new MobileResponse();
        response = apiService.prepaidRecharge(prepaidParams);
        PrepaidMapping pm = new PrepaidMapping();
        PrepaidDao pd =  pm.prepaidFieldMappingForDb(prepaidParams, response);
        System.out.println("PD: "+pd);
        dbService.saveTransaction(pd);
        return response;
    }

    @GetMapping("/postpaid")
    public MobileResponse doPostpaidRecharge(@RequestBody MobileRecharge postpaidParams){
        return apiService.postpaidRecharge(postpaidParams);
    }

    @GetMapping("/dth")
    public DthResponse doDTHRecharge(@RequestBody DthRecharge dthParams){
        return apiService.dthRecharge(dthParams);
    }
}

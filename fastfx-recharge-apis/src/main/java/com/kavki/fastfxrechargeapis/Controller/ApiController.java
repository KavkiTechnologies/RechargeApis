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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/prepaid")
    public MobileResponse doPrepaidRecharge(@RequestBody MobileRecharge requestParams){      
        MobileResponse responseParams = new MobileResponse();
        responseParams = apiService.prepaidRecharge(requestParams);
        PrepaidMapping mapper = new PrepaidMapping();
        PrepaidDao pd =  mapper.prepaidFieldMappingForDb(requestParams, responseParams);
        dbService.saveTransaction(pd);
        return responseParams;
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

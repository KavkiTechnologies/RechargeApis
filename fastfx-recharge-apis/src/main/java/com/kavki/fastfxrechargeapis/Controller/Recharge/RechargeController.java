package com.kavki.fastfxrechargeapis.Controller.Recharge;

import com.kavki.fastfxrechargeapis.DTO.MobileToDbEntity;
import com.kavki.fastfxrechargeapis.DTO.TransactionProcedure;
import com.kavki.fastfxrechargeapis.Entity.Recharge.*;
import com.kavki.fastfxrechargeapis.ParamsMapping.MobileParamsMapping;
import com.kavki.fastfxrechargeapis.Service.RechargeServices.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recharge")
public class RechargeController {
    
	@Autowired
    private RechargeService apiService;
    // @Autowired
    // private DatabaseService dbService;
    @Autowired 
    private TransactionProcedure tProcedure;

    
    @PostMapping("/prepaid")
    public String doPrepaidRecharge(@RequestBody MobileRecharge requestParams){      
        MobileResponse responseParams = new MobileResponse();
        responseParams = apiService.prepaidRecharge(requestParams);
        MobileParamsMapping mapper = new MobileParamsMapping();
        MobileToDbEntity db =  mapper.mobileEntityMappingForDb(requestParams, responseParams);
        tProcedure.callTransactionProcedure(db);
        // dbService.saveTransaction(db);
        return responseParams.getMESSAGE();
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

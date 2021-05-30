package com.kavki.fastfxrechargeapis.Controller;

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

    @GetMapping("/checkbalance")
    public String chekBalance()
    {
        return apiService.checkRkitBalance();
    }
    @PostMapping("/prepaid")
    public MobileResponse doPrepaidRecharge(@RequestBody MobileRecharge requestParams){ 
             
        MobileResponse responseParams = new MobileResponse();
        responseParams = apiService.prepaidRecharge(requestParams);
        System.out.println("Response: "+responseParams);
        MobileParamsMapping mapper = new MobileParamsMapping();
        MobileToDbEntity db =  mapper.mobileEntityMappingForDb(requestParams, responseParams);
        DateGenerator date = new DateGenerator();
        db.setServiceType("prepaid");
        db.setTransDate(date.getTimeStamp());
        System.out.println("DB: "+db);
        tProcedure.callTransactionProcedure(db);
      //  dbService.saveTransaction(db);
        return responseParams;
    }

    @PostMapping("/postpaid")
    public MobileResponse doPostpaidRecharge(@RequestBody MobileRecharge requestParams){
      //  return apiService.postpaidRecharge(postpaidParams);
        MobileResponse responseParams = new MobileResponse();
        responseParams = apiService.postpaidRecharge(requestParams);
        MobileParamsMapping mapper = new MobileParamsMapping();
        MobileToDbEntity db =  mapper.mobileEntityMappingForDb(requestParams, responseParams);
        DateGenerator date = new DateGenerator();
        db.setServiceType("prepaid");
        db.setTransDate(date.getTimeStamp());
        System.out.println("DB: "+db);
        tProcedure.callTransactionProcedure(db);
        System.out.println("done trans");
        // dbService.saveTransaction(db);
     //   return responseParams.getMESSAGE();
     return responseParams;
    }

    @PostMapping("/dth")
    public DthResponse doDTHRecharge(@RequestBody DthRecharge dthParams){
        return apiService.dthRecharge(dthParams);
    }
}

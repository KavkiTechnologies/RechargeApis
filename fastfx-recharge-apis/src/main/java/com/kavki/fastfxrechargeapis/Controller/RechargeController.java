package com.kavki.fastfxrechargeapis.Controller;

import com.kavki.fastfxrechargeapis.DTO.MapToDbEntity;
import com.kavki.fastfxrechargeapis.DTO.TransactionProcedure;
import com.kavki.fastfxrechargeapis.Entity.Recharge.*;
import com.kavki.fastfxrechargeapis.Service.RechargeServices.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recharge")
public class RechargeController {
    
	  @Autowired
    private RechargeService apiService;
    @Autowired 
    private TransactionProcedure transProcedure;
    @Autowired
    private RkitMobileResponse rkitResponseParams;
    @Autowired
    private MobileResponse response;
    @Autowired
    private MapToDbEntity dbEntityMapper;
    @Autowired
    private DateGenerator date;

    @GetMapping("/checkbalance")
    public String chekBalance()
    {
        return apiService.checkRkitBalance();
    }
    
    @PostMapping("/prepaid")
    public MobileResponse doPrepaidRecharge(@RequestBody MobileRecharge requestParams){ 
             
        String message = apiService.checkUserBalance(requestParams);
        if(message.equals("balance updated")){
          // calling rkit prepaid recharge api
          rkitResponseParams = apiService.prepaidRecharge(requestParams);         
          // mapping both mobileRequest & mobileResponse to Database Entity to be passed to transaction procedure
          MapToDbEntity databaseEntity = dbEntityMapper.mapMobileToDbEntity(requestParams, rkitResponseParams);
         
          databaseEntity.setServiceType("Prepaid"); // adding service type 
          databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
          databaseEntity.setCharge("none"); // prepaid apis don't have charge amount
          transProcedure.callTransactionProcedure(databaseEntity); // calling transaction procedure
          return response.mapRkitResponseToCustomResponse(rkitResponseParams);
        }
        else{
          response.setMessage(message);
          return response;
        }
    }

    @PostMapping("/postpaid")
    public MobileResponse doPostpaidRecharge(@RequestBody MobileRecharge requestParams){
  
      String message = apiService.checkUserBalance(requestParams);
      if(message.equals("balance updated")){
        rkitResponseParams = apiService.postpaidRecharge(requestParams);
        // mapping both mobileRequest & mobileResponse to Database Entity to be passed to transaction procedure
        MapToDbEntity databaseEntity = dbEntityMapper.mapMobileToDbEntity(requestParams, rkitResponseParams);
        
        databaseEntity.setServiceType("Postpaid"); // adding service type 
        databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
        transProcedure.callTransactionProcedure(databaseEntity); // calling transaction procedure
        return response.mapRkitResponseToCustomResponse(rkitResponseParams);
      }
      else{
        response.setMessage(message);
        return response;
      }
    }

    @PostMapping("/dth")
    public DthResponse doDTHRecharge(@RequestBody DthRecharge dthParams){
        return apiService.dthRecharge(dthParams);
    }
}

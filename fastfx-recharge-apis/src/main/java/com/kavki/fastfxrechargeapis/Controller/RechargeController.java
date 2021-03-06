package com.kavki.fastfxrechargeapis.Controller;

import java.util.List;

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
    private RkitApiResponse rkitResponse;
    @Autowired
    private RechargeRsponse response;
    @Autowired
    private MapToDbEntity dbEntityMapper;
    @Autowired
    private DateGenerator date;
    @Autowired
    private OperatorCodes operatorCodes;

    @GetMapping("/checkbalance")
    public String chekBalance()
    {
        return apiService.checkRkitBalance();
    }

    @PostMapping("/orderstatus")
    public RechargeRsponse checkOrderStatus(@RequestBody RechargeRsponse params){
      System.out.print("Order: "+params+"\n");
     // return null;  
     rkitResponse= apiService.getOrderStatus(params);
     return response.mapRkitResponseToCustomResponse(rkitResponse);
    }
    
    @PostMapping("/prepaid")
    public RechargeRsponse doPrepaidRecharge(@RequestBody MobileRecharge requestParams){ 
      // verify user balance 
      String message = apiService.checkUserBalance(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
        // if req user balance is present, rechagre amount is first deducted & then api is called
        if(message.equals("balance updated")){
          // calling rkit prepaid recharge api
          rkitResponse = apiService.prepaidRecharge(requestParams); 
          // mapping both mobileRequest & mobileResponse to Database Entity to be passed to transaction procedure
          MapToDbEntity databaseEntity = dbEntityMapper.mapMobileToDbEntity(requestParams, rkitResponse);
          System.out.println("DB befor: "+databaseEntity+"\n");
          databaseEntity.setServiceType("Prepaid"); // adding service type 
          databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
          databaseEntity.setCharge("0.00"); // prepaid apis don't have charge amount
          databaseEntity.setServiceProvider("Rkit"); //adding Service Provider for this api
          // setting operator name based on operator code 
          databaseEntity.setOperatorName(operatorCodes.getOperator(requestParams.getOperator_code())); 
          // in case of failed transaction, processing a refund
          if(rkitResponse.getERROR()!=0){
            apiService.wallentRefund(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
            databaseEntity.setRefund("Refund completed");
          }
          else{
            // Success transaction
            databaseEntity.setRefund("Not Req");
          }
          System.out.println("DB after: "+databaseEntity+"\n");
          // System.out.println(operatorCodes.getOperator(requestParams.getOperator_code()));
          transProcedure.callTransactionProcedure(databaseEntity); // calling transaction procedure
          return response.mapRkitResponseToCustomResponse(rkitResponse);
        }
        else{
          response.setMessage(message);
          return response;
        }
    }

    @PostMapping("/postpaid")
    public RechargeRsponse doPostpaidRecharge(@RequestBody MobileRecharge requestParams){
      String message = apiService.checkUserBalance(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
      if(message.equals("balance updated")){
        rkitResponse = apiService.postpaidRecharge(requestParams);
        // mapping both mobileRequest & mobileResponse to Database Entity to be passed to transaction procedure
        MapToDbEntity databaseEntity = dbEntityMapper.mapMobileToDbEntity(requestParams, rkitResponse);
        databaseEntity.setServiceType("Postpaid"); // adding service type 
        databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
        databaseEntity.setServiceProvider("Rkit"); //adding Service Provider for this api
        databaseEntity.setOperatorName(operatorCodes.getOperator(requestParams.getOperator_code())); // setting operator name based on operator code 
        // in case of failed transaction, processing a refund
        if(rkitResponse.getERROR()!=0){
          apiService.wallentRefund(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
          databaseEntity.setRefund("Refund completed");
        }
        else{
          // Success transaction
          databaseEntity.setRefund("Not Req");
        }
        System.out.println("DB after: "+databaseEntity+"\n");
        transProcedure.callTransactionProcedure(databaseEntity); // calling transaction procedure
        return response.mapRkitResponseToCustomResponse(rkitResponse);
      }
      else{
        response.setMessage(message);
        return response;
      }
    }

    @PostMapping("/dth")
    public RechargeRsponse doDTHRecharge(@RequestBody DthRecharge requestParams){
      System.out.println("req params1: "+requestParams+"\n");
      String message = apiService.checkUserBalance(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
        if(message.equals("balance updated")){
          // calling rkit prepaid recharge api
          //System.out.println("DTH: "+requestParams);
          rkitResponse = apiService.dthRecharge(requestParams);
          System.out.println("RkitResponse: "+rkitResponse+"\n");
          System.out.println("req params2: "+requestParams+"\n");
          MapToDbEntity databaseEntity = dbEntityMapper.mapDthToDbEntity(requestParams, rkitResponse);
          databaseEntity.setServiceType("Dth"); // adding service type 
          databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
          databaseEntity.setServiceProvider("Rkit");  //adding Service Provider for this api
          databaseEntity.setOperatorName(operatorCodes.getOperator(requestParams.getOperator_code())); // setting operator name based on operator code 
          // in case of failed transaction, processing a refund
          System.out.println("req params3: "+requestParams+"\n");
          System.out.println("db before: "+databaseEntity+"\n");
          if(rkitResponse.getERROR()!=0){
            apiService.wallentRefund(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
            databaseEntity.setRefund("Refund completed");
          }
          else{
            // Success transaction
            databaseEntity.setRefund("Not Req");
          }
          System.out.println("req params4: "+requestParams+"\n");
          System.out.println("db: "+databaseEntity+"\n");
          transProcedure.callTransactionProcedure(databaseEntity); // calling transaction procedure
          return response.mapRkitResponseToCustomResponse(rkitResponse);      
          }
        else{
          response.setMessage(message);
          return response;
        }
    }

    @PostMapping("/ottplans")
    public OttPlans getPlanDetails(@RequestBody OttRecharge plans){
      //return apiService.fetchPlans(plans);
      return apiService.fetchOttPlans(plans.getOperator_code());
    }

    @PostMapping("/ott")
    public RechargeRsponse dOttRecharge(@RequestBody OttRecharge requestParams){
      // getting recharge amount based on operator_code and plan_id 
      int planId = requestParams.getPlan_id();
      boolean valid_request = false;
      OttPlans plans = apiService.fetchOttPlans(requestParams.getOperator_code());
      System.out.println("plans: "+plans);
      List<OttPlansData> list = plans.getDATA();
      for (OttPlansData planDetails : list) { 
        if(planDetails.plan_id == planId){
          requestParams.setAmount(planDetails.amount);
          valid_request = true;
        }
      }
      if(valid_request==false){
        response.setMessage("plan_id doesn't match for the operator code");
        return response;
      }
      String message = apiService.checkUserBalance(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
        if(message.equals("balance updated")){
          System.out.println("REQ PARAMS: "+requestParams+"\n");
          rkitResponse = apiService.ottRecharge(requestParams);
          //System.out.println("RkitResponse: "+rkitResponse+"\n");
          MapToDbEntity databaseEntity = dbEntityMapper.mapOttToDbEntity(requestParams, rkitResponse);
          databaseEntity.setServiceType("Ott"); // adding service type 
          databaseEntity.setTransDate(date.getTimeStamp()); //adding current transaction date
          databaseEntity.setServiceProvider("Rkit");  //adding Service Provider for this api
          databaseEntity.setOperatorName(operatorCodes.getOperator(requestParams.getOperator_code())); // setting operator name based on operator code 
           // in case of failed transaction, processing a refund
           if(rkitResponse.getERROR()!=0){
            apiService.wallentRefund(requestParams.getClientId(), requestParams.getRetailerId(), requestParams.getAmount());
            databaseEntity.setRefund("Refund completed");
          }
          else{
            // Success transaction
            databaseEntity.setRefund("Not Req");
          }
          System.out.println("ott db: "+databaseEntity+"\n");
          transProcedure.callTransactionProcedure(databaseEntity);
          return response.mapRkitResponseToCustomResponse(rkitResponse);

        }
        else{
            response.setMessage(message);
            return response;
        }
    }
}



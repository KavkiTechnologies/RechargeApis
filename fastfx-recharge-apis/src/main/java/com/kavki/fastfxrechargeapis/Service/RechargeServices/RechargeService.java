package com.kavki.fastfxrechargeapis.Service.RechargeServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.ClientListRepo;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.RetailerListRepo;
import com.kavki.fastfxrechargeapis.DAO.RetailerRepositories.RetailerLoginRepo;
import com.kavki.fastfxrechargeapis.DTO.MapToDbEntity;
import com.kavki.fastfxrechargeapis.Entity.Admin.RetailerEntity;
import com.kavki.fastfxrechargeapis.Entity.Recharge.DthRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.DthResponse;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.OttPlansData;
import com.kavki.fastfxrechargeapis.Entity.Recharge.OttPlans;
import com.kavki.fastfxrechargeapis.Entity.Recharge.OttRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.RechargeRsponse;
import com.kavki.fastfxrechargeapis.Entity.Recharge.RkitApiResponse;
import com.kavki.fastfxrechargeapis.Entity.Recharge.RkitWalletBalance;
import com.kavki.fastfxrechargeapis.Entity.Recharge.TransactionIdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RechargeService {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
	private Environment env;
    @Autowired
    private RetailerLoginRepo rLoginRepo;
    @Autowired
    private RetailerListRepo rListRepo;
    @Autowired 
    private ClientListRepo cListRepo;

    private static String baseUrl = "https://sandbox.rechargkit.biz/get/";

    public RkitApiResponse prepaidRecharge(MobileRecharge rechargeObj){

        try{
            String new_url = baseUrl + "prepaid/mobile";
            String clientId, transId, retailerId;

            retailerId = rechargeObj.getRetailerId();
            clientId = rechargeObj.getClientId();
            // genertaing transaction Id 
            if(retailerId != null){
                clientId = rLoginRepo.findByRetailerId(retailerId);
                rechargeObj.setClientId(clientId);
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getRetailerId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
            }
            else if(clientId !=null && retailerId == null){
                rechargeObj.setRetailerId("self");
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
            }
            else{
                RkitApiResponse responseObj = new RkitApiResponse();
                responseObj.setMESSAGE("Call Service in proper way");
                return responseObj;
            }

            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("mobile_no", rechargeObj.getMobile_no())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id",  transId) // passing our transaction Id
            .queryParam("circle", rechargeObj.getCircle())
            .queryParam("recharge_type", rechargeObj.getRecharge_type())
            .queryParam("user_var1",rechargeObj.getUser_var1())
            .queryParam("user_var2",rechargeObj.getUser_var2())
            .queryParam("user_var3",rechargeObj.getUser_var3());

            //Consuming Recharge API for GET 
            System.out.println("URL: "+uriBuilder.toUriString());
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody(); // capturing api json response
            
            //converting json response from API to java Response Obj
            RkitApiResponse responseObj = new ObjectMapper().readValue(jsonStr, RkitApiResponse.class);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

    public RkitApiResponse postpaidRecharge(MobileRecharge rechargeObj) {
        try{
            String new_url = baseUrl + "postpaid/mobile";
            String clientId, transId, retailerId;

            retailerId = rechargeObj.getRetailerId();
            clientId = rechargeObj.getClientId();
            // genertaing transaction Id 
            if(retailerId != null){
                clientId = rLoginRepo.findByRetailerId(retailerId);
                rechargeObj.setClientId(clientId);
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getRetailerId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
            }
            else if(clientId !=null && retailerId == null){
                rechargeObj.setRetailerId("self");
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
            }
            else{
                RkitApiResponse responseObj = new RkitApiResponse();
                responseObj.setMESSAGE("Call Service in proper way");
                return responseObj;
            }
                      
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("mobile_no", rechargeObj.getMobile_no())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id", transId)
            .queryParam("circle", rechargeObj.getCircle())
            .queryParam("recharge_type", rechargeObj.getRecharge_type())
            .queryParam("user_var1",rechargeObj.getUser_var1())
            .queryParam("user_var2",rechargeObj.getUser_var2())
            .queryParam("user_var3",rechargeObj.getUser_var3());
            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            //converting json response from API to Response Obj
            System.out.println("URL: "+uriBuilder.toUriString()+"\n");
            RkitApiResponse responseObj = new ObjectMapper().readValue(jsonStr, RkitApiResponse.class);
            System.out.println("Rkit resonse:"+responseObj+"\n");
            return responseObj;
        }
        catch(Exception e){
            return null;
        } 
    }

    public RkitApiResponse dthRecharge(DthRecharge rechargeObj) {
        try{
            String new_url = baseUrl + "/dth";
            String clientId, transId, retailerId;

            retailerId = rechargeObj.getRetailerId();
            clientId = rechargeObj.getClientId();
            // genertaing transaction Id 
            if(retailerId != null){
                clientId = rLoginRepo.findByRetailerId(retailerId);
                rechargeObj.setClientId(clientId);
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getRetailerId(),rechargeObj.getOperator_code(), 0);
            }
            else if(clientId !=null && retailerId == null){
                rechargeObj.setRetailerId("self");
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), 0);
            }
            else{
                RkitApiResponse responseObj = new RkitApiResponse();
                responseObj.setMESSAGE("Call Service in proper way");
                return responseObj;
            }
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("customer_id", rechargeObj.getCustomer_id())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id", transId)
            .queryParam("user_va1", rechargeObj.getUser_var1())
            .queryParam("user_var2",rechargeObj.getUser_var2())
            .queryParam("user_var3",rechargeObj.getUser_var3());

            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            //converting json response from API to Response Obj
            RkitApiResponse responseObj = new ObjectMapper().readValue(jsonStr, RkitApiResponse.class);
            return responseObj;
        }
        catch(Exception e){
           return null;
        } 
    }

    public OttPlans fetchOttPlans(int operator_code) {
        try{
            String new_url = "https://dev.rechargkit.biz/ott/planDetailsget";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("operator_code",operator_code);
            
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            OttPlans responseObj = new ObjectMapper().readValue(jsonStr, OttPlans.class); // converting Json to java Object
            return responseObj;
        }
        catch(Exception e){
            return null;
        }

    }
    
    public RkitApiResponse ottRecharge(OttRecharge rechargeObj) {
        try{
            String new_url = "https://dev.rechargkit.biz/ott/createSubscriptionget";
            String clientId, transId, retailerId;

            retailerId = rechargeObj.getRetailerId();
            clientId = rechargeObj.getClientId();
            System.out.println("params: "+rechargeObj);
            // genertaing transaction Id 
            if(retailerId != null){
                clientId = rLoginRepo.findByRetailerId(retailerId);
                rechargeObj.setClientId(clientId);
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getRetailerId(),rechargeObj.getOperator_code(), 0);
            }
            else if(clientId !=null && retailerId == null){
                rechargeObj.setRetailerId("self");
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), 0);
            }
            else{
                RkitApiResponse responseObj = new RkitApiResponse();
                responseObj.setMESSAGE("Call Service in proper way");
                return responseObj;
            }
           
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("plan_id", rechargeObj.getPlan_id())
            .queryParam("mobile_no", rechargeObj.getMobile_no())
            .queryParam("partner_request_id", transId)
            .queryParam("customer_email", rechargeObj.getCustomer_email());
             System.out.println("URL: "+uriBuilder.toUriString()+"\n");
            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            System.out.println("js: "+jsonStr+"\n");
            //converting json response from API to Response Obj
            RkitApiResponse responseObj = new ObjectMapper().readValue(jsonStr, RkitApiResponse.class);
            System.out.println("jsObj: "+responseObj);
            return responseObj;
            //return null;
        }
        catch(Exception e)
        {
           return null;
        } 
    }

    public String checkRkitBalance() {
        try{
            String new_url = baseUrl + "/user/balance";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"));

            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            RkitWalletBalance responseObj = new ObjectMapper().readValue(jsonStr, RkitWalletBalance.class); // converting Json to java Object
            return responseObj.getWALLET_BALANCE();
        }
        catch(Exception e){
            return null;
        }
    }

    public String checkUserBalance(String clientId, String retailerId, int amount) {
        Float rechargeAmount, currentBalance, updatedBalance;
        rechargeAmount = (float) amount;

        if(retailerId!=null)
{
            currentBalance = Float.parseFloat(rListRepo.getWalletBalance(retailerId)); 
            System.out.println("cur: "+currentBalance);
            if(rechargeAmount<= currentBalance){
                updatedBalance = currentBalance - rechargeAmount;
                System.out.println("up: "+updatedBalance);
                rListRepo.updateBalance(retailerId,updatedBalance);
                return "balance updated";
            }
            else{
                return "Insufficient Balance";
            }
        
        }
        else if(retailerId==null && clientId!=null)
        {
            currentBalance = Float.parseFloat(cListRepo.getWalletBalance(clientId));
            System.out.println("cur: "+currentBalance);
            if(rechargeAmount<= currentBalance){
                updatedBalance = currentBalance - rechargeAmount;
                System.out.println("up: "+updatedBalance);
                cListRepo.updateBalance(clientId,updatedBalance);
                return "balance updated";
            }
            else{
                return "Insufficient Balance";
            }
        }
        else {
            return "Call service in proper way";
        }
    }

    public RkitApiResponse getOrderStatus(RechargeRsponse params) {
        try{
            String new_url = baseUrl + "orderstatus";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("order_id", params.getOrderid())
            .queryParam("user_var1", params.getUservar1())
            .queryParam("user_var2",params.getUservar2())
            .queryParam("user_var3",params.getUservar3());
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            RkitApiResponse responseObj = new ObjectMapper().readValue(jsonStr, RkitApiResponse.class); // converting Json to java Object
            return responseObj;
        }
        catch(Exception e){
            return null;
        }
    }
   
	public void wallentRefund(String clientId, String retailerId, int amount) {
        Float rechargeAmount, currentBalance, updatedBalance;
        rechargeAmount = (float) amount;
        System.out.println(clientId+" "+retailerId+" "+rechargeAmount);
        if(retailerId!=null && retailerId!="self")
        {
            currentBalance = Float.parseFloat(rListRepo.getWalletBalance(retailerId)); 
            System.out.println("cur baL: "+currentBalance);
            updatedBalance = currentBalance + rechargeAmount;
            System.out.println("up ref: "+updatedBalance);
            rListRepo.updateBalance(retailerId,updatedBalance);
        }
        else 
        {
            System.out.println("inside"+"\n");
            currentBalance = Float.parseFloat(cListRepo.getWalletBalance(clientId));
            System.out.println("cur bal: "+currentBalance);
            updatedBalance = currentBalance + rechargeAmount;
            System.out.println("up ref: "+updatedBalance);
            cListRepo.updateBalance(clientId,updatedBalance);
        }
	}
    
}

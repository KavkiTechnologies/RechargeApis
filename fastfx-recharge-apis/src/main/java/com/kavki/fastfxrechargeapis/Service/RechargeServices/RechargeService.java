package com.kavki.fastfxrechargeapis.Service.RechargeServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavki.fastfxrechargeapis.DAO.RetailerRepositories.RetailerLoginRepo;
import com.kavki.fastfxrechargeapis.Entity.Admin.RkitWalletBalance;
import com.kavki.fastfxrechargeapis.Entity.Recharge.DthRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.DthResponse;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileResponse;
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

    private static String baseUrl = "https://rechargkit.biz/get/";

    public MobileResponse prepaidRecharge(MobileRecharge rechargeObj){

        try{
            String new_url = baseUrl + "prepaid/mobile";
            String clientId, transId;
            // genertaing transaction Id 
            if(rechargeObj.getRetailerId() != null){
                clientId = rLoginRepo.findByRetailerId(rechargeObj.getRetailerId());
                rechargeObj.setClientId(clientId);
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getRetailerId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
            }
            else{
                transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
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
            .queryParam("user_var1",rechargeObj.getClientId())
            .queryParam("user_var2",rechargeObj.getRetailerId());

            //Consuming Recharge API for GET 
            System.out.println("URL: "+uriBuilder.toUriString());
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            
            //converting json response from API to Response Obj
            MobileResponse responseObj = new ObjectMapper().readValue(jsonStr, MobileResponse.class);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

    public MobileResponse postpaidRecharge(MobileRecharge rechargeObj) {
        try{
            String new_url = baseUrl + "postpaid/mobile";
            // generating transacionId
            String transId = new TransactionIdGenerator().generateTransId(rechargeObj.getClientId(),rechargeObj.getOperator_code(), rechargeObj.getCircle());
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
            .queryParam("user_var1",rechargeObj.getClientId());

            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            
            //converting json response from API to Response Obj
            MobileResponse responseObj = new ObjectMapper().readValue(jsonStr, MobileResponse.class);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

    public DthResponse dthRecharge(DthRecharge rechargeObj) {
        try{
            String new_url = baseUrl + "/dth";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
            .queryParam("customer_id", rechargeObj.getCustomer_id())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id", rechargeObj.getPartner_request_id())
            .queryParam("customer_email", rechargeObj.getCustomer_email())
            .queryParam("user_val1", rechargeObj.getUser_varl1());
          //  .queryParam("user_val2", rechargeObj.getUser_varl2());

            //Consuming Recharge API for GET 
            System.out.println("URL: "+uriBuilder.toString());
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            
            //converting json response from API to Response Obj
            DthResponse responseObj = new ObjectMapper().readValue(jsonStr, DthResponse.class);
            //System.out.println(responseObj);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

    public String checkRkitBalance() {
      //  https://rechargkit.biz/get/user/balance?partner_id=RKITAPI210305&api_password=xdvcsx6
        try{
            String new_url = baseUrl + "/user/balance";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"));

            System.out.println("URL: "+uriBuilder.toUriString());
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            System.out.println("json: "+jsonStr);
            RkitWalletBalance responseObj = new ObjectMapper().readValue(jsonStr, RkitWalletBalance.class);
            System.out.println("Res: "+responseObj+"\n");
            return responseObj.getWALLET_BALANCE();
    }
    catch(Exception e)
    {
         System.out.println(e.getMessage());
         return null;
    }
    }
}

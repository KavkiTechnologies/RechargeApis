package com.kavki.fastfxrechargeapis.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavki.fastfxrechargeapis.Entity.MobileRechargeModel;
import com.kavki.fastfxrechargeapis.Entity.MobileRechargeResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
    
    @Autowired
    private RestTemplate restTemplate;

    private static String baseUrl = "https://sandbox.rechargkit.biz/get/";

    public MobileRechargeResponse prepaidRecharge(MobileRechargeModel rechargeObj){

        try{
            String new_url = baseUrl + "prepaid/mobile";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id",rechargeObj.getPartner_id())
            .queryParam("api_password",rechargeObj.getApi_password())
            .queryParam("mobile_no", rechargeObj.getMobile_no())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id", rechargeObj.getPartner_request_id())
            .queryParam("circle", rechargeObj.getCircle())
            .queryParam("recharge_type", rechargeObj.getRecharge_type());

            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            
            //converting json response from API to Response Obj
            MobileRechargeResponse responseObj = new ObjectMapper().readValue(jsonStr, MobileRechargeResponse.class);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

    public MobileRechargeResponse postpaidRecharge(MobileRechargeModel rechargeObj) {
        try{
            String new_url = baseUrl + "postpaid/mobile";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id",rechargeObj.getPartner_id())
            .queryParam("api_password",rechargeObj.getApi_password())
            .queryParam("mobile_no", rechargeObj.getMobile_no())
            .queryParam("operator_code", rechargeObj.getOperator_code())
            .queryParam("amount", rechargeObj.getAmount())
            .queryParam("partner_request_id", rechargeObj.getPartner_request_id())
            .queryParam("circle", rechargeObj.getCircle())
            .queryParam("recharge_type", rechargeObj.getRecharge_type());

            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            //System.out.println(jsonStr);
            
            //converting json response from API to Response Obj
            MobileRechargeResponse responseObj = new ObjectMapper().readValue(jsonStr, MobileRechargeResponse.class);
           // System.out.println(responseObj);
            return responseObj;
        }
        catch(Exception e)
        {
            return null;
        } 
    }

}

package com.kavki.fastfxrechargeapis.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavki.fastfxrechargeapis.Entity.DthRecharge;
import com.kavki.fastfxrechargeapis.Entity.DthResponse;
import com.kavki.fastfxrechargeapis.Entity.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.MobileResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
	private Environment env;

    private static String baseUrl = "https://sandbox.rechargkit.biz/get/";

    public MobileResponse prepaidRecharge(MobileRecharge rechargeObj){

        try{
            String new_url = baseUrl + "prepaid/mobile";
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
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
           // System.out.println(jsonStr);
            
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
            UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(new_url)
            // Add query parameter to url 
            .queryParam("partner_id", env.getProperty("fastfx.partner_id"))
            .queryParam("api_password",env.getProperty("fastfx.api_password"))
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
            MobileResponse responseObj = new ObjectMapper().readValue(jsonStr, MobileResponse.class);
           // System.out.println(responseObj);
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
            .queryParam("user_val1", rechargeObj.getUser_varl1())
            .queryParam("user_val2", rechargeObj.getUser_varl2());

            //Consuming Recharge API for GET 
            ResponseEntity<String> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                    HttpMethod.GET,
                    null,
                    String.class);
            String jsonStr = responseUser.getBody();
            System.out.println(jsonStr);
            
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

}

package com.kavki.fastfxrechargeapis.Service;

import com.kavki.fastfxrechargeapis.Entity.PrepaidModel;

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

    private static String baseUrl = "https://sandbox.rechargkit.biz/get/prepaid/mobile";

    public PrepaidModel prepaidRecharge(PrepaidModel rechargeObj){

        UriComponentsBuilder uriBuilder  = UriComponentsBuilder.fromUriString(baseUrl)
        // Add query parameter to url 
        .queryParam("partner_id",rechargeObj.getPartner_id())
        .queryParam("api_password",rechargeObj.getApi_password())
        .queryParam("mobile_no", rechargeObj.getMobile_no())
        .queryParam("operator_code", rechargeObj.getOperator_code())
        .queryParam("amount", rechargeObj.getAmount())
        .queryParam("partner_request_id", rechargeObj.getPartner_request_id())
        .queryParam("circle", rechargeObj.getCircle())
        .queryParam("recharge_type", rechargeObj.getRecharge_type());
    
        System.out.println(uriBuilder.toUriString());

        ResponseEntity<PrepaidModel> responseUser = restTemplate.exchange(uriBuilder.toUriString() ,
                HttpMethod.GET,
                null,
                PrepaidModel.class);
        
        PrepaidModel userBody = responseUser.getBody();
      
        System.out.println("user object1 - " + userBody);
        return userBody;
      
    }
}

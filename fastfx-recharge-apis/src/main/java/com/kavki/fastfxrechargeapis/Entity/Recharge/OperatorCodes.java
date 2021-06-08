package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.util.HashMap;

import org.springframework.stereotype.Component;
@Component
public class OperatorCodes {

    public String getOperator(int code){

    HashMap<Integer,String> map = new HashMap<>();
    map.put(1,"Airtel");
    map.put(9,"Bsnl Top Up");
    map.put(10,"Bsnl Special");
    map.put(103,"Jio");
    map.put(1167,"VI");
    map.put(25,"Airtel");
    map.put(26,"Bsnl Postpaid");
    map.put(29,"VI Postpaid");
    map.put(1182,"Zee5");
    
    return map.get(code);
    }


}

package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.util.HashMap;

import org.springframework.stereotype.Component;
@Component
public class OperatorCodes {

    public String getOperator(int code){

    HashMap<Integer,String> map = new HashMap<>();
    //prepaid
    map.put(1,"Airtel");
    map.put(9,"Bsnl Top Up");
    map.put(10,"Bsnl Special");
    map.put(103,"Jio");
    map.put(1167,"VI");
    // dth
    map.put(18,"Airtel Dth");
    map.put(20,"Dish Tv");
    map.put(21,"Sun Tv");
    map.put(22,"Videocon Dth");
    map.put(23,"Tata Sky");
    //postpaid
    map.put(25,"Airtel Postpaid");
    map.put(26,"Bsnl Postpaid");
    map.put(29,"VI Postpaid");
    //ott
    map.put(1182,"Zee5");
    
    
    return map.get(code);
    }


}

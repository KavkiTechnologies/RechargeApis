package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionIdGenerator {

    public String generateTransId(String prefix,int operatorCode, int circleCode){

      //  String prefix = "FX";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String TransId = prefix+date+circleCode+operatorCode;
        return TransId;
    }
    
}

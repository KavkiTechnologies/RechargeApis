package com.kavki.fastfxrechargeapis.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionIdGenerator {

    public String generateTransId(String prefix,int operatorCode, int circleCode){

      //  String prefix = "FX";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        String date = dtf.format(now);
        //String mid = "00";
        String TransId = prefix+date+circleCode+operatorCode;
        return TransId;
    }
    
}

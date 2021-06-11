package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionIdGenerator {

    public String generateTransId(String prefix,int suffix, int mid){

      //  String prefix = "FX";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String TransId = prefix+date+mid+suffix;
        return TransId;
    }
  }

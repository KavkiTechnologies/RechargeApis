package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class TransactionIdGenerator {

    public String generateTransId(String prefix,int suffix, int mid){
      //  String prefix = "FX";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String TransId = prefix+date+mid+suffix;
        return TransId;
    }

    public String getCurrentDateAndTime(){
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      c.add(Calendar.MINUTE,10);
    	String dateString = dateFormat.format(c.getTime());
    	System.out.println("Current date and time in AM/PM: "+dateString);
      return dateString;
    }

    public String generateTransId(String userId){
      //  String prefix = "FX";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String TransId = userId+date;
        return TransId;
    }

  }

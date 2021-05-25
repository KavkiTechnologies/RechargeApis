package com.kavki.fastfxrechargeapis.Entity.Recharge;


import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DateGenerator {
    
    public String getTimeStamp(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String date = dtf.format(now);
    return date;
    }
}

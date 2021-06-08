package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OttPlans {
   // OttPlansResponse plans = new OttPlansResponse();
    private int operator_code;
    private String clientId;
    private String retailerId;
    private String customer_email;
   
    
    public int plan_id;
    public String planCode;
    public String duration;
    public int amount;

}

package com.kavki.fastfxrechargeapis.Entity.Recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OttRecharge {
    
    private int operator_code;
    private int plan_id;
    private long mobile_no;
    private String customer_email;
    private String retailerId;
    private String clientId;
    private int amount;
}

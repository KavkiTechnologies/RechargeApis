package com.kavki.fastfxrechargeapis.Entity.Recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileRecharge {

    private long Mobile_no;
    private int operator_code;
    private int amount;
    private String partner_request_id;
    private int circle;
    private String recharge_type;
    private String clientId;
    private String retailerId;
    private String user_var1;
    private String user_var2;
    private String user_var3;
}

package com.kavki.fastfxrechargeapis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileRechargeModel {

    private String partner_id;
    private String api_password;
    private long Mobile_no;
    private int operator_code;
    private int amount;
    private String partner_request_id;
    private int circle;
    private String recharge_type;
    private String user_varl;
}

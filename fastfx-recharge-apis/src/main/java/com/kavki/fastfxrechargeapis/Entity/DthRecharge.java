package com.kavki.fastfxrechargeapis.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DthRecharge {
    
    private String partner_id;
    private String api_password;
    private long customer_id;
    private int operator_code;
    private int amount;
    private String partner_request_id;
    private String customer_email;
    private String user_varl1;
    private String user_varl2;
}

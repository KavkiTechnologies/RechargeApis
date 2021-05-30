package com.kavki.fastfxrechargeapis.Entity.Recharge;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DthRecharge {
    
   
    private long customer_id;
    private int operator_code;
    private int amount;
    private String partner_request_id;
    private String customer_email;
    private String user_var1;
    private String user_var2;
    private String user_var3;
}

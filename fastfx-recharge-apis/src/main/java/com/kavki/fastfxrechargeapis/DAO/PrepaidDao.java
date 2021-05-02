package com.kavki.fastfxrechargeapis.DAO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="test_prepaid")
public class PrepaidDao {
    
    @Id
    private int orderid;
    @Column(name = "transactionId")
    private String Partner_request_id;
    private long Mobile_no;
    private int operator_code;
    private int amount;
    private int circle;
    private String recharge_type;
   @Column(name = "error_coe")
   private int eRROR;
   @Column(name = "status_code")
   private int status;
   private long otransid;
   private String message;
   private String commission;
   @Column(name ="user_val1")
    private String uSERVAR1;
    @Column(name ="user_val2")
   private String uSERVAR2;
   @Column(name ="user_val3")
   private String uSERVAR3;
	// TODO Auto-generated method stub
	


}

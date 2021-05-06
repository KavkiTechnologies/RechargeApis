package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prepaid_Service")
@Entity(name="prepaid_service")
public class MobileToDbEntity {
    
    @Id
    @Column(name = "Transaction_Id")
    private String Partner_request_id;
    private long Mobile_no;
    private int orderid;
    private int operator_code;
    private int amount;
    private int circle;
    private String recharge_type;
    @Column(name = "error_code")
    private int eRROR;
    @Column(name = "status_code")
    private int status;
    private long optransid;
    private String message;
    private String commission;
    @Column(name ="client_id")
    private String uSERVAR1;
    @Column(name ="user_val2")
    private String uSERVAR2;
    @Column(name ="user_val3")
    private String uSERVAR3;
}
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
    @Column(name="mobile_no")
    private long MobileNo;
    @Column(name="orderid")
    private int orderId;
    @Column(name="operator_code")
    private int operatorCode;
    @Column(name="amount")
    private int amount;
    @Column(name="circle")
    private int circle;
    @Column(name="recharge_type")
    private String rechargeType;
    @Column(name = "error_code")
    private int eRROR;
    @Column(name = "status_code")
    private int status;
    @Column(name="optransid")
    private long optransId;
    @Column(name="message")
    private String message;
    @Column(name="commission")
    private String commission;
    @Column(name ="client_id")
    private String uSERVAR1;
    @Column(name ="user_val2")
    private String uSERVAR2;
    @Column(name ="user_val3")
    private String uSERVAR3;
}

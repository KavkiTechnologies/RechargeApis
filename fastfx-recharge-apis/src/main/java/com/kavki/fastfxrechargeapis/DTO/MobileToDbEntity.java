package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recharge_service")
@Entity(name="recharge_service")
@NamedStoredProcedureQuery(
    name = "transactionsP",
    procedureName = "transactionsP",
    parameters = {@StoredProcedureParameter(mode=ParameterMode.IN, name = "transactionId",type = String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="clientId",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="mobileNo",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="operatorCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="amount",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="circle",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="rechargeType",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="serviceType",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="errorCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="statusCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="orderId",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="optransId",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="message",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="transDate",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="commission",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="userVal2",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="userVal3",type= String.class)})

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
    @Column(name="service_type")
    private String serviceType;
    @Column(name = "error_code")
    private int eRROR;
    @Column(name = "status_code")
    private int status;
    @Column(name="optransid")
    private long optransId;
    @Column(name="message")
    private String message;
    @Column(name="tranx_date")
    private String transDate;
    @Column(name="commission")
    private String commission;
    @Column(name ="client_id")
    private String uSERVAR1;
    @Column(name ="user_val2")
    private String uSERVAR2;
    @Column(name ="user_val3")
    private String uSERVAR3;
}

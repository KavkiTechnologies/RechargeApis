package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.*;

import com.kavki.fastfxrechargeapis.Entity.Recharge.DthRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.OttRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.RkitApiResponse;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recharge_service")
@Entity(name="recharge_service")
@NamedStoredProcedureQuery(
    name = "transactionP",
    procedureName = "transactionP",
    parameters = {@StoredProcedureParameter(mode=ParameterMode.IN, name = "transactionId",type = String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="clientId",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="rechargeNumber",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="operatorCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="operatorName",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="amount",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="circle",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="rechargeType",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="serviceProvider",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="serviceType",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="errorCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="statusCode",type= Integer.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="orderId",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="optransId",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="message",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="transDate",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="commission",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="retailerId",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="charge",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="refund",type= String.class)
                })
                 
@Component
public class MapToDbEntity {
    
    @Id
    @Column(name = "Transaction_Id")
    private String Partner_request_id;
    @Column(name="recharge_number")
    private long Number;
    @Column(name="orderid")
    private long orderId;
    @Column(name="operator_code")
    private int operatorCode;
    @Column(name="operator_name")
    private String operatorName;
    @Column(name="amount")
    private int amount;
    @Column(name="circle")
    private int circle;
    @Column(name="recharge_type")
    private String rechargeType;
    @Column(name="service_provider")
    private String serviceProvider;
    @Column(name="service_type")
    private String serviceType;
    @Column(name = "error_code")
    private int eRROR;
    @Column(name = "status_code")
    private int status;
    @Column(name="optransid")
    private String optransId;
    @Column(name="message")
    private String message;
    @Column(name="tranx_date")
    private String transDate;
    @Column(name="commission")
    private String commission;
    @Column(name ="client_id")
    private String clientId;
    @Column(name ="retailer_Id")
    private String retailerId;
    @Column(name = "charge")
    private String charge;
    @Column(name = "refund")
    private String refund;
  
    // Mapping rechargeParameters & Rkit Response Parameters into one to be passed to the Transaction Stored Procedure
    public MapToDbEntity mapMobileToDbEntity(MobileRecharge requestParams, RkitApiResponse responseParams){
            MapToDbEntity dbEntity = new MapToDbEntity();
            dbEntity.setPartner_request_id(responseParams.getPARTNERREQID());
            dbEntity.setNumber(requestParams.getMobile_no());
            dbEntity.setOperatorCode(requestParams.getOperator_code());
            dbEntity.setAmount(requestParams.getAmount());
            dbEntity.setCircle(requestParams.getCircle());
            dbEntity.setRechargeType(requestParams.getRecharge_type());
            dbEntity.setERROR(responseParams.getERROR());
            dbEntity.setStatus(responseParams.getSTATUS());
            dbEntity.setOrderId(responseParams.getORDERID());
            dbEntity.setOptransId(responseParams.getOPTRANSID());
            dbEntity.setMessage(responseParams.getMESSAGE());
            dbEntity.setCommission(responseParams.getCOMMISSION());
            dbEntity.setClientId(requestParams.getClientId());
            dbEntity.setRetailerId(requestParams.getRetailerId());
            dbEntity.setCharge(responseParams.getCHARGE());
            return dbEntity;
    }

    public MapToDbEntity mapDthToDbEntity(DthRecharge requestParams, RkitApiResponse responseParams){
        MapToDbEntity dbEntity = new MapToDbEntity();
        dbEntity.setPartner_request_id(responseParams.getPARTNERREQID());
        dbEntity.setNumber(Integer.parseInt(requestParams.getCustomer_id()));
        dbEntity.setOperatorCode(requestParams.getOperator_code());
        dbEntity.setAmount(requestParams.getAmount());
        dbEntity.setCircle(0);  // dth has no circle code so zero
        dbEntity.setRechargeType("Dth Normal");
        dbEntity.setERROR(responseParams.getERROR());
        dbEntity.setStatus(responseParams.getSTATUS());
        dbEntity.setOrderId(responseParams.getORDERID());
        dbEntity.setOptransId(responseParams.getOPTRANSID());
        dbEntity.setMessage(responseParams.getMESSAGE());
        dbEntity.setCommission(responseParams.getCOMMISSION());
        dbEntity.setClientId(requestParams.getClientId());
        dbEntity.setRetailerId(requestParams.getRetailerId());
        dbEntity.setCharge(responseParams.getCHARGE());
        return dbEntity;
    }

    public MapToDbEntity mapOttToDbEntity(OttRecharge requestParams, RkitApiResponse responseParams){
        MapToDbEntity dbEntity = new MapToDbEntity();
        dbEntity.setPartner_request_id(responseParams.getPARTNERREQID());
        dbEntity.setNumber(requestParams.getMobile_no());
        dbEntity.setOperatorCode(requestParams.getOperator_code());
        dbEntity.setAmount(requestParams.getAmount());
        dbEntity.setCircle(0); 
        dbEntity.setRechargeType("OTT Normal");
        dbEntity.setERROR(responseParams.getERROR());
        dbEntity.setStatus(responseParams.getSTATUS());
        dbEntity.setOrderId(responseParams.getORDERID());
        dbEntity.setOptransId(responseParams.getOPTRANSID());
        dbEntity.setMessage(responseParams.getMESSAGE());
        dbEntity.setCommission(responseParams.getCOMMISSION());
        dbEntity.setClientId(requestParams.getClientId());
        dbEntity.setRetailerId(requestParams.getRetailerId());
        dbEntity.setCharge(responseParams.getCHARGE());
        return dbEntity;
    }
}

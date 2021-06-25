package com.kavki.fastfxrechargeapis.Entity.Admin;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_details")
@Entity(name= "transaction_details")
public class TransactionEntity {

    @Id
    @Column(name="S_no")
    private int Sno;
    @Column(name= "Transaction_Id", nullable = false)
    private String TransactionId;
    @Column(name= "Client_Id", nullable = false)
    private String ClientId;
    @Column(name="Retailer_Id")
    private String RetailerId;
    @Column(name= "Operator_Name", nullable = false)
    private String OperatorName;
    @Column(name="Service_Type", nullable=false)
    private String ServiceType;
    @Column(name="Service_Provider", nullable=false)
    private String ServiceProvider;
    @Column(name= "Tranx_date", nullable = false)
    private String TranxDate;
    @Column(name= "Updated_Tranx_date", nullable = false)
    private String UpdatedTranxDate;
    @Column(name= "Description", nullable = false)
    private String Description;
    @Column(name= "Amount", nullable = false)
    private int Amount;
    @Column(name= "Orderid", nullable = false)
    private String OrderId;
    @Column(name= "Reference_No", nullable = false)
    private String ReferenceNo;
    @Column(name="Fastfx_Commission",nullable=false)
    private String Commission;
    @Column(name="Charge", nullable = false)
    private String Charge;
    @Column(name="Refund", nullable = false)
    private String Refund;
}

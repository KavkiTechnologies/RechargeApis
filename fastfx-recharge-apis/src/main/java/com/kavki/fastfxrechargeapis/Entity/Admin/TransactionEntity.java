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
    @Column(name= "Client_Name", nullable = false)
    private String Client;
    @Column(name= "Client_ID", nullable = false)
    private String ClientId;
    @Column(name="Retailer_Id")
    private String RetailerId;
    @Column(name= "Operator_Name", nullable = false)
    private String OperatorName;
    @Column(name="Service_Type", nullable=false)
    private String ServiceType;
    @Column(name= "Tranx_date", nullable = false)
    private String TranxDate;
    @Column(name= "Updated_Tranx_date", nullable = false)
    private String UpdatedTranxDate;
    @Column(name= "Description", nullable = false)
    private String Description;
    @Column(name= "Amount", nullable = false)
    private int Amount;
    @Column(name= "Reference_No", nullable = false)
    private String ReferenceNo;
    @Column(name="Fastfx_Commission",nullable=false,precision = 5,scale =  4)
    private float Commission;
}

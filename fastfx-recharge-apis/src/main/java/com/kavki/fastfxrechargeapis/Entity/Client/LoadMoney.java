package com.kavki.fastfxrechargeapis.Entity.Client;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "load_money")
@Table(name = "load_money")
public class LoadMoney {
    
    @Id
    @GeneratedValue
    @Column(name= "Sl_No")
    private int slno;
    @Column(name="Client_Id")
    private String clientId;
    @Column(name="Retailer_Id")
    private  String retailerId;
    @Column(name="Request_Date")
    private String reqDate;
    @Column(name= "Amount")
    private String amount;
    @Column(name= "Type")
    private String type;
    @Column(name= "Client_Bank")
    private String fromBank;
    @Column(name= "Client_Account")
    private String fromAccount;
    @Column(name= "Reference")
    private String reference;
    @Column(name= "Bank")
    private String toBank;
    @Column(name= "Bank_Number")
    private String toAccount;
    @Column(name= "Remarks")
    private String remarks;
    @Column(name="Status")
    private String status;
}

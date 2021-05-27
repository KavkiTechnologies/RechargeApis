package com.kavki.fastfxrechargeapis.Entity.Admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client_records")
@Entity(name="request_prefund")
public class PrefundEntity {
    
    @Id
    @Column(name="S_No")
    private int SL_No;
    @Column(name = "Mobile_No")
    private long MobileNo;
    @Column(name = "Client_Name")
    private String Name;
    @Column(name = "Account_Balance")
    private float Balance;
    @Column(name = "Status")
    private String Status;
    @Column(name = "Client_Id")
    private String ClientId;
}
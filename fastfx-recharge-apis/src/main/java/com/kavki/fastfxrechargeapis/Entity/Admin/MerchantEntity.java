package com.kavki.fastfxrechargeapis.Entity.Admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="merchant_list")
public class MerchantEntity {
    
    
    @Id
    @Column(name="Merchant_Name",nullable =  false)
    private String Merchant;
    @Column(name="S_No",nullable =  false)
    private int SL_No;
    @Column(name = "Available_Balance", nullable =  false, precision = 10, scale = 4)
    private float Balance;
    @Column(name = "Total_Service_Type", nullable =  false)
    private int Total_ServiceType;
    @Column(name="Status", nullable =  false)
    private String Status;

}
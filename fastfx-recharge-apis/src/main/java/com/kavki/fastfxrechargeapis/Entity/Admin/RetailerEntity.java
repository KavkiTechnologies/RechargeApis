package com.kavki.fastfxrechargeapis.Entity.Admin;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name="retailer_records")
@Entity(name="retailer_records")
public class RetailerEntity {
       
    
    @Id
    @Column(name = "Retailer_Id")
    private String RetailerId;
    @Column(name="S_No")
    private int SL_No;
    @Column(name="Client_Id")
    private String ClientId;
    @Column(name = "Mobile_No")
    private long MobileNo;
    @Column(name="Email_Id")
    private String EmailId;
    @Column(name = "Retailer_Name")
    private String Name;
    @Column(name = "Retailer_Address")
    private String Address;
    @Column(name = "Retailer_Country")
    private String Country;
    @Column(name = "Account_Balance")
    private float Balance;
    @Column(name = "Status")
    private String Status;
}
   
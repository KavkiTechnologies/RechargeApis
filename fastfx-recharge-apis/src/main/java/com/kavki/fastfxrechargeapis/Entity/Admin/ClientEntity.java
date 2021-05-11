package com.kavki.fastfxrechargeapis.Entity.Admin;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client_records")
@Entity(name="client_records")
public class ClientEntity {
    
    
    @Id
    @Column(name="S_No")
    private int SL_No;
    @Column(name = "Mobile_No")
    private long MobileNo;
    @Column(name="Email_Id")
    private String EmailId;
    @Column(name = "Client_Name")
    private String Name;
    @Column(name = "Client_Address")
    private String Address;
    @Column(name = "Client_Country")
    private String Country;
    @Column(name = "Account_Balance")
    private float Balance;
    @Column(name = "Status")
    private String Status;
    @Column(name = "Client_Id")
    private String ClientId;
}

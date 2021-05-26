package com.kavki.fastfxrechargeapis.Entity.Retailer;

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
@Table(name="retailer_records")
@Entity(name="retailer_login")
public class RetailerCredentials {
    
    @Id
    @Column(name="Email_Id")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="p_salt")
    private String salt;
    @Column(name="Retailer_Id")
    private String retailerId;
    @Column(name="Client_Id")
    private String clientId;
    
}

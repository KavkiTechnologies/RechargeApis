package com.kavki.fastfxrechargeapis.Entity.Client;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client_records")
@Entity(name="client_login")
public class ClientCredentials {

    @Id
    @Column(name="Email_Id")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="p_salt")
    private String salt;
    @Column(name="client_id")
    private String clientId;
    
}

package com.kavki.fastfxrechargeapis.Entity.Admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="admin_credentials")
@Table(name="admin_credentials")
public class AdminCredentials {
    
    @Id
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="p_salt")
    private String salt;
}

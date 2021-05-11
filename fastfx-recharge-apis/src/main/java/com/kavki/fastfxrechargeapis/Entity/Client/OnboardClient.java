package com.kavki.fastfxrechargeapis.Entity.Client;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client_records")
@Entity(name="client_onboard")
@NamedStoredProcedureQuery(
    name = "onboardClientP",
    procedureName = "onboardClientP",
    parameters = {@StoredProcedureParameter(mode=ParameterMode.IN, name = "email",type = String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name= "password",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name= "salt",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="mobile",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="name",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="address",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="country",type= String.class)})
                  
public class OnboardClient {

    @Id
    @Column(name="Email_Id ")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="p_salt")
    private String salt;
    @Column(name="Mobile_No")
    private long mobile;
    @Column(name="Client_Name")
    private String name;
    @Column(name="Client_Address")
    private String address;
    @Column(name="Client_Country")
    private String country;
}

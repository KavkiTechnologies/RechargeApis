package com.kavki.fastfxrechargeapis.Entity.Retailer;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="retailer_records")
@Entity(name="retailer_onboard")
@NamedStoredProcedureQuery(
    name = "onboardRetailerP",
    procedureName = "onboardRetailerP",
    parameters = {@StoredProcedureParameter(mode=ParameterMode.IN, name = "email",type = String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name= "password",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name= "salt",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="mobile",type= Long.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="name",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="address",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="country",type= String.class),
                  @StoredProcedureParameter(mode=ParameterMode.IN,name="clientId",type= String.class)})
                  
public class OnboardRetailer {

    @Id
    @Column(name="Email_Id ")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="p_salt")
    private String salt;
    @Column(name="Mobile_No")
    private long mobile;
    @Column(name="Retailer_Name")
    private String name;
    @Column(name="Retailer_Address")
    private String address;
    @Column(name="Retailer_Country")
    private String country;
    @Column(name="Client_Id")
    private String clientId;
    @Column(name="Retailer_Id")
    private String retailerId;
}

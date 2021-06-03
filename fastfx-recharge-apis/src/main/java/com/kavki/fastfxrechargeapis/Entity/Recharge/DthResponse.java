package com.kavki.fastfxrechargeapis.Entity.Recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DthResponse {
    
    @JsonProperty("ERROR") 
    public int eRROR;
    @JsonProperty("STATUS") 
    public int sTATUS;
    @JsonProperty("ORDERID") 
    public String oRDERID;
    @JsonProperty("OPTRANSID") 
    public String oPTRANSID;
    @JsonProperty("PARTNERREQID") 
    public String pARTNERREQID;
    @JsonProperty("MESSAGE") 
    public String mESSAGE;
    @JsonProperty("USERVAR1") 
    public String uSERVAR1;
    @JsonProperty("USERVAR2") 
    public String uSERVAR2;
    @JsonProperty("USERVAR3") 
    public String uSERVAR3;
    
}

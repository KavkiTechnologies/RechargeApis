package com.kavki.fastfxrechargeapis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrepaidResponse {
    
    @JsonProperty("ERROR") 
    private int eRROR;
    @JsonProperty("STATUS") 
    private int sTATUS;
    @JsonProperty("ORDERID") 
    private int oRDERID;
    @JsonProperty("OPTRANSID") 
    private int oPTRANSID;
    @JsonProperty("PARTNERREQID") 
    private String pARTNERREQID;
    @JsonProperty("MESSAGE") 
    private String mESSAGE;
    @JsonProperty("USERVAR1") 
    private String uSERVAR1;
    @JsonProperty("USERVAR2") 
    private String uSERVAR2;
    @JsonProperty("USERVAR3") 
    private String uSERVAR3;
    @JsonProperty("COMMISSION") 
    private String cOMMISSION;

}

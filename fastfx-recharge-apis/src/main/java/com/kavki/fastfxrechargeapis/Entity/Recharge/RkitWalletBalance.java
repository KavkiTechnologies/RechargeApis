package com.kavki.fastfxrechargeapis.Entity.Recharge;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RkitWalletBalance {

    @JsonProperty("ERROR") 
    public int eRROR;
    @JsonProperty("MESSAGE") 
    public String mESSAGE;
    @JsonProperty("USERVAR1") 
    public String uSERVAR1;
    @JsonProperty("USERVAR2") 
    public String uSERVAR2;
    @JsonProperty("USERVAR3") 
    public String uSERVAR3;
    @JsonProperty("WALLET_BALANCE") 
    public String wALLET_BALANCE;
    @JsonProperty("DMR_BALANCE") 
    public String dMR_BALANCE;
   
}

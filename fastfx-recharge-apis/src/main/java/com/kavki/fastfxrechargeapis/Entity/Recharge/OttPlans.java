package com.kavki.fastfxrechargeapis.Entity.Recharge;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OttPlans {
  //  @JsonProperty("ERROR") 
    public int eRROR;
  //  @JsonProperty("SUCCESS") 
    public int sUCCESS;
  //  @JsonProperty("STATUS") 
    public int sTATUS;
  //  @JsonProperty("DATA") 
    public List<OttPlansData> dATA;
  //  @JsonProperty("MESSAGE") 
    public String mESSAGE;
}

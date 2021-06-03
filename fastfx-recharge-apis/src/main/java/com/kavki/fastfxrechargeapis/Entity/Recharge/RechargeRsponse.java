package com.kavki.fastfxrechargeapis.Entity.Recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RechargeRsponse {
     
    private int error; 
    private int status;
    private int orderid;
    private long referenceid; 
    private String transactionid; 
    private String message;
    private String uservar1; 
    private String uservar2; 
    private String uservar3;

    public RechargeRsponse mapRkitResponseToCustomResponse(RkitApiResponse rkitResponseParams){
        RechargeRsponse response = new RechargeRsponse();
        
        response.setError(rkitResponseParams.getERROR());
        response.setStatus(rkitResponseParams.getSTATUS());
        response.setOrderid(rkitResponseParams.getORDERID());
        response.setReferenceid(rkitResponseParams.getOPTRANSID());
        response.setTransactionid(rkitResponseParams.getPARTNERREQID());
        response.setMessage(rkitResponseParams.getMESSAGE());
        response.setUservar1(rkitResponseParams.getUSERVAR1());
        response.setUservar2(rkitResponseParams.getUSERVAR2());
        response.setUservar3(rkitResponseParams.getUSERVAR3());
        return response;
    }
}

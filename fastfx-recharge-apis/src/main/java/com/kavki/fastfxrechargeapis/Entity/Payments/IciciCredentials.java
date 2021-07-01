package com.kavki.fastfxrechargeapis.Entity.Payments;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name="icici")
public class IciciCredentials {
    
   // private long merchantId;
    //private String merchantName;
    private long subMerchantId;
    private String subMerchantName;
    private int terminalId;
    private String merchantTranId;
    private String billNumber;
    private String userId;
    private double amount;
    private String note;
    private String collectByDate;

    private String originalmerchantTranId;
    private String originalBankRRN;
    private String refundAmount;
}

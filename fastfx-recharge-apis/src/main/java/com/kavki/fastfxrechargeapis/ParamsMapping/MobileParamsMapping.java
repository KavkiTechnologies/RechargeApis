package com.kavki.fastfxrechargeapis.ParamsMapping;

import com.kavki.fastfxrechargeapis.DTO.MobileToDbEntity;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.Recharge.MobileResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class MobileParamsMapping {
    
    @Autowired
    private MobileToDbEntity mobiledbEntity;

    public MobileToDbEntity mobileEntityMappingForDb(MobileRecharge rechargeParams, MobileResponse responseParams){
    	
        mobiledbEntity = new MobileToDbEntity();

        mobiledbEntity.setPartner_request_id(responseParams.getPARTNERREQID());
        mobiledbEntity.setMobileNo(rechargeParams.getMobile_no());
        mobiledbEntity.setOperatorCode(rechargeParams.getOperator_code());
        mobiledbEntity.setAmount(rechargeParams.getAmount());
        mobiledbEntity.setCircle(rechargeParams.getCircle());
        mobiledbEntity.setRechargeType(rechargeParams.getRecharge_type());
        mobiledbEntity.setERROR(responseParams.getERROR());
        mobiledbEntity.setStatus(responseParams.getSTATUS());
        mobiledbEntity.setOrderId(responseParams.getORDERID());
        mobiledbEntity.setOptransId(responseParams.getOPTRANSID());
        mobiledbEntity.setMessage(responseParams.getMESSAGE());
        mobiledbEntity.setCommission(responseParams.getCOMMISSION());
        mobiledbEntity.setClientId(rechargeParams.getClientId());
        mobiledbEntity.setRetailerId(rechargeParams.getRetailerId());
        return mobiledbEntity;
    }

}

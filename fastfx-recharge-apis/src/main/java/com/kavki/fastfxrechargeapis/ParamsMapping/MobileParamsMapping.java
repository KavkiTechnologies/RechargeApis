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
        mobiledbEntity.setMobile_no(rechargeParams.getMobile_no());
        mobiledbEntity.setOperator_code(rechargeParams.getOperator_code());
        mobiledbEntity.setAmount(rechargeParams.getAmount());
        mobiledbEntity.setCircle(rechargeParams.getCircle());
        mobiledbEntity.setRecharge_type(rechargeParams.getRecharge_type());
        mobiledbEntity.setERROR(responseParams.getERROR());
        mobiledbEntity.setStatus(responseParams.getSTATUS());
        mobiledbEntity.setOrderid(responseParams.getORDERID());
        mobiledbEntity.setOptransid(responseParams.getOPTRANSID());
        mobiledbEntity.setMessage(responseParams.getMESSAGE());
        mobiledbEntity.setCommission(responseParams.getCOMMISSION());
        mobiledbEntity.setUSERVAR1(responseParams.getUSERVAR1());
        mobiledbEntity.setUSERVAR2(responseParams.getUSERVAR2());
        mobiledbEntity.setUSERVAR3(responseParams.getUSERVAR3());

        return mobiledbEntity;
    }

}
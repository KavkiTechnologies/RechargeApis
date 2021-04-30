package com.kavki.fastfxrechargeapis.ObjectMapperRepository;

import com.kavki.fastfxrechargeapis.DAO.PrepaidDao;
import com.kavki.fastfxrechargeapis.Entity.MobileRecharge;
import com.kavki.fastfxrechargeapis.Entity.MobileResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class PrepaidMapping {
    
    @Autowired
    private PrepaidDao prepaidDao;

    public PrepaidDao prepaidFieldMappingForDb(MobileRecharge rechargeParams, MobileResponse responseParams){
       
        prepaidDao = new PrepaidDao();
       
        prepaidDao.setPartner_request_id(rechargeParams.getPartner_request_id());
        prepaidDao.setMobile_no(rechargeParams.getMobile_no());
        prepaidDao.setOperator_code(rechargeParams.getOperator_code());
        prepaidDao.setAmount(rechargeParams.getAmount());
        prepaidDao.setCircle(rechargeParams.getCircle());
        prepaidDao.setRecharge_type(rechargeParams.getRecharge_type());
        prepaidDao.setERROR(responseParams.getERROR());
        prepaidDao.setStatus(responseParams.getSTATUS());
        prepaidDao.setOrderid(responseParams.getORDERID());
        prepaidDao.setOtransid(responseParams.getOPTRANSID());
        prepaidDao.setMessage(responseParams.getMESSAGE());
        prepaidDao.setCommission(responseParams.getCOMMISSION());
        prepaidDao.setUSERVAR1(rechargeParams.getUser_varl());
        prepaidDao.setUSERVAR1(responseParams.getUSERVAR2());
        prepaidDao.setUSERVAR1(responseParams.getUSERVAR3());

       //System.out.println("DAO2- "+ prepaidDao);
        return prepaidDao;
    }
}

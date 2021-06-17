package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TransactionProcedure {
    
    
    @Autowired
    private EntityManager em;
    
    public void callTransactionProcedure(MapToDbEntity db)
    {
        em.createNamedStoredProcedureQuery("transactionP").setParameter("transactionId", db.getPartner_request_id())
                                                    .setParameter("clientId",db.getClientId())
                                                    .setParameter("rechargeNumber",db.getNumber()) // mobile or vc Number
                                                    .setParameter("operatorCode",db.getOperatorCode())
                                                    .setParameter("operatorName",db.getOperatorName())
                                                    .setParameter("amount",db.getAmount())
                                                    .setParameter("circle", db.getCircle())
                                                    .setParameter("rechargeType", db.getRechargeType())
                                                    .setParameter("serviceProvider", db.getServiceProvider())
                                                    .setParameter("serviceType", db.getServiceType())
                                                    .setParameter("errorCode", db.getERROR())
                                                    .setParameter("statusCode", db.getStatus())
                                                    .setParameter("orderId", db.getOrderId())
                                                    .setParameter("optransId", db.getOptransId())
                                                    .setParameter("message", db.getMessage())
                                                    .setParameter("transDate",db.getTransDate())
                                                    .setParameter("commission", db.getCommission())
                                                    .setParameter("retailerId", db.getRetailerId())
                                                    .setParameter("charge", db.getCharge())
                                                    .setParameter("refund", db.getRefund()).execute();
    }
  
}

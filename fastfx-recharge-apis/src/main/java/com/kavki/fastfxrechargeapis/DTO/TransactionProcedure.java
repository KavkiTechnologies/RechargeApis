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
        em.createNamedStoredProcedureQuery("transactionsP").setParameter("transactionId", db.getPartner_request_id())
                                                    .setParameter("clientId",db.getClientId())
                                                    .setParameter("recharge_number",db.getNumber()) // mobile or vc Number
                                                    .setParameter("operatorCode",db.getOperatorCode())
                                                    .setParameter("amount",db.getAmount())
                                                    .setParameter("circle", db.getCircle())
                                                    .setParameter("rechargeType", db.getRechargeType())
                                                    .setParameter("serviceType", db.getServiceType())
                                                    .setParameter("errorCode", db.getERROR())
                                                    .setParameter("statusCode", db.getStatus())
                                                    .setParameter("orderId", db.getOrderId())
                                                    .setParameter("optransId", db.getOptransId())
                                                    .setParameter("message", db.getMessage())
                                                    .setParameter("transDate",db.getTransDate())
                                                    .setParameter("commission", db.getCommission())
                                                    .setParameter("retailerId", db.getRetailerId())
                                                    .setParameter("charge", db.getCharge()).execute();
    }
  
}

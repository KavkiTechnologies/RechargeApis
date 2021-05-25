package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.EntityManager;
import com.kavki.fastfxrechargeapis.Entity.Retailer.OnboardRetailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OnboardRetailerProcedure {
    
    @Autowired
    private EntityManager em;
    
    public void callOnboadRetailerProcedure(OnboardRetailer details){

        em.createNamedStoredProcedureQuery("onboardRetailerP").setParameter("email", details.getEmail())
        .setParameter("password", details.getPassword())
        .setParameter("salt", details.getSalt())
        .setParameter("mobile", details.getMobile())
        .setParameter("name", details.getName())
        .setParameter("address", details.getAddress())
        .setParameter("country", details.getCountry())
        .setParameter("clientId", details.getClientId()).execute();

    }
}

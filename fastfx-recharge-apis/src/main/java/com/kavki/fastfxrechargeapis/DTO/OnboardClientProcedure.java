package com.kavki.fastfxrechargeapis.DTO;

import javax.persistence.EntityManager;

import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OnboardClientProcedure {
    
    @Autowired
    private EntityManager em;
    
    public void callOnboadClientProcedure(OnboardClient details){

        em.createNamedStoredProcedureQuery("onboardClientP").setParameter("email", details.getEmail())
        .setParameter("password", details.getPassword())
        .setParameter("salt", details.getSalt())
        .setParameter("mobile", details.getMobile())
        .setParameter("name", details.getName())
        .setParameter("address", details.getAddress())
        .setParameter("country", details.getCountry()).execute();

    }
}

package com.kavki.fastfxrechargeapis.DAO.RechargeRepositories;

import com.kavki.fastfxrechargeapis.Entity.Retailer.RetailerCredentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerLoginRepo extends JpaRepository<RetailerCredentials, String> {

    RetailerCredentials findByEmail(String email);
    
}

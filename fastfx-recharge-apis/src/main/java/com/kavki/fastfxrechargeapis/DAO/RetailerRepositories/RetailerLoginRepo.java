package com.kavki.fastfxrechargeapis.DAO.RetailerRepositories;
import com.kavki.fastfxrechargeapis.Entity.Retailer.RetailerCredentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerLoginRepo extends JpaRepository<RetailerCredentials, String> {

    RetailerCredentials findByEmail(String email);

   @Query(value="Select client_Id from retailer_records where retailer_id =:retailerId", nativeQuery = true)
    String findByRetailerId(String retailerId);
    
}

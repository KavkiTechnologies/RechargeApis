package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import java.util.List;

import com.kavki.fastfxrechargeapis.Entity.Admin.RetailerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface RetailerListRepo extends JpaRepository<RetailerEntity, String>{

    @Query("Select r.Balance from retailer_records r where r.RetailerId =:rId")
    String getWalletBalance(@Param("rId") String retailerId);

    @Query(value="Select * from retailer_records where Client_Id =:clientId",nativeQuery = true)
    List<RetailerEntity> findByClientId( String clientId);
    
}

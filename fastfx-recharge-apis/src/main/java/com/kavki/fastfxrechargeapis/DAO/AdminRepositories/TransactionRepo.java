package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<TransactionEntity, String>{

    @Query(value="select count(*) from transaction_details where Description='Success' ",nativeQuery = true)
    int calcSuccess();

    @Query(value="select count(*) from transaction_details where Description='Failed' ",nativeQuery = true)
    int calcFailed();
    
    @Query(value="select count(*) from transaction_details where Description='Pending' ",nativeQuery = true)
    int calcPending();
}

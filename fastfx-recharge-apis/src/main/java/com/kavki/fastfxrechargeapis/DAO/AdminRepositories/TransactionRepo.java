package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import java.util.List;

import com.kavki.fastfxrechargeapis.Entity.Admin.TransactionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<TransactionEntity, String>{

    @Query(value="select count(*) from transaction_details where Description='Success' ",nativeQuery = true)
    int calcSuccess();

    @Query(value="select count(*) from transaction_details where Description='Failed' ",nativeQuery = true)
    int calcFailed();
    
    @Query(value="select count(*) from transaction_details where Description='Pending' ",nativeQuery = true)
    int calcPending();

    @Query("select t from transaction_details t where t.ClientId =:cId")
    List<TransactionEntity> findByClientId(@Param("cId") String clientId);

    @Query("select t from transaction_details t where t.RetailerId =:rId")
    List<TransactionEntity> findByRetailerId(@Param("rId") String retailerId);
    

    
}


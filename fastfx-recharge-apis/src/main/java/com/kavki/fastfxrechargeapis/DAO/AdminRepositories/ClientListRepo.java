package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.ClientEntity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientListRepo extends JpaRepository<ClientEntity, String>{

    @Query("Select r.Balance from client_records r where r.ClientId =:cId")
    String getWalletBalance(@Param("cId") String clientId);
    
}
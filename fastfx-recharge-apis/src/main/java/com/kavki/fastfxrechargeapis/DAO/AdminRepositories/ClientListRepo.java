package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.kavki.fastfxrechargeapis.Entity.Admin.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  ClientListRepo extends JpaRepository<ClientEntity, String>{


    @Query("Select r.Balance from client_records r where r.ClientId =:cId")
    String getWalletBalance(@Param("cId") String clientId);

    @Query(value="select sum(c.Account_balance) from client_records c",nativeQuery = true)
    Double calcTotalClientPrefund();

    @Transactional
    @Modifying
    @Query("update client_records c set c.Balance =:balance where c.ClientId =:cid")
    void updateBalance(@Param(value="cid") String clientId, @Param(value="balance") Float updatedBalance);

    
}
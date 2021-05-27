package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.PrefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PrefundRepo extends JpaRepository<PrefundEntity, Integer>{

    @Query(value="select sum(c.Account_balance) from client_records c",nativeQuery = true)
    Double calcTotal();
}
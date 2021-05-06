package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.PrefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrefundRepo extends JpaRepository<PrefundEntity, Long>{

    @Query(value="select sum(c.Account_balance) from client_records c",nativeQuery = true)
    Double calcTotal();
}
package com.kavki.fastfxrechargeapis.DAO.ClientRepositories;

import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoadMoneyRepo extends JpaRepository<LoadMoney, Integer> {
    
}

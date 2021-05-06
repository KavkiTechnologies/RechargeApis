package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientListRepo extends JpaRepository<ClientEntity, Long>{

    
}
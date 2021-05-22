package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientListRepo extends JpaRepository<ClientEntity, String>{
    
}
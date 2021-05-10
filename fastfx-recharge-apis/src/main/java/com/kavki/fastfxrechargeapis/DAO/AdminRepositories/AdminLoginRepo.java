package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.AdminCredentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminCredentials,String>{

    AdminCredentials findByEmail(String email);
    
}

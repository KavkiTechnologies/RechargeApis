package com.kavki.fastfxrechargeapis.DAO.ClientRepositories;

import com.kavki.fastfxrechargeapis.Entity.Client.ClientCredentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLoginRepo extends JpaRepository<ClientCredentials, String> {

    ClientCredentials findByEmail(String email);
    
}

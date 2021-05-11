package com.kavki.fastfxrechargeapis.DAO.ClientRepositories;

import com.kavki.fastfxrechargeapis.Entity.Client.OnboardClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOnboardRepo extends JpaRepository<OnboardClient, Integer> {

    OnboardClient findByEmail(String email);
    
}

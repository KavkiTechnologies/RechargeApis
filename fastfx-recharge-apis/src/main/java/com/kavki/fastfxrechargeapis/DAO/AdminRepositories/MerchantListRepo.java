package com.kavki.fastfxrechargeapis.DAO.AdminRepositories;

import com.kavki.fastfxrechargeapis.Entity.Admin.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantListRepo extends JpaRepository<MerchantEntity, String> {
    
}

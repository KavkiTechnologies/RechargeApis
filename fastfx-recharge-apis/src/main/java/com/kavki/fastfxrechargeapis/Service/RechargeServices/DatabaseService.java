package com.kavki.fastfxrechargeapis.Service.RechargeServices;

import com.kavki.fastfxrechargeapis.DAO.RechargeRepositories.MobileRechargeRepo;
import com.kavki.fastfxrechargeapis.DTO.MobileToDbEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    
    @Autowired
    private MobileRechargeRepo prepaidRepo;

    public void saveTransaction(MobileToDbEntity prepaidParams){
        prepaidRepo.save(prepaidParams);
        
    }
   
}

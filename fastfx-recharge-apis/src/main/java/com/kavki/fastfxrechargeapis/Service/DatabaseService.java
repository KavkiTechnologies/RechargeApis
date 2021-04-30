package com.kavki.fastfxrechargeapis.Service;

import com.kavki.fastfxrechargeapis.DAO.PrepaidDao;
import com.kavki.fastfxrechargeapis.DTO.PrepaidRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    
    @Autowired
    private PrepaidRepo prepaidRepo;

    public void saveTransaction(PrepaidDao prepaidParams){
        prepaidRepo.save(prepaidParams);
    }
   
}

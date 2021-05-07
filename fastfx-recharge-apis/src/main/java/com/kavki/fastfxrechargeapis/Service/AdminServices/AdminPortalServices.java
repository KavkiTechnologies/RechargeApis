package com.kavki.fastfxrechargeapis.Service.AdminServices;

import java.util.List;
import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.*;
import com.kavki.fastfxrechargeapis.Entity.Admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPortalServices {
   
    @Autowired
    private MerchantListRepo mListRepo;
    @Autowired
    private ClientListRepo cListRepo;
    @Autowired
    private PrefundRepo prefundRepo;
    @Autowired
    private TransactionRepo tListRepo;

    public List<MerchantEntity> getMRecords() {
        return mListRepo.findAll();
    }

    public List<TransactionEntity> getTRecords() {
        return tListRepo.findAll();
    }

    public List<ClientEntity> getCRecords() {
        return cListRepo.findAll();
    }

    public List<PrefundEntity> getPrefund(){
        return prefundRepo.findAll();
    }

    public Double getBalance() {
        return prefundRepo.calcTotal();
    }

    public int getSuccess(){
        return tListRepo.calcSuccess();
    }

    public int getFailed(){
        return tListRepo.calcFailed();
    }

    public int getPending(){
        return tListRepo.calcPending();
    }

   
    
}
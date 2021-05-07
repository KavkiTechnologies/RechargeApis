package com.kavki.fastfxrechargeapis.Controller.AdminPortal;

import java.util.List;

import com.kavki.fastfxrechargeapis.Entity.Admin.*;
import com.kavki.fastfxrechargeapis.Service.AdminServices.AdminPortalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminPortalServices portalServices;

    
    @GetMapping("/merchants")
    public List<MerchantEntity> getMerchant()
    {
        return portalServices.getMRecords();
    }

    @GetMapping("/transactions")
    public List<TransactionEntity> getTransactions()
    {
        return portalServices.getTRecords();
    }

    @GetMapping("/clients")
    public List<ClientEntity> getClients(){
        return portalServices.getCRecords();
    }

    @GetMapping("/prefund")
    public List<PrefundEntity> getPrefundBalance(){
        return portalServices.getPrefund();
    }

    @GetMapping("/totalbalance")
    public Double getTotalPrefund(){
        return portalServices.getBalance();
    }

    @GetMapping("/successtrans")
    public int getSuccessTransactions(){
        return portalServices.getSuccess();
    }

    @GetMapping("/failedtrans")
    public int getFailedTransactions(){
        return portalServices.getFailed();
    }

    @GetMapping("/pendingtrans")
    public int getPendingTransactions(){
        return portalServices.getPending();
    }
}

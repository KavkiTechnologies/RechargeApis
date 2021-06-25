package com.kavki.fastfxrechargeapis.DAO.ClientRepositories;

import java.util.List;

import com.kavki.fastfxrechargeapis.Entity.Client.LoadMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentSummeryRepo extends JpaRepository<LoadMoney, Integer>{

    @Query("Select m from load_money m where m.clientId =:cId")
    List<LoadMoney> findByClientId(@Param("cId") String clientId);

    @Query("Select m from load_money m where m.retailerId =:rId")
    List<LoadMoney> findByRetailerId(@Param("rId") String retailerId);

    LoadMoney findByClientIdAndReference(String clientId, String reference);

    List<LoadMoney> findByRetailerIdIsNull();

    LoadMoney findByRetailerIdAndReference(String retailerId, String reference);

    @Query("Select m from load_money m where m.clientId =:cId and m.retailerId =:rId")
    List<LoadMoney> findByClientIdAndRetailerId(@Param("cId") String clientId,@Param("rId") String retailer);

   // @Query("select m from load_money where retailerId = 'self' ")
   // List<LoadMoney> getPrefundRequests();
    
}

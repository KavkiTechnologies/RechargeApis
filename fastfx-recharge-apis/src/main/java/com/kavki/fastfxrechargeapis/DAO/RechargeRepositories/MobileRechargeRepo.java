package com.kavki.fastfxrechargeapis.DAO.RechargeRepositories;

import com.kavki.fastfxrechargeapis.DTO.MobileToDbEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRechargeRepo  extends JpaRepository<MobileToDbEntity, String>{
	
}

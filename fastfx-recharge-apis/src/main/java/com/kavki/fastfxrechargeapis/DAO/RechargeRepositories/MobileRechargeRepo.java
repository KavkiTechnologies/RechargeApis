package com.kavki.fastfxrechargeapis.DAO.RechargeRepositories;

import com.kavki.fastfxrechargeapis.DTO.MapToDbEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRechargeRepo  extends JpaRepository<MapToDbEntity, String>{
	
}

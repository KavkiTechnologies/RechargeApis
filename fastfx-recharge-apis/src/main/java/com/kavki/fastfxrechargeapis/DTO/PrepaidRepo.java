package com.kavki.fastfxrechargeapis.DTO;

import com.kavki.fastfxrechargeapis.DAO.PrepaidDao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrepaidRepo  extends JpaRepository<PrepaidDao, Integer>{
	
}

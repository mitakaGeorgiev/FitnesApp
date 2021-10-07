package com.myfirm.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myfirm.app.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
    public Food findByName(String name);
    
    @Query("SELECT u FROM Food u WHERE u.id = ?1")
    public Food findByLongId(Long id);
    
}

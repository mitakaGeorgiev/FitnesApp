package com.myfirm.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myfirm.app.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
	
    public Exercise findByName(String name);
    
    @Query("SELECT u FROM Exercise u WHERE u.id = ?1")
    public Exercise findByLongId(Long id);
      
}

package com.myfirm.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfirm.app.entity.PersonalProgram;

@Repository
public interface PersonalProgramRepository extends JpaRepository<PersonalProgram, Long>{
	
	@Query("SELECT u FROM PersonalProgram u WHERE u.id = ?1")
	public PersonalProgram findByLongId(Long id);
		
}

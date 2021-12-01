package com.myfirm.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myfirm.app.entity.PersonalRegime;

@Repository
public interface PersonalRegimeRepository extends JpaRepository<PersonalRegime, Long>{
	
	@Query("SELECT u FROM PersonalRegime u WHERE u.id = ?1")
	public PersonalRegime findByLongId(Long id);
}

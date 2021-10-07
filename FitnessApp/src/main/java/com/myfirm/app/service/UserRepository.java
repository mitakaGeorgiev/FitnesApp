package com.myfirm.app.service;

 

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfirm.app.entity.User;
import com.myfirm.app.entity.UserRole;
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    
   // public User findById(long id);
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findByLongId(Long id);
    
    public User findByRole(UserRole role);
    
    public List<User> findAllByRole(UserRole role);
    
    public User findByMentorid(long mentorid);
    
    public List<User> findAllByMentorid(long mentorid);
   
 } 

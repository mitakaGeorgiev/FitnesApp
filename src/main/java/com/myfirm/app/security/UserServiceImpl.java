package com.myfirm.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfirm.app.entity.User;
import com.myfirm.app.service.UserRepository;
import com.myfirm.app.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}
	
}

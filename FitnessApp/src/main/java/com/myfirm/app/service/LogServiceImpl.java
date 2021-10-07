package com.myfirm.app.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.myfirm.app.entity.Blog;

@Service
@Primary
public class LogServiceImpl implements LogService{

	@Override
	public List<Blog> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> findLatest5() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog create(Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog edit(Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

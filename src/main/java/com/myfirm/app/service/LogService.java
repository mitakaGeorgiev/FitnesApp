package com.myfirm.app.service;

import java.util.List;

import com.myfirm.app.entity.Blog;

public interface LogService {
	
	List<Blog> findAll();
	
	List<Blog> findLatest5();
	
	Blog findById(Long id);
	
	Blog create(Blog blog);
	
	Blog edit(Blog blog);
	
	void deleteById(Long id);
}

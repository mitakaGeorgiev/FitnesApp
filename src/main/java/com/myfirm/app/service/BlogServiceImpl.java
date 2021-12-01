package com.myfirm.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfirm.app.entity.Blog;

@Service
@Primary
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepository blogrepo;

	@Override
	public List<Blog> findAll() {
		// TODO Auto-generated method stub
		return this.blogrepo.findAll();
	}

	@Override
	public List<Blog> findLatest5() {
		return this.blogrepo.findBlogs(PageRequest.of(0, 5, Sort.by("date").descending()));
		
	}

	@Override
	public Blog findById(Long id) {
		// TODO Auto-generated method stub
		return blogrepo.findById(id).get();
	}

	@Override
	public Blog create(Blog blog) {
		// TODO Auto-generated method stub
		return this.blogrepo.save(blog);
	}

	@Override
	public Blog edit(Blog blog) {
		// TODO Auto-generated method stub
		return this.blogrepo.save(blog);
	}

	@Override
	public void deleteById(Long id) {
		this.blogrepo.deleteById(id);;
		
	}

}

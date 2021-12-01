package com.myfirm.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfirm.app.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	@Query("SELECT b FROM Blog b LEFT JOIN FETCH b.user")
	List<Blog> findBlogs(Pageable pageable);
	
}

package com.myfirm.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myfirm.app.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{

}

package com.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ems.model.HR;

@Repository
public interface HRRepository extends JpaRepository<HR, Integer>{
	
//    @Query("SELECT hr.HRId, hr.password FROM HR hr WHERE hr.email = :email")
//    Object[] findHRIdAndPasswordByEmail(@Param("email") String email);

}

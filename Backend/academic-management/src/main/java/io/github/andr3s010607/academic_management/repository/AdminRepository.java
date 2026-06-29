package io.github.andr3s010607.academic_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.andr3s010607.academic_management.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	public Optional<Admin> findByUserName(String userName);

	public void deleteByUserName(String userName); 
	
}

package io.github.andr3s010607.academic_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.andr3s010607.academic_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public Optional<Student> findByUserName(String username);

	public void deleteByUserName(String username); 
	
}

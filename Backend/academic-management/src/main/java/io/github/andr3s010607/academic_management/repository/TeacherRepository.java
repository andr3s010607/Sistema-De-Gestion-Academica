package io.github.andr3s010607.academic_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.andr3s010607.academic_management.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	public Optional<Teacher> findByUsername(String username);

	public void deleteByUsername(String username); 
}

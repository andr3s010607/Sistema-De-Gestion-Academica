package io.github.andr3s010607.academic_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.andr3s010607.academic_management.entity.Subject;


public interface SubjectRepository extends JpaRepository<Subject, Long>{
	
	public Optional<Subject> findByName(String name);

	public void deleteByName(String name); 
	
	public Optional<Subject> findBySubjectCode(int subjectCode);

	public void deleteBySubjectCode(int subjectCode); 
}

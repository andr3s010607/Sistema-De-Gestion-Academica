package io.github.andr3s010607.academic_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.andr3s010607.academic_management.dto.StudentDTO;
import io.github.andr3s010607.academic_management.entity.Student;
import io.github.andr3s010607.academic_management.repository.StudentRepository;

@Service
public class StudentService implements CRUDOperation<StudentDTO>{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private PasswordEncoder passwordEncoder;
	
	public StudentService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long count() {
		return studentRepo.count();
	}
	
	@Override
	public boolean exist(Long id) {
		return studentRepo.existsById(id);
	}
	
	@Override
	public int create(StudentDTO data) {
		Student entity = modelMapper.map(data, Student.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		}else {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			if (data.getUserType() != null) {
				entity.setUserType(data.getUserType());
			}
			studentRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<StudentDTO> getAll() {
		List<Student> entityList = studentRepo.findAll();
		List<StudentDTO> dtoList = new ArrayList<>();
		entityList.forEach(
			(entity)->{
				StudentDTO dto = modelMapper.map(entity, StudentDTO.class);
				dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Student> found = studentRepo.findById(id);
		if(found.isPresent()) {
			studentRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteByUsername(String username) {
		Optional<Student> found = studentRepo.findByUsername(username);
		if(found.isPresent()) {
			studentRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	@Override
	public int UpdateById(Long id, StudentDTO newData) {
		Optional<Student> found = studentRepo.findById(id);
		Optional<Student> newFound = studentRepo.findByUsername(newData.getUserName());
		if (found.isPresent() && !newFound.isPresent()) {
			Student temp = found.get();
			temp.setUserName(newData.getUserName());
			temp.setPassword(passwordEncoder.encode(newData.getPassword()));
			if (newData.getUserType() != null) {
				temp.setUserType(newData.getUserType());
			}
			studentRepo.save(temp);
			return 0;
		}
		if (found.isPresent() && newFound.isPresent()) {
			return 1;
		}
		if (!found.isPresent()) {
			return 2;
		}else {
			return 3;
		}
		
	}

	public StudentDTO getByID(Long id) {
		Optional<Student> found = studentRepo.findById(id); 
		if (found.isPresent()) {
			return modelMapper.map(found.get(), StudentDTO.class);
		}else {
			return null;
		}
	}
	
	public boolean findUsernameAlreadyTaken(Student newStudent) {
		Optional<Student> found = studentRepo.findByUsername(newStudent.getUsername());
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean findUsernameAlreadyTaken(String username) {
		Optional<Student> found = studentRepo.findByUsername(username);
		return found.isPresent();
	}
	
	public int validateCredentials(String username, String password) {
		Optional<Student> studentOpt = studentRepo.findByUsername(username);
		if (studentOpt.isPresent()) {
			Student student = studentOpt.get();
			if (passwordEncoder.matches(password, student.getPassword())) {
				return 0;
			}
		}
		return 1;
	}
	
}

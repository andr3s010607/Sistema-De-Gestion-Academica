package io.github.andr3s010607.academic_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.andr3s010607.academic_management.dto.TeacherDTO;
import io.github.andr3s010607.academic_management.entity.Teacher;
import io.github.andr3s010607.academic_management.repository.TeacherRepository;

@Service
public class TeacherService implements CRUDOperation<TeacherDTO>{
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public TeacherService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long count() {
		return teacherRepo.count();
	}
	
	@Override
	public boolean exist(Long id) {
		return teacherRepo.existsById(id);
	}
	
	@Override
	public int create(TeacherDTO data) {
		Teacher entity = modelMapper.map(data, Teacher.class);
		if (finUsernameAlreadyTaken(entity)) {
			return 1;
		}else {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			if (data.getUserType() != null) {
				entity.setUserType(data.getUserType());
			}
			teacherRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<TeacherDTO> getAll() {
		List<Teacher> entityList = teacherRepo.findAll();
		List<TeacherDTO> dtoList = new ArrayList<>();
		entityList.forEach(
				(entity) ->{
					TeacherDTO dto = modelMapper.map(entity, TeacherDTO.class);
					dtoList.add(dto);
				});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Teacher> found = teacherRepo.findById(id);
		if (found.isPresent()) {
			teacherRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteByUsername(String username) {
		Optional<Teacher> found = teacherRepo.findByUsername(username);
		if (found.isPresent()) {
			teacherRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	@Override
	public int UpdateById(Long id, TeacherDTO newData) {
		Optional<Teacher> found = teacherRepo.findById(id);
		Optional<Teacher> newFound = teacherRepo.findByUsername(newData.getUserName());
		
		if (found.isPresent() && !newFound.isPresent()) {
			Teacher temp = found.get();
			temp.setUserName(newData.getUserName());
			temp.setPassword(passwordEncoder.encode(newData.getPassword()));
			if (newData.getUserType() != null) {
				temp.setUserType(newData.getUserType());
			}
			teacherRepo.save(temp);
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
	
	public TeacherDTO getById(Long id) {
		Optional<Teacher> found = teacherRepo.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), TeacherDTO.class);
		}else {
			return null;
		}
	}
	
	public boolean finUsernameAlreadyTaken(Teacher newTeacher) {
		Optional<Teacher> found = teacherRepo.findByUsername(newTeacher.getUsername());
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean finUsernameAlreadyTaken(String username) {
	 Optional<Teacher> found = teacherRepo.findByUsername(username);
	 return found.isPresent();
	}
	
	public int validateCredentials(String username, String password) {
		Optional<Teacher> teacherOpt = teacherRepo.findByUsername(username);
		if(teacherOpt.isPresent()) {
			Teacher teacher = teacherOpt.get();
			if (passwordEncoder.matches(password, teacher.getPassword())) {
				return 0;
			}
		}
		return 1;
	}

}
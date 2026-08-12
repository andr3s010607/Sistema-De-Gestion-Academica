package io.github.andr3s010607.academic_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.andr3s010607.academic_management.dto.StudentDTO;
import io.github.andr3s010607.academic_management.dto.SubjectDTO;
import io.github.andr3s010607.academic_management.entity.Student;
import io.github.andr3s010607.academic_management.entity.Subject;
import io.github.andr3s010607.academic_management.entity.Teacher;
import io.github.andr3s010607.academic_management.repository.SubjectRepository;

@Service
public class SubjectService implements CRUDOperation<SubjectDTO>{
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public SubjectService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long count() {
		return subjectRepo.count();
	}

	@Override
	public boolean exist(Long id) {
		return subjectRepo.existsById(id);
	}
	
	public boolean existBySubjectCode(int subjectCode) {
		Optional<Subject> found = subjectRepo.findBySubjectCode(subjectCode);
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int create(SubjectDTO data) {
		Subject entity = modelMapper.map(data, Subject.class);
		if (findNameAlreadyUsed(entity)) {
			return 1;
		}else {
			subjectRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<SubjectDTO> getAll() {
		List<Subject> entityList = subjectRepo.findAll();
		List<SubjectDTO> dtoList = new ArrayList<>();
		entityList.forEach(
				(entity) -> {
					SubjectDTO dto = modelMapper.map(entity, SubjectDTO.class);
					dtoList.add(dto);
				});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Subject> found = subjectRepo.findById(id);
		if(found.isPresent()) {
			subjectRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteBySubjectCode(int subjectCode) {
		Optional<Subject> found = subjectRepo.findBySubjectCode(subjectCode);
		if(found.isPresent()) {
			subjectRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteByName(String name) {
		Optional<Subject> found = subjectRepo.findByName(name);
		if(found.isPresent()) {
			subjectRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	@Override
	public int UpdateById(Long id, SubjectDTO newData) {
		Optional<Subject> found = subjectRepo.findById(id);
		Optional<Subject> newFound = subjectRepo.findByName(newData.getName());
		if (found.isPresent() && !newFound.isPresent()) {
			Subject temp = found.get();
			temp.setName(newData.getName());
			subjectRepo.save(temp);
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
	
	public int UpdateBySubjectCode(int subjectCode, SubjectDTO newData) {
		Optional<Subject> found = subjectRepo.findBySubjectCode(subjectCode);
		Optional<Subject> newFound = subjectRepo.findByName(newData.getName());
		if (found.isPresent() && !newFound.isPresent()) {
			Subject temp = found.get();
			temp.setName(newData.getName());
			subjectRepo.save(temp);
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
	
	public SubjectDTO getById(Long id) {
		Optional<Subject> found = subjectRepo.findById(id);
		if(found.isPresent()) {
			return modelMapper.map(found.get(), SubjectDTO.class);
		}else {
			return null;
		}
	}
	
	public boolean findNameAlreadyUsed(Subject newSubject) {
		Optional<Subject> found = subjectRepo.findByName(newSubject.getName());
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean findNameAlreadyUsed(String name) {
		Optional<Subject> found = subjectRepo.findByName(name);
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addStudentToSubject(Long id, Student newStudent) {
		Optional<Subject> found = subjectRepo.findById(id);
		Subject temp = found.get();
		temp.getStudents().add(newStudent);
	}
	
	
}

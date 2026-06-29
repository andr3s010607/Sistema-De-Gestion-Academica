package io.github.andr3s010607.academic_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.andr3s010607.academic_management.dto.AdminDTO;
import io.github.andr3s010607.academic_management.entity.Admin;
import io.github.andr3s010607.academic_management.repository.AdminRepository;

@Service
public class AdminService implements CRUDOperation<AdminDTO>{
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AdminService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long count() {
		return adminRepo.count();
	}
	
	@Override
	public boolean exist(Long id) {
		return adminRepo.existsById(id);
	}
	
	@Override
	public int create(AdminDTO data) {
		Admin entity = modelMapper.map(data, Admin.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		}else {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			if (data.getUserType() != null) {
				entity.setUserType(data.getUserType());
			}
			adminRepo.save(entity);
			return 0;
		}
		
	}

	@Override
	public List<AdminDTO> getAll() {
		List<Admin> entityList = adminRepo.findAll();
		List<AdminDTO> dtoList = new ArrayList<>();
		entityList.forEach(
				(entity) -> {
					AdminDTO dto = modelMapper.map(entity, AdminDTO.class);
					dtoList.add(dto);
				});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Admin> found = adminRepo.findById(id);
		if(found.isPresent()) {
			adminRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteByUsername(String username) {
		Optional<Admin> found = adminRepo.findByUsername(username);
		if(found.isPresent()) {
			adminRepo.delete(found.get());
			return 0;
		}else {
			return 1;
		}
	}
	
	
	@Override
	public int UpdateById(Long id, AdminDTO newData) {
		Optional<Admin>found = adminRepo.findById(id);
		Optional<Admin> newFound = adminRepo.findByUsername(newData.getUserName());
		
		if (found.isPresent() && !newFound.isPresent()) {
			Admin temp = found.get();
			temp.setUserName(newData.getUserName());
			temp.setPassword(passwordEncoder.encode(newData.getPassword()));
			if (newData.getUserType() != null) {
				temp.setUserType(newData.getUserType());
			}
			adminRepo.save(temp);
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
	
	public AdminDTO getById(Long id) {
		Optional<Admin> found = adminRepo.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), AdminDTO.class);
		}else {
			return null;
		}
	}
	
	
	public boolean findUsernameAlreadyTaken(Admin newAdmin) {
		Optional<Admin> found = adminRepo.findByUsername(newAdmin.getUsername());
		if(found.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean findUsernameAlreadyTaken(String username) {
		Optional<Admin> found = adminRepo.findByUsername(username);
		return found.isPresent();
	}
	
	public int validateCredentials(String username, String password) {
		Optional<Admin> adminOpt = adminRepo.findByUsername(username);
		if (adminOpt.isPresent()) {
			Admin admin = adminOpt.get();
			if (passwordEncoder.matches(password, admin.getPassword())) {
				return 0;
			}
		}
		return 1;
	}
	
	
}

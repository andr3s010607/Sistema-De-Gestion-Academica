package io.github.andr3s010607.academic_management.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.andr3s010607.academic_management.entity.User;
import io.github.andr3s010607.academic_management.repository.AdminRepository;
import io.github.andr3s010607.academic_management.repository.StudentRepository;
import io.github.andr3s010607.academic_management.repository.TeacherRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final AdminRepository adminRepo;
	private final StudentRepository studentRepo;
	private final TeacherRepository teacherRepo;
	
	
	
	public UserDetailsServiceImpl(
			AdminRepository adminRepo,
			StudentRepository studentRepo,
			TeacherRepository teacherRepo) {
		this.adminRepo = adminRepo;
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<? extends User> user = adminRepo.findByUserName(username);
		if (user.isPresent()) {
			return user.get();
		}

		user = studentRepo.findByUserName(username);
		if (user.isPresent()) {
			return user.get();
		}

		user = teacherRepo.findByUserName(username);
		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("User not found with username: " + username);
	}

}

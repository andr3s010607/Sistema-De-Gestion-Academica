package io.github.andr3s010607.academic_management.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.andr3s010607.academic_management.entity.Admin;
import io.github.andr3s010607.academic_management.entity.Student;
import io.github.andr3s010607.academic_management.entity.Teacher;
import io.github.andr3s010607.academic_management.enums.UserType;
import io.github.andr3s010607.academic_management.repository.AdminRepository;
import io.github.andr3s010607.academic_management.repository.StudentRepository;
import io.github.andr3s010607.academic_management.repository.TeacherRepository;


@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(
			AdminRepository adminRepo,
			StudentRepository studentRepo,
			TeacherRepository teacherRepo,
			PasswordEncoder passwordEncoder) {
		
		return args -> {
			if(!existsAnyUserWithName(
					"adminTest",
					adminRepo,
					studentRepo,
					teacherRepo)) {
				Admin admin = new Admin("andres", "adminTest", passwordEncoder.encode("1234567890"), UserType.ADMIN);
				adminRepo.save(admin);
				log.info("Preloading admin user");
			}
			
			if(!existsAnyUserWithName("studentTest",
					adminRepo,
					studentRepo,
					teacherRepo)) {
				Student student = new Student("andres", "studentTest", passwordEncoder.encode("1234567890"), UserType.STUDENT, null);
				studentRepo.save(student);
				log.info("Preloading student user");
			}
			if(!existsAnyUserWithName("teacherTest",
					adminRepo,
					studentRepo,
					teacherRepo)) {
				Teacher teacher = new Teacher("andres", "teacherTest", passwordEncoder.encode("1234567890"), UserType.TEACHER, null);
				teacherRepo.save(teacher);
				log.info("Preloading teacher user");
			}
			
		};
		
	}
	
	private boolean existsAnyUserWithName(
			String userName,
			AdminRepository adminRepo,
			StudentRepository studentRepo,
			TeacherRepository teacherRepo) {
		return adminRepo.findByUsername(userName).isPresent()
				||studentRepo.findByUsername(userName).isPresent()
				||teacherRepo.findByUsername(userName).isPresent();
	}
	
	
}

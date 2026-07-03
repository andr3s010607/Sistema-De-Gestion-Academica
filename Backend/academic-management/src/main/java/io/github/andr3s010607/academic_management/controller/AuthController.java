package io.github.andr3s010607.academic_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.andr3s010607.academic_management.dto.AdminDTO;
import io.github.andr3s010607.academic_management.dto.StudentDTO;
import io.github.andr3s010607.academic_management.dto.TeacherDTO;
import io.github.andr3s010607.academic_management.dto.UserDTO;
import io.github.andr3s010607.academic_management.entity.User;
import io.github.andr3s010607.academic_management.enums.UserType;
import io.github.andr3s010607.academic_management.security.JwtUtil;
import io.github.andr3s010607.academic_management.service.AdminService;
import io.github.andr3s010607.academic_management.service.StudentService;
import io.github.andr3s010607.academic_management.service.TeacherService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final AdminService adminService;
	private final TeacherService teacherService;
	private final StudentService studentService;
	
	
	
	
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AdminService adminService,
			TeacherService teacherService, StudentService studentService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.adminService = adminService;
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO loginRequest) {
		try {
			Authentication authentication =
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(
									loginRequest.getUserName(), loginRequest.getPassword()));

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String jwt = jwtUtil.generateToken(userDetails);

			String role = null;
			if (userDetails instanceof User) {
				User usuario = (User) userDetails;
				role = usuario.getUserType().name();
			}

			return ResponseEntity.ok(new AuthResponse(jwt, role));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Nombre o contrasena invalidos o usuario no encontrado");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO registerRequest) {
		
		if(registerRequest.getUserType().name() == "ADMIN") {
			AdminDTO newUser = new AdminDTO(registerRequest.getName(), registerRequest.getUserName(), registerRequest.getPassword(), UserType.ADMIN);
			int result = adminService.create(newUser);
			if (result == 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
			}
			if (result == 1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre no puede estar vacio");
			}
		}
		if(registerRequest.getUserType().name() == "STUDENT") {
			StudentDTO newUser = new StudentDTO(registerRequest.getName(), registerRequest.getUserName(), registerRequest.getPassword(), UserType.STUDENT);
			int result = studentService.create(newUser);
			if (result == 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
			}
			if (result == 1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre no puede estar vacio");
			}
		}
		if(registerRequest.getUserType().name() == "TEACHER") {
			TeacherDTO newUser = new TeacherDTO(registerRequest.getName(), registerRequest.getUserName(), registerRequest.getPassword(), UserType.TEACHER);
			int result = teacherService.create(newUser);
			if (result == 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
			}
			if (result == 1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre no puede estar vacio");
			}
		}
		
		
		return null;
		
		
		
	}
	
	
	
	private static class AuthResponse {
		private final String token;
		private final String role;

		public AuthResponse(String token, String role) {
			this.token = token;
			this.role = role;
		}

		public String getToken() {
			return token;
		}

		public String getRole() {
			return role;
		}
	}
}

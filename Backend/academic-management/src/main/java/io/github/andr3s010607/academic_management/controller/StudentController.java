package io.github.andr3s010607.academic_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.andr3s010607.academic_management.dto.StudentDTO;
import io.github.andr3s010607.academic_management.enums.UserType;
import io.github.andr3s010607.academic_management.service.StudentService;

@RestController
@RequestMapping("/student-user")
@CrossOrigin(origins = "http://localhost:8081" , allowCredentials = "true")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createStudentUser(@RequestParam String name, @RequestParam String userName, @RequestParam String password){
		StudentDTO newUser = new StudentDTO(name, userName, password, UserType.STUDENT);
		int status = studentService.create(newUser);
		
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Student user successfully created", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error creating student user", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<StudentDTO>> showAll(){
		List<StudentDTO> users = studentService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<StudentDTO>>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StudentDTO>>(users, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update-by-id")
	public ResponseEntity<String> updateStudentUser(@RequestParam Long id,@RequestParam String name, @RequestParam String userName, @RequestParam String password){
		StudentDTO newUser = new StudentDTO(name, userName, password, UserType.STUDENT);
		int status = studentService.UpdateById(id, newUser);
		
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Student user successfully updated", HttpStatus.ACCEPTED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error updating student user", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete-by-id")
	public ResponseEntity<String> deleteStudentUser(@RequestParam Long id) {
		int status = studentService.deleteById(id);
		
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Student user successfully deleted", HttpStatus.ACCEPTED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error deleting student user", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete-by-username")
	public ResponseEntity<String> deleteStudentUser(@RequestParam String userName) {
		int status = studentService.deleteByUsername(userName);
		
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Student user successfully deleted", HttpStatus.ACCEPTED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error deleting student user", HttpStatus.BAD_REQUEST);
		}
	}
	
}

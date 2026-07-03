package io.github.andr3s010607.academic_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.andr3s010607.academic_management.dto.AdminDTO;
import io.github.andr3s010607.academic_management.enums.UserType;
import io.github.andr3s010607.academic_management.service.AdminService;

@RestController
@RequestMapping("/admin-user")
@CrossOrigin(origins = "http://localhost:8081" , allowCredentials = "true")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAdminUser(@RequestParam String name, @RequestParam String userName, @RequestParam String password){
		AdminDTO newUser = new AdminDTO(name, userName, password, UserType.ADMIN);
		int status = adminService.create(newUser);
		
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Administrator user successfully created", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error creating administrator user", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<AdminDTO>> showAll(){
		List<AdminDTO> users = adminService.getAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<AdminDTO>>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AdminDTO>>(users, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/update_by_id")
	public ResponseEntity<String> updateAdminUser(@RequestParam Long id, @RequestParam String name, @RequestParam String userName, @RequestParam String password){
		AdminDTO newUser = new AdminDTO(name, userName, password, UserType.ADMIN);
		int status = adminService.UpdateById(id, newUser);
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Administrator user successfully updated", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error creating administrator user", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete_by_id")
	public ResponseEntity<String> deleteAdminUser(@RequestParam Long id) {
		int status = adminService.deleteById(id);
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Administrator user successfully deleted", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error deleting administrator user", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete_by_username")
	public ResponseEntity<String> deleteAdminUser(@RequestParam String userName) {
		int status = adminService.deleteByUsername(userName);
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Administrator user successfully deleted", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error deleting administrator user", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}

package io.github.andr3s010607.academic_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.andr3s010607.academic_management.dto.SubjectDTO;
import io.github.andr3s010607.academic_management.service.SubjectService;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:8081" , allowCredentials = "true")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createSubject(@RequestParam String name, @RequestParam int subjectCode, @RequestParam String classRoom){
		SubjectDTO newSubject = new SubjectDTO(name, subjectCode, classRoom, null, null);
		int status = subjectService.create(newSubject);
		switch (status) {
		case 0: {
			return new ResponseEntity<String>("Subject successfully created", HttpStatus.CREATED);
		}
		case 1:{
			return new ResponseEntity<String>("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}
		default:
			return new ResponseEntity<String>("Error creating subject", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<SubjectDTO>> showAll(){
		List<SubjectDTO> subjects = subjectService.getAll();
		if (subjects.isEmpty()) {
			return new ResponseEntity<List<SubjectDTO>>(subjects, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SubjectDTO>>(subjects, HttpStatus.ACCEPTED);
	}
	
	
	
	
}

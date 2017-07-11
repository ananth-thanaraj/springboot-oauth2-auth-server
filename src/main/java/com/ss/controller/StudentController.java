package com.ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.data.StudentRepository;
import com.ss.data.UserRepository;
import com.ss.model.CustomDetails;
import com.ss.model.Student;
import com.ss.model.User;

@RestController
@RequestMapping("/studs")
public class StudentController {

	@Autowired
	private StudentRepository sturepo;
	
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value="/public/stu", method=RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@RequestBody Student student){		
		sturepo.save(student);		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/public/stu", method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudents(){		
		List<Student> stu = sturepo.findAll();		
		return new ResponseEntity<>(stu, HttpStatus.OK);
	}
	
	@RequestMapping(value="/home/", method=RequestMethod.GET)
	public ResponseEntity<String> getHome(){		
	
		return new ResponseEntity<>("Home page", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user){	
		User cds = new User();
		cds.setId(user.getId());
		cds.setUsername(user.getUsername());
		cds.setPassword(passwordEncoder.encode(user.getPassword()));
		cds.setRole(user.getRole());
		urepo.save(cds);		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
}

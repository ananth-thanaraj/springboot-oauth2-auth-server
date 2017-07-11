package com.ss.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ss.model.Student;

public interface StudentRepository extends MongoRepository<Student, String>{

}

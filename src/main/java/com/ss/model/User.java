package com.ss.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="user")
public @Data class User {

	@Id
	private String id;
	
	private String username;
	private String password;
	private List<String> role;
}

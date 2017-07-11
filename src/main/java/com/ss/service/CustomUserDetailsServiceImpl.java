package com.ss.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ss.data.UserRepository;
import com.ss.model.CustomDetails;
import com.ss.model.User;

public class CustomUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private MongoClient mongoClient;
	
	@Autowired
	private UserRepository urepo;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String name;
		
		MongoDatabase database = mongoClient.getDatabase("local");
		MongoCollection<Document> collection = database.getCollection("user");
		
		Document document = collection.find(Filters.eq("username",username)).first();
		
		
		/*name=document.getString("username");
		String pass = document.getString("password");
		@SuppressWarnings("unchecked")
		List<String> role = (List<String>) document.get("role");
		*/
		/*CustomDetails cds = new CustomDetails(name, pass, role.toArray(new String[role.size()]));
		return cds;*/
		
		return urepo
				.findByUsername(username)
				.map(custom -> new CustomDetails(document.getString("username"), document.getString("password"),((List<String>) document.get("role")).toArray(new String[((List<String>) document.get("role")).size()])))				
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
	}

}

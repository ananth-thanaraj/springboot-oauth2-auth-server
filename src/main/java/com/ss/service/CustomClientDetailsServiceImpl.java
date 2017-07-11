package com.ss.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ss.model.CustomClientDetails;

public class CustomClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private MongoClient mongoClient;
	
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
				String client;
		
		MongoDatabase database = mongoClient.getDatabase("local");
		MongoCollection<Document> collection = database.getCollection("oauth2_client");
		
		Document document = collection.find(Filters.eq("clientId",clientId)).first();
		
		if(document!=null){
		
		client=document.getString("clientId");
		String secret = document.getString("secret");
		
		Set<String> scope = new HashSet<String> ((List) document.get("scope"));
		Set<String> authGrantTypes = new HashSet<String> ((List) document.get("authorizedGrantTypes"));
		Integer accessTokenValidity = document.getInteger("accessTokenValidity");
		Integer refreshTokenValidity = document.getInteger("refreshTokenValidity");
		List<String> role = (List<String>) document.get("authorities");

		CustomClientDetails cds = new CustomClientDetails(client,secret,scope,authGrantTypes,accessTokenValidity,refreshTokenValidity,role.toArray(new String[role.size()]));
		return cds;
		}
		else
		{
			throw new ClientRegistrationException("No client found");
		}
		
	}

}

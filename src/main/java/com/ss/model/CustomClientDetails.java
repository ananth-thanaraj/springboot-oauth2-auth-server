package com.ss.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

public class CustomClientDetails implements ClientDetails{

	private String clientId;
	private String secret;
	private Set<String> scope;
	private Set<String> authorizedGrantTypes;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	List<GrantedAuthority> grantedAuthorities;
	
	
	
	public CustomClientDetails(String clientId, String secret, Set<String> scope, Set<String> authorizedGrantTypes,
			Integer accessTokenValidity, Integer refreshTokenValidity, String[] roles) {
		super();
		this.clientId = clientId;
		this.secret = secret;
		this.scope = scope;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.grantedAuthorities=AuthorityUtils.createAuthorityList(roles);
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return accessTokenValidity;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		// TODO Auto-generated method stub
		return authorizedGrantTypes;
	}

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return clientId;
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return secret;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return refreshTokenValidity;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getResourceIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getScope() {
		// TODO Auto-generated method stub
		return scope;
	}

	@Override
	public boolean isAutoApprove(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return true;
	}

}

package com.ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.ss.service.CustomClientDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class OauthAuthServer extends AuthorizationServerConfigurerAdapter{

	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Bean
	public ClientDetailsService clientDetaialsService(){
		return new CustomClientDetailsServiceImpl();
	}
	

	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		ClientDetailsService cls = clientDetaialsService();
		
		clients.withClientDetails(clientDetaialsService());
		/*clients.inMemory()
			.withClient("my-client")
			.secret("password")
			.authorizedGrantTypes("password","implicit")
			.scopes("read","write","trust")
			.accessTokenValiditySeconds(120)
			.refreshTokenValiditySeconds(600);*/
	}

}

package com.stard.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final DataSource dataSource;
	
	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.mvcMatchers(HttpMethod.GET, "/", "/member/join").permitAll()
		.mvcMatchers(HttpMethod.POST, "/member/join/save").permitAll()
		.anyRequest().authenticated();
	}
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcRepository = new JdbcTokenRepositoryImpl();
		jdbcRepository.setDataSource(dataSource);
		return jdbcRepository;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//정적자원에 대해서는 security 적용하지 않음 >> 이거 안하면 나중에 css 다 깨짐.
		web.ignoring()
		.mvcMatchers("/static/**")
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

}

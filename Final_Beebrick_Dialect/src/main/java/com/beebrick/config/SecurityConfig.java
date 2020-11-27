package com.beebrick.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.beebrick.controller.CustomAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).
			withUser("test").password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("kai").password("123456").roles("USER");
	}
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Chỉ cho phép user đã đăng nhập mới được truy cập đường dẫn /user/**
		http.authorizeRequests().antMatchers("/admin/**").authenticated();
		
		// Cấu hình concurrent session
		http.sessionManagement().sessionFixation().newSession()
		.invalidSessionUrl("/login?message=timeout")
		.maximumSessions(1).expiredUrl("/login?message=max_session").maxSessionsPreventsLogin(true);

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_login")//
				.loginPage("/login")//
				.defaultSuccessUrl("/admin/user/list")//
				.failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout");

	}
}

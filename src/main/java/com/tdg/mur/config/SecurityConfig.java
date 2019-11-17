package com.tdg.mur.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tdg.mur.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService uds;
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/auth/**")
			.permitAll()
			.antMatchers("/api/posts/query/**")
            .permitAll()
			.anyRequest()
			.authenticated();
		httpSecurity.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(uds).passwordEncoder(encoder());
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthFilter() {
		return new JwtAuthenticationFilter();
	}
	
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }
}

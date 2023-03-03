package com.arcesi.banque.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.repositories.UserRepository;
import com.arcesi.banque.services.AppUserService;
import com.arcesi.banque.services.impl.AppUserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final AppUserService appUserService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(appUserService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		   
		 
		 http.csrf().disable();
		 http.cors();
		 http.headers().frameOptions();
         http.authorizeRequests()
             .antMatchers(
            		 UIConstanteUrls.CONFIRMATION_TOKEN,
            		 UIConstanteUrls.SING_IN)
             .permitAll()
             .antMatchers(HttpMethod.GET,UIConstanteUrls.FIND_USER_CLIENT_CONNECTE).hasAuthority("ROLE_CLIENT")
            .antMatchers(HttpMethod.POST,UIConstanteUrls.CREATE_CLIENT).hasAnyAuthority("ROLE_EMPLOYE","ROLE_ADMINISTRATEUR") 
            .anyRequest().authenticated()
             .and()
             .addFilter(new AuthenticationFilter(authenticationManager()))
         .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
         // this disables session creation on Spring Security
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	
	 
	 
    /**
     * Liste des urls POST
     */
	List<String> pathListPOST = Arrays.asList(UIConstanteUrls.CREATE_CLIENT);
	
	/**
	 * List des Urls GET
	 */
	List<String> pathListGET=Arrays.asList(UIConstanteUrls.CONFIRMATION_TOKEN,UIConstanteUrls.SING_IN);
	
	 
	public static String getAllUrlPOST(List<String> pathListPOST) {
		StringBuilder st=new StringBuilder();
		for(String s:pathListPOST) {
			st.append('"'+s+'"');
			st.append(",");
		}
		String urls=st.toString();
		String last=urls.substring(0,urls.length()-1);
		return last;
	}
	
	public static String getAllUrlGET(List<String> pathListGET) {
		StringBuilder st=new StringBuilder();
		for(String s:pathListGET) {
			st.append('"'+ s+'"');
			st.append(",");
		}
		String urls=st.toString();
		String last=urls.substring(0,urls.length()-1);
		return last;
	}
}

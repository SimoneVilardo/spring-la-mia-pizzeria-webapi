package org.java.spring.auth.conf;

import org.java.spring.auth.db.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {
    @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.csrf().disable()
	        	.cors().disable()
	          	.authorizeHttpRequests()
	          	.requestMatchers("/pizzas/create/**").hasAuthority("ADMIN")
	          	.requestMatchers("/pizzas/edit/**").hasAuthority("ADMIN")
	          	.requestMatchers("/pizzas/delete/**").hasAuthority("ADMIN")
	          	.requestMatchers("/pizzas/{id}/offertaspeciale/create/**").hasAuthority("ADMIN")
	          	.requestMatchers("/pizzas/{pizzaId}/offertaspeciale/edit/**").hasAuthority("ADMIN")
	          	.requestMatchers("/ingredienti/create/**").hasAuthority("ADMIN")
	          	.requestMatchers("/ingredienti/delete/**").hasAuthority("ADMIN")
	          	.requestMatchers("/api/v1.0/pizzas/**").permitAll()
	          	.requestMatchers("/**").hasAnyAuthority("ADMIN", "USER")
	            .and().formLogin()
	            .and().logout()
        ;
        
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
	    return new UserService();
	}
    
    @Bean
	public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
   
    	return authProvider;
    }
   
}

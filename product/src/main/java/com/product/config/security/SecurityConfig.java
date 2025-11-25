package com.product.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.config.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
	
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/info", "/actuator/health").permitAll()
				.requestMatchers(HttpMethod.GET, "/category").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/category/active").permitAll()
				.requestMatchers(HttpMethod.POST, "/category").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/category/{id}").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/category/{id}/enable").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/category/{id}/disable").hasAuthority("ADMIN")
				
				// PRODUCT
			     .requestMatchers(HttpMethod.GET,  "/product").authenticated()                   // lista
			     .requestMatchers(HttpMethod.GET,  "/product/{id}").authenticated()                 // detalle por id
			     .requestMatchers(HttpMethod.POST, "/product").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.PUT,  "/product/{id}").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.PATCH,"/product/{id}/enable").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.PATCH,"/product/{id}/disable").hasAuthority("ADMIN")
			     
			     .requestMatchers(HttpMethod.GET,    "/product/{id}/image").authenticated()
			     .requestMatchers(HttpMethod.POST,   "/product/{id}/image").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.DELETE, "/product/{id}/image/{product_image_id}").hasAuthority("ADMIN")
			     
			     //ShoppingCart
			     .requestMatchers(HttpMethod.POST, "/cart-item").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.GET, "/cart-item").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.DELETE, "/cart-item/{id}").hasAuthority("ADMIN")
			     .requestMatchers(HttpMethod.DELETE, "/cart-item").hasAuthority("ADMIN")
				)
		.cors(cors -> cors.configurationSource(corsConfig))
		.httpBasic(Customizer.withDefaults())
		.formLogin(form -> form.disable())
		.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
}



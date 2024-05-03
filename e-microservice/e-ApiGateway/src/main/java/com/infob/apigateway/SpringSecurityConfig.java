package com.infob.apigateway;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;

import com.google.common.collect.ImmutableList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    	
        
    	// @formatter:off
    	http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange) {
				// TODO Auto-generated method stub
	            CorsConfiguration config = new CorsConfiguration();
	            config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	            config.setAllowedMethods(ImmutableList.of("HEAD",
	                    "GET", "POST", "PUT", "DELETE", "PATCH"));
	            config.setAllowCredentials(true);
	            config.setAllowedHeaders(Collections.singletonList("*"));
	            config.setExposedHeaders(Arrays.asList("Authorization"));
	            config.setMaxAge(3600L);
				return config;
			}
	    }));
        http.csrf()
		        .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
		        .and()
//		        .authorizeExchange()
//                .anyExchange().authenticated()
		        .authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
//		        		.pathMatchers("/product/**").hasRole("ADMIN")
//		        		.pathMatchers("/department/**").hasAnyRole("ADMIN", "USER")
		        		.pathMatchers("/product/getAllProduct").hasAnyRole("ADMIN", "USER")
		        		.pathMatchers("/product/addProduct").hasRole("ADMIN")
		        		.pathMatchers("/department/getAllDepartment").hasAnyRole("ADMIN", "USER")
		                )
                .oauth2Login()
                .and()
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        return http.build();
        // @formatter:on
    }
    
    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
    	System.out.println("inside grantedAuthoritiesExtractor");
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}

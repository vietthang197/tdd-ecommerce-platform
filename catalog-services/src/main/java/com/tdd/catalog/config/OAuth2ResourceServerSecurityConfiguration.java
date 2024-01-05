/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tdd.catalog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import java.io.IOException;

/**
 * OAuth2 resource configuration.
 *
 * @author Josh Cummings
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:9010")})
@SecurityScheme(name = "Authorization" , scheme = "Bearer", type = SecuritySchemeType.OAUTH2, in = SecuritySchemeIn.HEADER,
		flows = @OAuthFlows(password = @OAuthFlow(authorizationUrl = "http://localhost:8080/realms/ecommerce/protocol/openid-connect/auth",
				tokenUrl = "http://localhost:8080/realms/ecommerce/protocol/openid-connect/token")))
public class OAuth2ResourceServerSecurityConfiguration {

	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;
	private final MvcRequestMatcher.Builder matcher;
	public OAuth2ResourceServerSecurityConfiguration(MvcRequestMatcher.Builder matcher) {
		this.matcher = matcher;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.anonymous(Customizer.withDefaults())
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers(matcher.pattern("/actuator/**"), matcher.pattern("/swagger-ui/**"), matcher.pattern("/v3/api-docs/**")).permitAll()
						.anyRequest().authenticated()
				)
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
				.addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
		return http.build();
	}

	private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
		return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
			@Override
			public PolicyEnforcerConfig resolve(HttpRequest request) {
				try {
					return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
	}

}

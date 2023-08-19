package com.shopsense.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.shopsense.util.GrantedAuthorityDeserializer;
import com.shopsense.util.GrantedAuthoritySerializer;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

	@Bean
	Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializerByType(GrantedAuthority.class, new GrantedAuthoritySerializer());
		builder.deserializerByType(GrantedAuthority.class, new GrantedAuthorityDeserializer());
		return builder;
	}

	@Bean
	SimpleModule customAuthorityModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(GrantedAuthority.class, new GrantedAuthoritySerializer());
		module.addDeserializer(GrantedAuthority.class, new GrantedAuthorityDeserializer());
		return module;
	}
}

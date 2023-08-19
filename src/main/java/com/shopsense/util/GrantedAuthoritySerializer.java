package com.shopsense.util;

import java.io.IOException;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GrantedAuthoritySerializer extends JsonSerializer<GrantedAuthority> {

	@Override
	public void serialize(GrantedAuthority authority, JsonGenerator jsonGenerator, SerializerProvider serializers)
			throws IOException {
		jsonGenerator.writeString(authority.getAuthority());
	}
}

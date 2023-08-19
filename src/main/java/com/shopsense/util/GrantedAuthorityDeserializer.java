package com.shopsense.util;

import java.io.IOException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GrantedAuthorityDeserializer extends JsonDeserializer<GrantedAuthority> {
	@Override
	public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		String authority = jsonParser.getValueAsString();
		return new SimpleGrantedAuthority(authority);
	}
}

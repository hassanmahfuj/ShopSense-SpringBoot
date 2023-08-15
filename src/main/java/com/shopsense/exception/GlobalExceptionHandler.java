package com.shopsense.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<HashMap<String, String>> handleAllException(Exception e) {
		HashMap<String, String> m = new HashMap<>();
		m.put("status", "error");
		m.put("message", e.toString());
		return new ResponseEntity<HashMap<String, String>>(m, HttpStatus.OK);
	}
}

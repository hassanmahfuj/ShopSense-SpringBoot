package com.shopsense.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopsense.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

	@Autowired
	FileService fileService;
	
	@PostMapping("/upload")
	public HashMap<String, String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		HashMap<String, String> m = new HashMap<>();
		String url = fileService.saveFile(file);
		m.put("status", "success");
		m.put("fileUrl", url);
		return m;
	}

	@GetMapping(value = "uploads/{fileName}", produces = MediaType.ALL_VALUE)
	public void fileDownload(@PathVariable("fileName") String fileName, HttpServletResponse response)
			throws IOException {
		InputStream is = fileService.getFile(fileName);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(is, response.getOutputStream());
	}
}

package com.shopsense.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	public static final String UPLOAD_PATH = "uploads";

	public String saveFile(MultipartFile file) throws IOException {
		File f = new File(UPLOAD_PATH);
		if (!f.exists())
			f.mkdir();
		String fileName = file.getOriginalFilename();
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String filePath = UPLOAD_PATH.concat("/").concat(fileName);
		int count = 1;
		while (true) {
			try {
				Files.copy(file.getInputStream(), Paths.get(filePath));
				break;
			} catch (FileAlreadyExistsException e) {
				filePath = UPLOAD_PATH.concat("/").concat(name).concat("(").concat(String.valueOf(count++)).concat(")")
						.concat(extension);
			}
		}
		return filePath;
	}

	public InputStream getFile(String path) throws FileNotFoundException {
		String filePath = UPLOAD_PATH.concat("/").concat(path);
		InputStream is = new FileInputStream(filePath);
		return is;
	}
}

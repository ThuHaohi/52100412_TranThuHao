package com.university.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public static File convertMultiPartFileToFile(MultipartFile multipartFile) {
		File convertedFile = new File(multipartFile.getOriginalFilename());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(convertedFile);
			fos.write(multipartFile.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedFile;
	}
}

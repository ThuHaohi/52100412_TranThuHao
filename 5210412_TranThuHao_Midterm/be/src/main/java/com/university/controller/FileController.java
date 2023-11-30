package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.university.service.FileService;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File")
@RestController
@RequestMapping("/api/v1/file")
public class FileController implements SecuredRestController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
		try {
			byte[] data = fileService.downloadFile(fileName);
			ByteArrayResource resource = new ByteArrayResource(data);
			return ResponseHandler.generateResponse(resource, HttpStatus.OK, null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(null, HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
		}	
	}
	
	@DeleteMapping("/{fileName}")
	public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
		try {
			fileService.deleteFile(fileName);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(null, HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
		}
		
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadFile(@RequestPart(value = "file", required = true) MultipartFile multipartFile) {
		try {
			String path = fileService.uploadFile(multipartFile);
			return ResponseHandler.generateResponse(path, HttpStatus.OK, null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(null, HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
		}
	}
}

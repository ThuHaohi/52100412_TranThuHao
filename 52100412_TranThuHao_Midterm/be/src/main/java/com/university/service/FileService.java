package com.university.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.university.utilities.FileUtils;
import com.university.utilities.ResponseHandler;

@Service
public class FileService {
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${aws.s3.bucket.name}")
	private String bucketName;
	
	@Value("${aws.s3.bucket.url}")
	private String bucketUrl;
	
	public String uploadFile(MultipartFile multipartFile) {
		File file = FileUtils.convertMultiPartFileToFile(multipartFile);
		String fileName = System.currentTimeMillis()+"_"+multipartFile.getOriginalFilename();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "images/" + fileName, file);
		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		s3Client.putObject(putObjectRequest);
		file.delete();
		return bucketUrl + "/" + fileName;
	}
	
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(s3ObjectInputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public ResponseEntity<BaseResponseDto<String>> retrieveFileUrl(String fileName) {
//		URL url = s3Client.getUrl(bucketName, fileName);
//		return BaseResponseDto.success("success", url.toString());
//	}
	
	public void deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
	}
	
}

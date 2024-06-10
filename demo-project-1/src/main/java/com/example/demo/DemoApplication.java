package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

//	@PostMapping
//public ResponseEntity<?> uploadimage(@RequestParam("image") MultipartFile file) throws IOException{
//String uploadImage = uploadimage(file);
//return ResponseEntity.status(HttpStatus.OK)
//		.body(uploadImage);
//	}
//
//	@GetMapping("/{fliename}")
//	public ResponseEntity<?> downloadImage(@PathVariable String filename){
//		byte[] imageData = .downloadImage(filename);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

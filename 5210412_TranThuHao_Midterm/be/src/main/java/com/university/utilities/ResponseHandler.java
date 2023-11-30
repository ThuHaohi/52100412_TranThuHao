package com.university.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity generateResponse(Object data, HttpStatus status, String errorMessage) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("errorMessage", errorMessage);
        return new ResponseEntity(map, status);
    }
	
}

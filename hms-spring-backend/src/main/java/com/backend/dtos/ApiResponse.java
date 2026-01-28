package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private LocalDateTime timeStamp;
	private String message;
	private String status;
	 private Object data;

	public ApiResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = LocalDateTime.now();
	}

	 public ApiResponse(String message, String status, Object data) {
	        this.message = message;
	        this.status = status;
	        this.data = data;
	        this.timeStamp = LocalDateTime.now();
	    }
}
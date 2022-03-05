package com.bridgelabz.bookstore.dto;

import lombok.Data;

//Data transfer object to display output in message with object format
@Data
public class ResponseDTO {
	private String message;
	private Object data;
	
	public ResponseDTO(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}	
}

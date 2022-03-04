package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotEmpty;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;

import lombok.Data;
@Data
public class OrderDTO {
	
	private Integer quantity;
	@NotEmpty(message="Please provide address")
	private String address;
	private Integer userID;
	private Integer bookID;
	private boolean cancel;
	
}
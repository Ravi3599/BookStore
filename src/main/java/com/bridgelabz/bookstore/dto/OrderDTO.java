package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotEmpty;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;

import lombok.Data;

//Data transfer object of Order Model
@Data
public class OrderDTO {
	
	private Integer quantity;
	@NotEmpty(message="Please provide address")
	private String address;
	private Integer price;
	private Integer userID;
	private Integer bookID;
	private boolean cancel;
	
}

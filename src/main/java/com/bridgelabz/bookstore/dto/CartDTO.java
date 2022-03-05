package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

//Data transfer object of Cart Model
@Data
public class CartDTO {
	private Integer userID;
	private Integer bookID;
	@NotNull(message="Book quantity yet to be provided")
	private Integer quantity;
}

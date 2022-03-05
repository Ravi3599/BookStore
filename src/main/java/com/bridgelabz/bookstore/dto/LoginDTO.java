package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

//Data transfer object to login user
@Data
public class LoginDTO {
	@NotEmpty(message="Please Provide email")
	@Email
	private String email;
	@NotBlank(message="Please provide password")
	private String password;
}

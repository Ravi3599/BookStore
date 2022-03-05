package com.bridgelabz.bookstore.dto;

import lombok.Data;

//Data transfer object if forgot password
@Data
public class ChangePasswordDTO {
	private String email;
	private String newPassword;
	private String token;

}

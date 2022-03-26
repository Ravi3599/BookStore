package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.model.User;

//Interface to achieve abstraction
public interface IUserService {

	public User registerUser(UserDTO userdto);

	public List<User> getAllRecords();

	public User getRecord(Integer id);

	public User updateRecord(Integer id,UserDTO dto);

	public User userLogin(LoginDTO logindto);

	public User getRecordByEmail(String email);
	
	public String getToken(String email);

	public User changePassword(ChangePasswordDTO dto);

	public User getRecordByToken(String token);

}

package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.model.User;

public interface IUserService {

	public User registerUser(UserDTO userdto);

	public List<User> getAllRecords();

	public User getRecord(Integer id);

	public User updateRecord(Integer id,UserDTO dto);

	public User userLogin(LoginDTO logindto);

	public User getRecordByEmail(String email);

	public User changePassword(String email,String newPassword,String token);

}
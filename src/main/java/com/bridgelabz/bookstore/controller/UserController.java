package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.IUserService;

//Controller to make api calls
@RestController
@RequestMapping("/userdetails")
public class UserController {
	//Autowired IUserService to inject its dependency here
	@Autowired
	private IUserService userService;
	
	//Ability to call api to login user
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody LoginDTO logindto){
		User newUser = userService.userLogin(logindto);
		ResponseDTO dto = new ResponseDTO("User logged in successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to change password
	@PutMapping("/changepassword")
	public ResponseEntity<ResponseDTO> forgotPassword(@Valid @RequestBody ChangePasswordDTO passwordDTO){
		User newUser = userService.changePassword(passwordDTO);
		ResponseDTO dto = new ResponseDTO("Password Resetted successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to get token if forgot password
	@GetMapping("/getToken/{email}")
	public ResponseEntity<ResponseDTO> getToken(@PathVariable String email){
		String generatedToken = userService.getToken(email);
		ResponseDTO dto = new ResponseDTO("Token for mail id sent on mail successfully !",generatedToken);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to register user
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO userdto){
		User newUser = userService.registerUser(userdto);
		ResponseDTO dto = new ResponseDTO("User registered successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	//Ability to call api to retrieve all user records
	@GetMapping("/retrieveAll")
	public ResponseEntity<ResponseDTO> getAllRecords(){
		List<User> newUser = userService.getAllRecords();
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to retrieve user record by id
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<ResponseDTO> getRecord(@PathVariable Integer id){
		User newUser = userService.getRecord(id);
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to update user record by id
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateRecord(@PathVariable Integer id,@Valid @RequestBody UserDTO userdto){
		User newUser = userService.updateRecord(id,userdto);
		ResponseDTO dto = new ResponseDTO("Record updated successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to call api to retrieve user record by email
	@GetMapping("/retrievebyemail/{email}")
	public ResponseEntity<ResponseDTO> getRecordByEmail(@PathVariable String email){
		User newUser = userService.getRecordByEmail(email);
		ResponseDTO dto = new ResponseDTO("Record for particular email retrieved successfully !",newUser);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	
}

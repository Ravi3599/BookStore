package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repositoy.UserRepository;
import com.bridgelabz.bookstore.util.EmailSenderService;
import com.bridgelabz.bookstore.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

//Ability to provide service to controller
@Service
@Slf4j
public class UserService implements IUserService{

	//Autowired to inject dependency here
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmailSenderService mailService;
	@Autowired
	private TokenUtil util;
	
	//Ability to serve controller's insert user record api call
	public User registerUser(UserDTO userdto) {
		User newUser = new User(userdto);
		userRepo.save(newUser);
		log.info("User got registered");
		String token = util.createToken(newUser.getUserID());
		mailService.sendEmail("ravirenapurkar@gmail.com","Welcome "+newUser.getFirstName(),"Click here \n http://localhost:8080/userdetails//"+token);
		return newUser;
	}
	//Ability to serve controller's retrieve all user records api call
	public List<User> getAllRecords(){
		List<User> 	userList = userRepo.findAll();
		log.info("All Record Retrieved Successfully");
		return userList;
	}
	//Ability to serve controller's retrieve user record by id api call
	public User getRecord(Integer id){
		Optional<User> 	user = userRepo.findById(id);
		if(user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		}
		else {
			log.info("Record retrieved successfully for id "+id);
			return user.get();
		}
	}
	//Ability to serve controller's retrieve user record by token api call
	public User getRecordByToken(String token){
			Integer id = util.decodeToken(token);
			Optional<User> 	user = userRepo.findById(id);
			if(user.isEmpty()) {
				throw new BookStoreException("User Record doesn't exists");
			}
			else {
				log.info("Record retrieved successfully for given token having id "+id);
				return user.get();
			}
		}
	//Ability to serve controller's update user record by id api call
	public User updateRecord(Integer id,UserDTO dto) {
		Optional<User> 	user = userRepo.findById(id);
		if(user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		}
		else {
			User newUser = new User(id,dto);
			userRepo.save(newUser);
			log.info("User data updated successfully");
			String token = util.createToken(newUser.getUserID());
			//mailService.sendEmail(newUser.getEmail(),"Welcome "+newUser.getFirstName(),"Click here \n http://localhost:8080/userdetails//"+token);
			return newUser;
		}
	}
	//Ability to serve controller's user login api call
	public User userLogin(LoginDTO logindto) {
		Optional<User> newUser = userRepo.findByMail(logindto.getEmail());
		if(logindto.getEmail().equals(newUser.get().getEmail()) && logindto.getPassword().equals(newUser.get().getPassword())) {
			log.info("SuccessFully Logged In");
			return newUser.get();
		}
		else {
			
			throw new BookStoreException("User doesn't exists");
			
		}
	}
	//Ability to serve controller's retrieve user record by email api call
	public User getRecordByEmail(String email){
		Optional<User> user = userRepo.findByMail(email);
		if(user.isEmpty()) {
			throw new BookStoreException("User doesn't exists");
		}
		else {
			log.info("Record retrieved successfully for mail "+email);
			return user.get();
		}
	}
	//Ability to serve controller's get token for changing password api call
	public String getToken(String email) {
		Optional<User> user = userRepo.findByMail(email);
		String token = util.createToken(user.get().getUserID());
		//mailService.sendEmail("ravirenapurkar@gmail.com","Welcome "+user.get().getFirstName(),"Token for changing password is :\n"+token);
		log.info("Token sent on mail successfully");
		return token;
	}
	//Ability to serve controller's change password api call
	public User changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
		Optional<User> user = userRepo.findByMail(dto.getEmail());
		String generatedToken = util.createToken(user.get().getUserID());
		//mailService.sendEmail(user.get().getEmail(),"Welcome "+user.get().getFirstName(),token);
		if(user.isEmpty()) {
			throw new BookStoreException("User doesn't exists");
		}
		else {
			if(dto.getToken().equals(generatedToken) ) {
				user.get().setPassword(dto.getNewPassword());
				userRepo.save(user.get());
				log.info("Password changes successfully");
				return user.get();
			}
			else {
				throw new BookStoreException("Invalid token");
			}
		}
	}

}

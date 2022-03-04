package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repositoy.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtil;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepo;
	/*@Autowired
	private EmailSenderService mailService;*/
	@Autowired
	private TokenUtil util;
	
	public User registerUser(UserDTO userdto) {
		User newUser = new User(userdto);
		userRepo.save(newUser);
		String token = util.createToken(newUser.getUserID());
		//mailService.sendEmail(newUser.getEmail(),"Welcome "+newUser.getFirstName(),"Click here \n http://localhost:8080/userdetails//"+token);
		return newUser;
	}
	public List<User> getAllRecords(){
		List<User> 	userList = userRepo.findAll();
		return userList;
	}
	public User getRecord(Integer id){
		Optional<User> 	user = userRepo.findById(id);
		if(user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		}
		else {
			return user.get();
		}
	}
	public User updateRecord(Integer id,UserDTO dto) {
		Optional<User> 	user = userRepo.findById(id);
		if(user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		}
		else {
			User newUser = new User(id,dto);
			userRepo.save(newUser);
			return newUser;
		}
	}
	public User userLogin(LoginDTO logindto) {
		Optional<User> newUser = userRepo.findByMail(logindto.getEmail());
		if(logindto.getEmail().equals(newUser.get().getEmail()) && logindto.getPassword().equals(newUser.get().getPassword())) {
			System.out.println("SuccessFully Logged In");
			return newUser.get();
		}
		else {
			
			throw new BookStoreException("User doesn't exists");
			
		}
	}
	public User getRecordByEmail(String email){
		Optional<User> user = userRepo.findByMail(email);
		if(user.isEmpty()) {
			throw new BookStoreException("User doesn't exists");
		}
		else {
			return user.get();
		}
	}
	public User changePassword(String email,String newPassword,String token) {
		Optional<User> user = userRepo.findByMail(email);
		String generatedToken = util.createToken(user.get().getUserID());
		//mailService.sendEmail(user.get().getEmail(),"Welcome "+user.get().getFirstName(),token);
		if(user.isEmpty()) {
			throw new BookStoreException("User doesn't exists");
		}
		else {
			if(token == generatedToken ) {
				user.get().setPassword(newPassword);
				return user.get();
			}
			else {
				throw new BookStoreException("Invalid token");
			}
		}
	}

}

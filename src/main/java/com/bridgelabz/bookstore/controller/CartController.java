package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.service.ICartService;

//Controller to call api
@RestController
@RequestMapping("/cartdetails")
public class CartController {
	//Autowired ICartService to inject its dependency here
	@Autowired
	private ICartService cartService;
	
	//Ability to call api to insert all cart records
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody CartDTO cartdto){
		Cart newCart = cartService.insertCart(cartdto);
		ResponseDTO dto = new ResponseDTO("User registered successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	//Ability to call api to retrieve all card records
	@GetMapping("/retrieveAllCarts")
	public ResponseEntity<ResponseDTO> getAllCartRecords(){
		List<Cart> newCart = cartService.getAllCartRecords();
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to retrieve cart record by id
	@GetMapping("/retrieveCart/{id}")
	public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
		Cart newCart = cartService.getCartRecord(id);
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to update cart by id
	@PutMapping("/updateCart/{id}")
	public ResponseEntity<ResponseDTO> updateCartRecord(@PathVariable Integer id,@Valid @RequestBody CartDTO cartdto){
		Cart newCart = cartService.updateCartRecord(id,cartdto);
		ResponseDTO dto = new ResponseDTO("Record updated successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to call api to delete cart by id
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<ResponseDTO> deleteCartRecord(@PathVariable Integer id){
		Cart newCart = cartService.deleteCartRecord(id);
		ResponseDTO dto = new ResponseDTO("Record deleted successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to call api to update quantity of book in cart by id
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id,@RequestParam Integer quantity){
		Cart newCart = cartService.updateQuantity(id,quantity);
		ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	
}
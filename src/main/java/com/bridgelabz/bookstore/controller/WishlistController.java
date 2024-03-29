package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.WishlistDTO;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.service.IWishlistService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin
public class WishlistController {
	
	@Autowired
	private IWishlistService wishlistService;
	
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> addToWishlist(@RequestBody WishlistDTO wishlistdto){
		Wishlist wishlist = wishlistService.addToWishlist(wishlistdto);
		ResponseDTO dto = new ResponseDTO("Wishlist added successfully",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/retrieveAll")
	public ResponseEntity<ResponseDTO> getAllWishlists(){
		List<Wishlist> wishlist = wishlistService.getAllWishlists();
		ResponseDTO dto = new ResponseDTO("All Wishlist records retrieved successfully",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<ResponseDTO> getWishlistRecordById(@PathVariable Integer id){
		List<Wishlist> wishlist = wishlistService.getWishlistRecordById(id);
		ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given id",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/retrieveByBookId/{bookId}")
	public ResponseEntity<ResponseDTO> getWishlistRecordByBookId(@PathVariable Integer bookId){
		List<Wishlist> wishlist = wishlistService.getWishlistRecordByBookId(bookId);
		ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given book id",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/retrieveByUserId/{userId}")
	public ResponseEntity<ResponseDTO> getWishlistRecordByUserId(@PathVariable Integer userId){
		List<Wishlist> wishlist = wishlistService.getWishlistRecordByUserId(userId);
		ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given user id",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteWishlistRecordById(@PathVariable Integer id){
		Wishlist wishlist = wishlistService.deleteWishlistRecordById(id);
		ResponseDTO dto = new ResponseDTO("Wishlist record deleted successfully for given id",wishlist);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	

}

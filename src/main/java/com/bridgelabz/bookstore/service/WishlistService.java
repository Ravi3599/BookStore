package com.bridgelabz.bookstore.service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.WishlistDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;

import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.repositoy.BookRepository;
import com.bridgelabz.bookstore.repositoy.UserRepository;
import com.bridgelabz.bookstore.repositoy.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishlistService implements IWishlistService {
	@Autowired
	private WishlistRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Override
	public Wishlist addToWishlist(WishlistDTO dto) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findById(dto.getUserID());
		Optional<com.bridgelabz.bookstore.model.Book> book = bookRepo.findById(dto.getBookID());
		if(user.isPresent() && book.isPresent()) {
			Wishlist newWishList = new Wishlist(user.get(),book.get());
			repo.save(newWishList);
			log.info("Wishlist added successfully");
			return newWishList;
		}
		else {	
			throw new BookStoreException("User or Book doesn't exists");
		}
	}

	@Override
	public List<Wishlist> getAllWishlists() {
		List<Wishlist> list = repo.findAll();
		return list;
	}
	public List<Wishlist> getWishlistRecordById(Integer id){
		List<Wishlist> list = repo.findByWishlistId(id);
		if(list.isEmpty()) {
			throw new BookStoreException("Wishlist doesn't exists for id "+id);
		}
		else {
			return list;
		}
	}

	@Override
	public List<Wishlist> getWishlistRecordByBookId(Integer bookId) {
		List<Wishlist> list = repo.findByBookId(bookId);
		if(list.isEmpty()) {
//			throw new BookStoreException("Wishlist doesn't exists for book id "+bookId);
			return null;
		}
		else {
			return list;
		}
	}
	@Override
	public List<Wishlist> getWishlistRecordByUserId(Integer userId) {
		List<Wishlist> list = repo.findByUserId(userId);
//		if(list.isEmpty()) {
////			throw new BookStoreException("Wishlist doesn't exists for book id "+bookId);
//			return null;
//		}
//		else {
			return list;
		
	}
	
	public Wishlist deleteWishlistRecordById(Integer Id) {	
		Optional<Wishlist> list = repo.findById(Id);
		repo.deleteById(Id);
		return list.get();
	}

	

}

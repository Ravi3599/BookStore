package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.WishlistDTO;
import com.bridgelabz.bookstore.model.Wishlist;

public interface IWishlistService {
	
	public Wishlist addToWishlist(WishlistDTO dto);
	
	public List<Wishlist> getAllWishlists();

	public List<Wishlist> getWishlistRecordById(Integer id);

	public List<Wishlist> getWishlistRecordByBookId(Integer bookId);

	public List<Wishlist> getWishlistRecordByUserId(Integer userId);

	public Wishlist deleteWishlistRecordById(Integer id);
	
	

}

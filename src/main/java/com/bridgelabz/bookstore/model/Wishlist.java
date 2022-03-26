package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bridgelabz.bookstore.dto.WishlistDTO;

import lombok.Data;

@Data
@Entity
public class Wishlist {
	@Id
	@GeneratedValue
	private Integer wishlistId;
	
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	@ManyToOne
	@JoinColumn(name="bookID")
	private Book book;
	
	public Wishlist() {
		
	}
	
	public Wishlist(User user, Book book) {
		super();
		this.user = user;
		this.book = book;
	}
	
	
}

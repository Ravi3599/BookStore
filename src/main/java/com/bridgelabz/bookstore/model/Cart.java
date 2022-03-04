package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bridgelabz.bookstore.dto.CartDTO;

import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue
	private Integer cartID;
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	@ManyToOne
	@JoinColumn(name="bookID")
	private Book book;
	private Integer quantity;
	
	public Cart(Integer cartID,Integer quantity, Book book, User user) {
		super();
		this.cartID= cartID;
		this.quantity = quantity;
		this.book=book;
		this.user=user;
	}
	public Cart(Integer quantity, Book book, User user) {
		super();
		this.quantity = quantity;
		this.book=book;
		this.user=user;
	}
	public Cart() {
		super();
	}	
}

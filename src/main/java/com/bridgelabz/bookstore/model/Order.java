package com.bridgelabz.bookstore.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.repositoy.OrderRepository;

import lombok.Data;

//Order Model with fields
@Entity
@Data
@Table(name="OrderDetails")
public class Order {
	
	@Id
	@GeneratedValue
	private Integer orderID;
	private LocalDate date = LocalDate.now();
	private Integer price;
	private Integer quantity;
	private String address;
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	@ManyToOne
	@JoinColumn(name="bookID")
	private Book book;
	private boolean cancel;
	
	public Order(Integer orderID,Integer price, Integer quantity, String address, Book book, User user, boolean cancel) {
		this.orderID = orderID;
		this.price=price;
		this.quantity=quantity;
		this.address=address;
		this.book = book;
		this.user=user;
		this.cancel = cancel;
	}
	
	public Order() {
		super();
	}
	
	public Order(Integer price, Integer quantity, String address, Book book, User user, boolean cancel) {
		this.price=price;
		this.quantity=quantity;
		this.address=address;
		this.book = book;
		this.user=user;
		this.cancel = cancel;
	}
	
	
}

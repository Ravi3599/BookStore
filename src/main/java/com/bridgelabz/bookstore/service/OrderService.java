package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repositoy.BookRepository;
import com.bridgelabz.bookstore.repositoy.OrderRepository;
import com.bridgelabz.bookstore.repositoy.UserRepository;

import lombok.extern.slf4j.Slf4j;

//Ability to provide service to controller
@Service
@Slf4j
public class OrderService implements IOrderService {
	//Autowired to inject dependency here
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	
	//Ability to serve controller's insert order record api call
	public Order insertOrder(OrderDTO orderdto) {
		Optional<Book>  book = bookRepo.findById(orderdto.getBookID());
		Optional<User> user = userRepo.findById(orderdto.getUserID());
		if(book.isPresent() && user.isPresent()) {
			if(orderdto.getQuantity() < book.get().getQuantity()) {
				Order newOrder = new Order(book.get().getPrice(),orderdto.getQuantity(),orderdto.getAddress(),book.get(),user.get(),orderdto.isCancel());
				orderRepo.save(newOrder);
				book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
				bookRepo.save(book.get());
				log.info("Order record inserted successfully");
				return newOrder;
			}else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}
	//Ability to serve controller's retrieve all order records api call
	public List<Order> getAllOrderRecords(){
		List<Order> orderList =orderRepo.findAll();
		log.info("ALL order records retrieved successfully");
		return orderList;
	}
	//Ability to serve controller's retrieve order record by id api call
	public Order getOrderRecord(Integer id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		}
		else {
			log.info("Order record retrieved successfully for id "+id);
			return order.get();
		}
	}
	//Ability to serve controller's update order record by id api call
	public Order updateOrderRecord(Integer id,OrderDTO dto) {
		Optional<Order> order = orderRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(dto.getBookID());
		Optional<User> user = userRepo.findById(dto.getUserID());
		if(order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		}
		else {
			if(book.isPresent() && user.isPresent()) {
				if(dto.getQuantity() < book.get().getQuantity()) {
					Order newOrder = new Order(id,book.get().getPrice(),dto.getQuantity(),dto.getAddress(),book.get(),user.get(),dto.isCancel());
					orderRepo.save(newOrder);
					log.info("Order record updated successfully for id "+id);
					book.get().setQuantity(book.get().getQuantity() -(dto.getQuantity() -order.get().getQuantity()));
					bookRepo.save(book.get());
					return newOrder;
				}else {
					throw new BookStoreException("Requested quantity is not available");
				}
			}
			else {
				throw new BookStoreException("Book or User doesn't exists");

			}
		}
	}
	//Ability to serve controller's delete order record by id api call
	public Order deleteOrderRecord(Integer id) {
		Optional<Order> order = orderRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(order.get().getBook().getBookID());
		if(order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		}
		else {
			book.get().setQuantity(book.get().getQuantity() + order.get().getQuantity());
			bookRepo.save(book.get());
			orderRepo.deleteById(id);
			log.info("Order record deleted successfully for id "+id);
			return order.get();
		}
	}
}

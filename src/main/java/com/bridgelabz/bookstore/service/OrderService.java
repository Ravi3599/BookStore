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

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	
	public Order insertOrder(OrderDTO orderdto) {
		Optional<Book>  book = bookRepo.findById(orderdto.getBookID());
		Optional<User> user = userRepo.findById(orderdto.getUserID());
		if(book.isPresent() && user.isPresent()) {
			if(orderdto.getQuantity() < book.get().getQuantity()) {
				Order newOrder = new Order(book.get().getPrice(),orderdto.getQuantity(),orderdto.getAddress(),book.get(),user.get(),orderdto.isCancel());
				orderRepo.save(newOrder);
				return newOrder;
			}else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}

	public List<Order> getAllOrderRecords(){
		List<Order> orderList =orderRepo.findAll();
		return orderList;
	}

	public Order getOrderRecord(Integer id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		}
		else {
			return order.get();
		}
	}

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

	public Order deleteOrderRecord(Integer id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		}
		else {
			orderRepo.deleteById(id);
			return order.get();
		}
	}
}

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
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.service.IOrderService;

//Controller to make api calls
@RestController
@RequestMapping("/orderdetails")
public class OrderController {
	//Autowired IOrderService dependency to inject its dependecy here
	@Autowired
	private IOrderService orderService;
	
	//Ability to call api to insert order record
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto){
		Order newOrder = orderService.insertOrder(orderdto);
		ResponseDTO dto = new ResponseDTO("User registered successfully !",newOrder);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	//Ability to call api retrieve all order records
	@GetMapping("/retrieveAllOrders")
	public ResponseEntity<ResponseDTO> getAllOrderRecords(){
		List<Order> newOrder = orderService.getAllOrderRecords();
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",newOrder);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to retrieve order records by id
	@GetMapping("/retrieveOrder/{id}")
	public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
		Order newOrder = orderService.getOrderRecord(id);
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newOrder);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to call api to update order record by id
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id,@Valid @RequestBody OrderDTO orderdto){
		Order newOrder = orderService.updateOrderRecord(id,orderdto);
		ResponseDTO dto = new ResponseDTO("Record updated successfully !",newOrder);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to call api to delete order record by id
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id){
		Order newOrder = orderService.deleteOrderRecord(id);
		ResponseDTO dto = new ResponseDTO("Record deleted successfully !",newOrder);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
}

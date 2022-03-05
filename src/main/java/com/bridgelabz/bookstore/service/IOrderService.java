package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.Order;

//Interface to achieve abstraction
public interface IOrderService {

	public Order insertOrder(OrderDTO orderdto);

	public List<Order> getAllOrderRecords();

	public Order getOrderRecord(Integer id);

	public Order updateOrderRecord(Integer id,OrderDTO dto);

	public Order deleteOrderRecord(Integer id);

}

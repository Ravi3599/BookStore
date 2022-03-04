package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repositoy.BookRepository;
import com.bridgelabz.bookstore.repositoy.CartRepository;
import com.bridgelabz.bookstore.repositoy.UserRepository;

@Service
public class CartService implements ICartService{
	
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	
	public Cart insertCart(CartDTO cartdto) {
		Optional<Book>  book = bookRepo.findById(cartdto.getBookID());
		Optional<User> user = userRepo.findById(cartdto.getUserID());
		if(book.isPresent() && user.isPresent()) {
			Cart newCart = new Cart(cartdto.getQuantity(),book.get(),user.get());
			cartRepo.save(newCart);
			return newCart;
		}
		else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}

	public List<Cart> getAllCartRecords(){
		List<Cart> 	cartList = cartRepo.findAll();
		return cartList;
	}

	public Cart getCartRecord(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			return cart.get();
		}
	}

	public Cart updateCartRecord(Integer id,CartDTO dto) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(dto.getBookID());
		Optional<User> user = userRepo.findById(dto.getUserID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			if(book.isPresent() && user.isPresent()) {
				Cart newCart = new Cart(id,dto.getQuantity(),book.get(),user.get());
				cartRepo.save(newCart);
				return newCart;
			}
			else {
				throw new BookStoreException("Book or User doesn't exists");
			}
		}
	}

	public Cart deleteCartRecord(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			cartRepo.deleteById(id);
			return cart.get();
		}
	}

	public Cart updateQuantity(Integer id, Integer quantity) {
		Optional<Cart> cart = cartRepo.findById(id);
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			cart.get().setQuantity(quantity);
			return cart.get();
		}
	}


}

package com.bridgelabz.bookstore.service;

import java.util.Iterator;
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

import lombok.extern.slf4j.Slf4j;

//Ability to provide service to controller
@Service
@Slf4j
public class CartService implements ICartService{
	
	//Autowired to inject its dependency here
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	
	//Ability to serve to controller's insert cart details api call
	public Cart insertCart(CartDTO cartdto) {
		Optional<Book>  book = bookRepo.findById(cartdto.getBookID());
		Optional<User> user = userRepo.findById(cartdto.getUserID());
		if(book.isPresent() && user.isPresent()) {
			if(cartdto.getQuantity() < book.get().getQuantity()) {
				Cart newCart = new Cart(cartdto.getQuantity(),book.get(),user.get());
				cartRepo.save(newCart);
				log.info("Cart record inserted successfully");
				book.get().setQuantity(book.get().getQuantity() - cartdto.getQuantity());
				bookRepo.save(book.get());
				return newCart;
			}else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}
		else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}
	//Ability to serve to controller's retrieve all cart records api call
	public List<Cart> getAllCartRecords(){
		List<Cart> 	cartList = cartRepo.findAll();
		log.info("All cart records retrieved successfully");
		return cartList;
	}
	//Ability to serve to controller's retrieve cart record by id api call
	public Cart getCartRecord(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			log.info("Cart record retrieved successfully for id "+id);
			return cart.get();
		}
	}
	//Ability to serve to controller's update cart record by id api call
	public Cart updateCartRecord(Integer id,CartDTO dto) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(dto.getBookID());
		Optional<User> user = userRepo.findById(dto.getUserID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			if(book.isPresent() && user.isPresent()) {
				if(dto.getQuantity() < book.get().getQuantity()) {
					Cart newCart = new Cart(id,dto.getQuantity(),book.get(),user.get());
					cartRepo.save(newCart);
					log.info("Cart record updated successfully for id "+id);
					book.get().setQuantity(book.get().getQuantity() -(dto.getQuantity() -cart.get().getQuantity()));
					bookRepo.save(book.get());
					return newCart;
				}
				else {
					throw new BookStoreException("Requested quantity is not available");
				}
			}
			else {
				throw new BookStoreException("Book or User doesn't exists");
			}
		}
	}
	//Ability to serve to controller's delete cart record by id api call
	public Cart deleteCartRecord(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(cart.get().getBook().getBookID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			book.get().setQuantity(book.get().getQuantity() + cart.get().getQuantity());
			bookRepo.save(book.get());
			cartRepo.deleteById(id);
			log.info("Cart record deleted successfully for id "+id);
			return cart.get();
		}
	}
	//Ability to serve to controller's update quantity of books in cart api call
	public Cart updateQuantity(Integer id, Integer quantity) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(cart.get().getBook().getBookID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			if(quantity < book.get().getQuantity()) {
				cart.get().setQuantity(quantity);
				cartRepo.save(cart.get());
				log.info("Quantity in cart record updated successfully");
				book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
				bookRepo.save(book.get());
				return cart.get();
			}
			else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}
	}
	public Cart decreaseQuantity(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(cart.get().getBook().getBookID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			if(cart.get().getQuantity() < book.get().getQuantity()) {
				cart.get().setQuantity(cart.get().getQuantity()-1);
				cartRepo.save(cart.get());
				log.info("Quantity in cart record updated successfully");
				book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()-1) - cart.get().getQuantity()));
				bookRepo.save(book.get());
				return cart.get();
			}
			else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}
	}
	public Cart increaseQuantity(Integer id) {
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Book>  book = bookRepo.findById(cart.get().getBook().getBookID());
		if(cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		}
		else {
			if(cart.get().getQuantity() < book.get().getQuantity()) {
				cart.get().setQuantity(cart.get().getQuantity()+1);
				cartRepo.save(cart.get());
				log.info("Quantity in cart record updated successfully");
				book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()+1) - cart.get().getQuantity()));
				bookRepo.save(book.get());
				return cart.get();
			}
			else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}
	}
	//Ability to serve to controller's retrieve cart record by book id api call
		public Cart getCartRecordByBookId(Integer bookId) {
			Optional<Cart> cart = cartRepo.findByBookId(bookId);
			if(cart.isEmpty()) {
				return null;
				//throw new BookStoreException("Cart Record doesn't exists");
			}
			else {
				log.info("Cart record retrieved successfully for book id "+bookId);
				return cart.get();
			}
		}
		//Ability to serve to controller's retrieve cart record by book id api call
			public List<Cart> getCartRecordByUserId(Integer userId) {
				List<Cart> cart = cartRepo.findByUserId(userId);
//				if(cart.isEmpty()) {
//					//return null;
//					//throw new BookStoreException("Cart Record doesn't exists");
//				}
//				else {
					log.info("Cart record retrieved successfully for book id "+userId);
					return cart;
				//}
			}
		@Override
		public List<Cart> getCartRecordByUserAndBookId(Integer userID, Integer bookID) {
//			List<Cart> cart = cartRepo.findByUserId(userID);
//			for(int i=0; i<10;i++) {
//			if(cart.get(i).getBook().getBookID().equals(bookID) && cart.get(i).getUser().getUserID().equals(userID)) {
				List<Cart> newCart = cartRepo.findByUserAndBookId(userID,bookID);
				if(newCart.isEmpty()) {
					return null;
				}
				return newCart;
			
//			else {
//				return null;
////				throw new BookStoreException("Cart record doesn't exists for given UserId and BookId");
//			}
		}


}

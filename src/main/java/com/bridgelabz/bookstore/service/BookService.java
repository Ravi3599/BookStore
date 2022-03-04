package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repositoy.BookRepository;
import com.bridgelabz.bookstore.repositoy.UserRepository;

@Service
public class BookService implements IBookService {
	@Autowired
	private BookRepository bookRepo;
	
	public Book insertBook(BookDTO bookdto) {
		Book newBook = new Book(bookdto);
		bookRepo.save(newBook);
		return newBook;
	}

	public List<Book> getAllBookRecords(){
		List<Book> 	bookList =bookRepo.findAll();
		return bookList;
	}

	public Book getBookRecord(Integer id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			return book.get();
		}
	}
	public Book updateBookRecord(Integer id,BookDTO dto) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			Book newBook = new Book(id,dto);
			bookRepo.save(newBook);
			return newBook;
		}
		
	}

	public Book getRecordByBookName(String bookName) {
		Optional<Book> book = bookRepo.findByBookName(bookName);
		if(book.isEmpty()) {
			throw new BookStoreException("Book doesn't exists");
		}
		else {
			return book.get();
		}
	}

	public Book deleteBookRecord(Integer id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			bookRepo.deleteById(id);
			return book.get();
		}
	}

	public List<Book> sortRecordDesc(){
		List<Book> listOfBooks = bookRepo.sortBooksAsc();
		return listOfBooks;
	}

	public List<Book> sortRecordAsc(){
		List<Book> listOfBooks = bookRepo.sortBooksDesc();
		return listOfBooks;
	}

	public Book updateQuantity(Integer id, Integer quantity) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			book.get().setQuantity(quantity);
			return book.get();
		}
	}
}

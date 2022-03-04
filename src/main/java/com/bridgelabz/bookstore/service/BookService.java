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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService implements IBookService {
	@Autowired
	private BookRepository bookRepo;
	
	public Book insertBook(BookDTO bookdto) {
		Book newBook = new Book(bookdto);
		bookRepo.save(newBook);
		log.info("Book record inserted successfully");
		return newBook;
	}

	public List<Book> getAllBookRecords(){
		List<Book> 	bookList =bookRepo.findAll();
		log.info("All book records retrieved successfully");
		return bookList;
	}

	public Book getBookRecord(Integer id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			log.info("Book record retrieved successfully for id "+id);
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
			log.info("Book record updated successfully for id "+id);
			return newBook;
		}
		
	}

	public Book getRecordByBookName(String bookName) {
		Optional<Book> book = bookRepo.findByBookName(bookName);
		if(book.isEmpty()) {
			throw new BookStoreException("Book doesn't exists");
		}
		else {
			log.info("Book record retrieved successfully for Book Name : "+bookName);
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
			log.info("Book record deleted successfully for id "+id);
			return book.get();
		}
	}

	public List<Book> sortRecordDesc(){
		List<Book> listOfBooks = bookRepo.sortBooksDesc();
		log.info("Book records sorted in descending order by bookName successfully");
		return listOfBooks;
	}

	public List<Book> sortRecordAsc(){
		List<Book> listOfBooks = bookRepo.sortBooksAsc();
		log.info("Book records sorted in ascending order by bookName successfully");
		return listOfBooks;
	}

	public Book updateQuantity(Integer id, Integer quantity) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		}
		else {
			book.get().setQuantity(quantity);
			bookRepo.save(book.get());
			log.info("Quantity for book record updated successfully for id "+id);
			return book.get();
		}
	}
}

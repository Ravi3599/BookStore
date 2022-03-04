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
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;

@RestController
@RequestMapping("/bookdetails")
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookdto){
		Book newBook = bookService.insertBook(bookdto);
		ResponseDTO dto = new ResponseDTO("User registered successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	@GetMapping("/retrieveAllBooks")
	public ResponseEntity<ResponseDTO> getAllBookRecords(){
		List<Book> newBook = bookService.getAllBookRecords();
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/retrieveBooks/{id}")
	public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
		Book newBook = bookService.getBookRecord(id);
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id,@Valid @RequestBody BookDTO bookdto){
		Book newBook = bookService.updateBookRecord(id,bookdto);
		ResponseDTO dto = new ResponseDTO("Record updated successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/retrieve/{bookName}")
	public ResponseEntity<ResponseDTO> getRecordByBookName(@PathVariable String bookName){
		Book newBook = bookService.getRecordByBookName(bookName);
		ResponseDTO dto = new ResponseDTO("Record for particular book retrieved successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<ResponseDTO> deleteBookRecord(@PathVariable Integer id){
		Book newBook = bookService.deleteBookRecord(id);
		ResponseDTO dto = new ResponseDTO("Record deleted successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/sortAsc")
	public ResponseEntity<ResponseDTO> sortRecordAsc(){
		List<Book> newBook = bookService.sortRecordAsc();
		ResponseDTO dto = new ResponseDTO("Records for book sorted in ascending order successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@GetMapping("/sortDesc")
	public ResponseEntity<ResponseDTO> sortRecordDesc(){
		List<Book> newBook = bookService.sortRecordDesc();
		ResponseDTO dto = new ResponseDTO("Records for book sorted in descending order successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id,@RequestParam Integer quantity){
		Book newBook = bookService.updateQuantity(id,quantity);
		ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newBook);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	
}

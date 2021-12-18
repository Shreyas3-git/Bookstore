package com.bridgelabz.bookstore.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.book.dto.BookDetailsDTO;
import com.bridgelabz.bookstore.book.dto.ResponseDTO;
import com.bridgelabz.bookstore.book.entity.BookDetails;
import com.bridgelabz.bookstore.book.service.IBookService;


@EnableAutoConfiguration
@RestController
@CrossOrigin
public class BookDetailsController 
{
	@Autowired
	private IBookService bookService;
	
	@GetMapping("/getbooks")
	public ResponseEntity<ResponseDTO> getAllBooks(@RequestParam String emailId) 
	{
		List<BookDetails> allBooks = bookService.retriveAllBooks(emailId);
		ResponseDTO dto = new ResponseDTO("All Books retrived sucessfully:",allBooks);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	 
	@GetMapping("/getBook")
	public ResponseEntity<ResponseDTO> getOneBook(@RequestParam String bookName,@RequestParam String emailId) 
	{
		BookDetails getOneBook = bookService.getBookByName(bookName,emailId);
		ResponseDTO dto = new ResponseDTO("Book retrived sucessfully for name =>"+bookName, getOneBook);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/bookCount")
	public ResponseEntity<ResponseDTO> getBookCount()
	{
		int bookCount = bookService.getCountOfBooks();
		ResponseDTO dto = new ResponseDTO("Book count will be:",bookCount);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	
		
	@GetMapping("/decending")
	public ResponseEntity<ResponseDTO> bookWithHigherToLowerOrder(@RequestParam String emailId)
	{
		List<BookDetails> highToLowOrder = bookService.getBookhigherTolowerPresidence(emailId);
		ResponseDTO dto = new ResponseDTO("getAll Books Higher order to lower order:", highToLowOrder);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/ascending")
	public ResponseEntity<ResponseDTO> bookWithLowerOrderToHigher(@RequestParam String emailId)
	{
		List<BookDetails> lowTohighOrder = bookService.getBooklowerTohigherPresidence(emailId);
		ResponseDTO dto = new ResponseDTO("getAll Books Lower order to Higher order:", lowTohighOrder);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}

	
	@PostMapping("/addBook")
	public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDetailsDTO bookDto,@RequestParam String emailId)
	{
		BookDetails addBook = bookService.insertBook(emailId,bookDto);
		System.out.println(addBook);
		ResponseDTO dto = new ResponseDTO("Book added sucessfully ", addBook);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}			
	
	@PutMapping("/updateBook")
	public ResponseEntity<ResponseDTO> updateBook(@RequestParam String emailId,@RequestParam String bookName,@RequestBody BookDetailsDTO bookDto)
	{
		Optional<BookDetails> updateBook = bookService.updateBook(emailId,bookName,bookDto);		
		ResponseDTO dto = new ResponseDTO("Book updated sucessfully ", updateBook);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updatebook/{price}")
	public ResponseEntity<ResponseDTO> updatePrice(@RequestParam String emailId,@RequestParam String bookName,@PathVariable double price)
	{
		Optional<BookDetails> updateBook = bookService.updateBookPrice(emailId,price,bookName);		
		ResponseDTO dto = new ResponseDTO("Book price updated sucessfully ", updateBook);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updatebook/{quantity}")
	public ResponseEntity<ResponseDTO> updatePrice(@RequestParam String emailId,@RequestParam String bookName,@PathVariable int quantity)
	{
		Optional<BookDetails> updateBook = bookService.updateBookQuantity(emailId,quantity,bookName);		
		ResponseDTO dto = new ResponseDTO("Book quantity updated sucessfully ", updateBook);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<ResponseDTO> deleteBook(@RequestParam String emailId,@RequestParam String bookName)
	{
		Optional<BookDetails> deleteBookByName = bookService.deleteBook(emailId,bookName);
		ResponseDTO dto = new ResponseDTO("Book deleted sucessfully : ", deleteBookByName);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
}

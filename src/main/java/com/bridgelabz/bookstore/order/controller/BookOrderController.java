package com.bridgelabz.bookstore.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.order.dto.BookOrderDTO;
import com.bridgelabz.bookstore.order.dto.ResponseDTO;
import com.bridgelabz.bookstore.order.entity.BookOrder;
import com.bridgelabz.bookstore.order.service.BookOrderService;

import java.util.List;


@RestController
public class BookOrderController 
{
	@Autowired
	private BookOrderService bookService;
	
	@GetMapping("/getorder")
	public ResponseEntity<ResponseDTO> getOrderDetails(@RequestParam String emailId)
	{
		List<BookOrder> order = bookService.getOrder(emailId);
		ResponseDTO dto = new ResponseDTO("all order data fetch successfully:",order);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	
	@PostMapping("/placeorder")
	public ResponseEntity<ResponseDTO> purchaseOrder(@RequestBody BookOrderDTO orderDTO,@RequestParam String emailId)
	{
		BookOrder placeOrder = bookService.placeOrder(orderDTO,emailId);
		ResponseDTO dto = new ResponseDTO("new order placed successfully:",placeOrder);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cancelorder/{id}")
	public ResponseEntity<ResponseDTO> removeOrder(@RequestParam String emailId,@PathVariable long id)
	{
		BookOrder cancelOrder = bookService.cancelOrder(emailId,id);
		ResponseDTO dto = new ResponseDTO("order cancel successfully:",cancelOrder);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
		
	}
}

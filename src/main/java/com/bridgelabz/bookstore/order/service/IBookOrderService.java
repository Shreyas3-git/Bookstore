package com.bridgelabz.bookstore.order.service;

import java.util.List;

import com.bridgelabz.bookstore.order.dto.BookOrderDTO;
import com.bridgelabz.bookstore.order.entity.BookOrder;


public interface IBookOrderService 
{
	public List<BookOrder> getOrder(String emailId);
	public BookOrder placeOrder(BookOrderDTO dto,String emailId);
	public BookOrder cancelOrder(String emailId,long id);
}

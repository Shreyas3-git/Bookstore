package com.bridgelabz.bookstore.order.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.order.dto.BookOrderDTO;
import com.bridgelabz.bookstore.order.entity.BookOrder;
import com.bridgelabz.bookstore.order.repository.IOrderRepository;
import com.bridgelabz.bookstore.order.tokenutils.TokenUtil;


@Service
public class BookOrderService implements IBookOrderService
{
	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<BookOrder> getOrder(String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (isuserPresent)
		{
			List<BookOrder> order = orderRepository.findAll();
			return order;
		}
		return null;
	}

	@Override
	public BookOrder placeOrder(BookOrderDTO dto,String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent)
		{
			return null;
		}
		BookOrder order = new BookOrder(dto);
		
		order.setAddress(dto.getAddress());
		order.setOrderDate(dto.getOrderDate().now());
		order.setPrice(dto.getPrice());
		order.setQuantity(dto.getQuantity());
		orderRepository.save(order);
		return order;
	}

	@Override
	public BookOrder cancelOrder(String emailId,long id) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent)
		{
			return null;
		}

		orderRepository.deleteById(id);
		return null;
	}
	
	
}

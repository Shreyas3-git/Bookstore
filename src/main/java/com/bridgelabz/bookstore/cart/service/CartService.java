package com.bridgelabz.bookstore.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.cart.dto.CartItemDTO;
import com.bridgelabz.bookstore.cart.entity.CartItem;
import com.bridgelabz.bookstore.cart.repository.ICartRepository;
import com.bridgelabz.bookstore.cart.tokenutils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService implements ICartService
{
	
	@Autowired
	private ICartRepository cartRepository; 
	
	@Autowired
	private TokenUtil tokenUtils;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CartItem cart;
	
	@Override
	public List<CartItem> getCart(String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (isuserPresent) 
		{
			List<CartItem> addedbooks = cartRepository.findAll();
			return addedbooks;
		}
		return null;
	}

	@Override
	public CartItem addToCart(CartItemDTO dto,String emailId,String bookName) 
	{
		
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if(!isuserPresent)
		{
			cart.setBookName(dto.getBookName());
			cart.setBookAuthor(dto.getBookAuthor());
			cart.setBookDescirption(dto.getBookDescirption());
			cart.setBookImage(dto.getBookImage());
			cart.setBookPrice(dto.getBookPrice());
			cart.setQuantity(dto.getQuantity());
			cartRepository.save(cart);
			return cart;
		}
		Optional<CartItem> searchForBook = cartRepository.findByBookName(bookName);
		log.error("user already exist try with different emailId");
		return null;
		
	}

	@Override
	public CartItem updateCartItem(String emailId, String bookName, CartItemDTO dto) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) {
			return null;
		}
		Optional<CartItem> isbookPresentInCart = cartRepository.findByBookName(bookName);
		if (isbookPresentInCart.isPresent()) 
		{
			cart.setBookAuthor(dto.getBookAuthor());
			cart.setBookDescirption(dto.getBookDescirption());
			cart.setBookName(dto.getBookName());
			cart.setBookPrice(dto.getBookPrice());
			cart.setPriceWithoutDiscount(dto.getPriceWithoutDiscount());
			cart.setQuantity(dto.getQuantity());
			cartRepository.save(cart);
			return cart;
		}
		return null;
	}

	@Override
	public CartItem deleteCartItem(String bookName,String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) {
			log.error("User not found exception:");
			return null;
		}

		Optional<CartItem> cart = cartRepository.findByBookName(bookName);
		if (cart.isPresent()) 
		{
			cartRepository.delete(cart.get());
			return null;
		}
		log.error("Book not found Exception:");
			return null;
	}


}




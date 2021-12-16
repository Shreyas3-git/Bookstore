package com.bridgelabz.bookstore.cart.service;

import java.util.List;

import com.bridgelabz.bookstore.cart.dto.CartItemDTO;
import com.bridgelabz.bookstore.cart.entity.CartItem;


public interface ICartService 
{
	public abstract List<CartItem> getCart(String emailId);
	public abstract CartItem addToCart(CartItemDTO dto,String emailId,String bookName);
	public abstract CartItem deleteCartItem(String bookName,String emailId);
	public abstract CartItem updateCartItem(String emailId,String bookName,CartItemDTO dto);
}

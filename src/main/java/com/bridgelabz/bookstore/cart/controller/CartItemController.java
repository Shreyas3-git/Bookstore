package com.bridgelabz.bookstore.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.cart.dto.CartItemDTO;
import com.bridgelabz.bookstore.cart.dto.ResponseDTO;
import com.bridgelabz.bookstore.cart.entity.CartItem;
import com.bridgelabz.bookstore.cart.service.ICartService;

@RestController
public class CartItemController 
{

	@Autowired
	private ICartService cartService;
	
	@GetMapping("/getcart")
	public ResponseEntity<ResponseDTO> getAllItemsInCart(@RequestParam String emailId)
	{
		List<CartItem> retriveAll = cartService.getCart(emailId);
		ResponseDTO dto = new ResponseDTO("retrived all items in a cart successfully:",retriveAll);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<ResponseDTO> addItemInCart(@RequestBody CartItemDTO dto,@RequestParam String emailId,@RequestParam String bookName)
	{
		CartItem addCart = cartService.addToCart(dto, emailId,bookName);
		ResponseDTO response = new ResponseDTO("item added in a cart successfully:",addCart);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updatecart")
	public ResponseEntity<ResponseDTO> updateCart(@RequestParam String emailId,@RequestParam String bookName,@RequestBody CartItemDTO dto)
	{
		CartItem cartItem = cartService.updateCartItem(emailId, bookName, dto);
		ResponseDTO response = new ResponseDTO("cart updated successfully:",dto);
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteitem")
	public ResponseEntity<ResponseDTO> removeCartItem(@RequestParam String name,@RequestHeader(value = "token", required = false) String token)
	{
		CartItem deleteItem = cartService.deleteCartItem(name,token);
		ResponseDTO dto = new ResponseDTO("Item in cart deleted successfully for name =>" +name,deleteItem);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
}

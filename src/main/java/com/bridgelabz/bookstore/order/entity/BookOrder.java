package com.bridgelabz.bookstore.order.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgelabz.bookstore.order.dto.BookOrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "orderDate")
	private LocalDate orderDate;
	
	private int quantity;
	private double price;
	private String address;
	

	public BookOrder(BookOrderDTO dto)
	{
		orderDate = LocalDate.now();
		quantity = dto.getQuantity();
		price = dto.getPrice();
		address = dto.getAddress();
	}
}

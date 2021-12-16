package com.bridgelabz.bookstore.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.cart.entity.CartItem;


@Repository
public interface ICartRepository extends JpaRepository<CartItem,Integer>
{
	public Optional<CartItem> findByBookName(String bookName);
}

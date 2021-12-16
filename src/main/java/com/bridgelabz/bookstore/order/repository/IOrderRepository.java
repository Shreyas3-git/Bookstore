package com.bridgelabz.bookstore.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.order.entity.BookOrder;

@Repository
public interface IOrderRepository extends JpaRepository<BookOrder,Long>
{
//	public Optional<BookOrder> findByBookName(String bookName);

}

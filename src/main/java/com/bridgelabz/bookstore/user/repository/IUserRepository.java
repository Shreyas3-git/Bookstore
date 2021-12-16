package com.bridgelabz.bookstore.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstore.user.entity.BookUserDetails;


@Repository
public interface IUserRepository extends JpaRepository<BookUserDetails,Long>
{
	public Optional<BookUserDetails> findByEmailIdAndPassword(String emailId,String password);
	public Optional<BookUserDetails> findByEmailId(String emailId);
}

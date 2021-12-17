package com.bridgelabz.bookstore.user.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.bookstore.user.entity.BookUserDetails;
import com.bridgelabz.bookstore.user.dto.BookUserDetailsDTO;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import com.bridgelabz.bookstore.user.dto.LoginDTO;
import com.bridgelabz.bookstore.user.dto.ForgotPasswordDTO;

public interface IUserService 
{
	boolean verifyUser(String emailId);
	List<BookUserDetails> findAllUsers(String Token);
	BookUserDetails registerNewUser(BookUserDetailsDTO dto);
	ResponseDTO UserLogin(LoginDTO dto);
	Optional<BookUserDetails> updateUser(BookUserDetailsDTO dto,String token);
	Optional<BookUserDetails> forgotPassword(ForgotPasswordDTO dto);
	Optional<BookUserDetails> deleteUser(String token);
}

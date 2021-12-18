package com.bridgelabz.bookstore.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.user.dto.BookUserDetailsDTO;
import com.bridgelabz.bookstore.user.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.user.dto.LoginDTO;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import com.bridgelabz.bookstore.user.entity.BookUserDetails;
import com.bridgelabz.bookstore.user.repository.IUserRepository;
import com.bridgelabz.bookstore.user.tokenutils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements IUserService
{
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	TokenUtil tokenUtils;	
	
	
	@Override
	public boolean verifyUser(String emailId) 
	{
		Optional<BookUserDetails> isuserPresent = userRepository.findByEmailId(emailId);
		if (isuserPresent.isPresent()) 
		{
			isuserPresent.get().setVerify(true);
			userRepository.save(isuserPresent.get());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public List<BookUserDetails> findAllUsers(String Token) 
	{
		long id = tokenUtils.decodeToken(Token);
		Optional<BookUserDetails> isUserPresent = userRepository.findById(id);
		if (isUserPresent.isPresent()) 
		{
			List<BookUserDetails> bookUser = userRepository.findAll();
			return bookUser;
		} 
		else 
		{
			return null;
		}
	}

	
	@Override
	public BookUserDetails registerNewUser(BookUserDetailsDTO dto) 
	{		
		Optional<BookUserDetails> users = userRepository.findByEmailId(dto.emailId);
		if (users.isPresent()) 
		{
			log.error("user already present Exception:");
			return null;
		}
		else
		{
			BookUserDetails user = new BookUserDetails();
			user.setFullName(dto.getFullName());
			user.setAddress(dto.getAddress());
			user.setEmailId(dto.getEmailId());
			user.setPassword(dto.getPassword());
			user.setMobile(dto.getMobile());
			user.setRegisterDate(LocalDate.now());
			tokenUtils.createToken(user.getId());
			userRepository.save(user);
			return user;
		}
	}

	@Override
	public ResponseDTO UserLogin(LoginDTO dto) 
	{
		Optional<BookUserDetails> user = userRepository.findByEmailIdAndPassword(dto.emailId, dto.password);
		if (user.isPresent()) 
		{
			String token = tokenUtils.createToken(user.get().getId());
			log.info("user logged in successfully");
			return new ResponseDTO("user login sucessful:",token);
		}
		else 
		{
			log.error("User not Found Exception:");
			return new ResponseDTO("User not found",null);
		}

	}

	@Override
	public Optional<BookUserDetails> updateUser(BookUserDetailsDTO dto,String token) 
	{
		long id = tokenUtils.decodeToken(token);
		Optional<BookUserDetails> user = userRepository.findById(id);
		if (user.isPresent()) 
		{			
			user.get().setFullName(dto.getFullName());
			user.get().setMobile(dto.getMobile());
			user.get().setEmailId(dto.getEmailId());
			user.get().setAddress(dto.getAddress());
			user.get().setUpdatedDate(LocalDate.now());
			userRepository.save(user.get());
			return user;
		}
		else 
		{
			log.error("User not found Exception:");
			return null;
		}
	}

	@Override
	public Optional<BookUserDetails> forgotPassword(ForgotPasswordDTO dto) 
	{
		if (dto.getNewPassword().equals(dto.getConfirmPassword())) 
		{
			Optional<BookUserDetails> user = userRepository.findByEmailId(dto.getEmailId());
			user.get().setPassword(dto.getNewPassword());
			user.get().setUpdatedDate(LocalDate.now());
			userRepository.save(user.get());
			return user;
			
		}
		else
		{
			log.error("Entered password is not correct:");
			return null;
		}
	}

	@Override
	public Optional<BookUserDetails> deleteUser(String token) 
	{
		long id = tokenUtils.decodeToken(token);
		System.out.println("id checked"+id);
		Optional<BookUserDetails> deleteuser = userRepository.findById(id);
		if (deleteuser.isPresent()) 
		{
			userRepository.delete(deleteuser.get());
			return null;
		}
		else 
		{
			log.error("User not found Exception:");
			return null;
		}
	}

	
}

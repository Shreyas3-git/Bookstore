package com.bridgelabz.bookstore.user.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.user.dto.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_list")
@Data
@NoArgsConstructor
public class BookUserDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_Id")
	private long id;
	private String fullName;
	@Column(name = "user_DOB")
	private LocalDate dob;
	private String address;
	@Column(name = "register_date")
	private LocalDate registerDate;
	@Column(name = "updated_date")
	private LocalDate updatedDate ;
	private String emailId;
	private String password;
	private String mobile;
	private boolean verify;
	/**
	 * Conturctor for updating exiting user Data
	 */
	public BookUserDetails(long id, String fullName, LocalDate dob, String address, LocalDate registerDate,
			LocalDate updatedDate, String emailId, String password, String mobile, boolean verify) 
	{
		super();
		this.id = id;
		this.fullName = fullName;
		this.dob = dob;
		this.address = address;
		this.registerDate = registerDate;
		this.updatedDate = updatedDate;
		this.emailId = emailId;
		this.password = password;
		this.mobile = mobile;
		this.verify = verify;
	}

	
	/**
	 * Constructor for Creating new user via registration
	 * @param dto ---> geting data from userdto and setting-up values here for creation
	 */
	
	public BookUserDetails(BookUserDetailsDTO dto)
	{
		this.id = id;
		this.fullName = dto.getFullName();
		this.dob = dto.getDob();
		this.address = dto.getAddress();
		this.registerDate = LocalDate.now();
		this.emailId = dto.getEmailId();
		this.password = dto.getPassword();
		this.mobile = dto.getMobile();
	}
	
	/**
	 * Resting user Password
	 * @param user ----> settingup previous view as it is
	 * @param newPassword -----> settingup new password by this variable
	 */
	
	public BookUserDetails(BookUserDetails user,String password)
	{
		this.id = user.id;
		this.fullName = user.fullName;
		this.dob = user.dob;
		this.address = user.address;
		this.registerDate = user.registerDate;
		this.updatedDate = user.registerDate;
		this.emailId = user.emailId;
		this.password = password;
		this.verify = user.verify;
	}

}

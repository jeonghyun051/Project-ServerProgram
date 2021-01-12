package com.cos.blog2.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

	private int id;
	private String username;
	private String name;
	private String password;
	private int age;
	private String address;
	private int residentnumber;
	private String userRole;
	private Timestamp createDate; 
}

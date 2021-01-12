package com.cos.blog2.domain;

import lombok.Data;

@Data
public class JoinReqDto {

	private String username;
	private String name;
	private String password;
	private int age;
	private String address;
	private int residentnumber;
	private String role;
}

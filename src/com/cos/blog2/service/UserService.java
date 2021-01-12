package com.cos.blog2.service;

import java.util.List;

import com.cos.blog2.domain.JoinReqDto;
import com.cos.blog2.domain.LoginReqDto;
import com.cos.blog2.domain.User;
import com.cos.blog2.domain.UserDao;

public class UserService {
	private UserDao userDao; 
	public UserService() {
		userDao = new UserDao();
	}
	
	public int 회원가입(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}

	public int 유저네임중복체크(String username) {
		int result = userDao.findByUsername(username);
		return result;
	}
	
	public List<User> 유저목록보기(int page) {
		return userDao.findAll(page);

	}
	
	public int 삭제하기 (String username) {
		return userDao.deleteByUsername(username);
	}
	
	public User 로그인(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}

}

package com.cos.blog2.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog2.domain.JoinReqDto;
import com.cos.blog2.domain.LoginReqDto;
import com.cos.blog2.domain.User;
import com.cos.blog2.service.UserService;
import com.cos.blog2.util.Script;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		if (cmd.equals("loginForm")) {
			
			RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			
			User userEntity = userService.로그인(dto);
			if (userEntity != null) {
			
				if (userEntity.getUserRole().equals("user")) {
					HttpSession session = request.getSession();
					session.setAttribute("principal", userEntity); // 인증주체
					
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
				} else if (userEntity.getUserRole().equals("admin")) {
					HttpSession session = request.getSession();
					session.setAttribute("principalAdmin", userEntity); // 인증주체
					
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
				}
//				
				//response.sendRedirect("index.jsp");
			}
		} else if (cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page);
			List<User> users = userService.유저목록보기(page); 
			request.setAttribute("userList", users);
			RequestDispatcher dis = request.getRequestDispatcher("user/list.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("joinForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/joinForm.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			int residentnumber = Integer.parseInt(request.getParameter("residentnumber"));
			String address = request.getParameter("address");
			String role = request.getParameter("role");
			
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setName(name);
			dto.setAge(age);
			dto.setResidentnumber(residentnumber);
			dto.setAddress(address);
			dto.setRole(role);
			
			
			int result = userService.회원가입(dto);
			if (result == 1) {
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
				//response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "회원가입 실패");

			}
			
		}else if (cmd.equals("usernameCheck")) {
			BufferedReader br = request.getReader();
			String username = br.readLine();
			System.out.println(username);
			int result = userService.유저네임중복체크(username);
			PrintWriter out = response.getWriter();
			if (result == 1) {
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();

		}else if(cmd.equals("delete")) {
			String username = request.getParameter("username");
			int result = userService.삭제하기(username);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(result);
				out.flush(); // 버퍼 비우기
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
			//response.sendRedirect("index.jsp");
		}	
}
}

package com.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.UserDAO;
import com.user.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDAO dao;
   
    public UserServlet() {
        super();
        
    }
    
    public void init() {
    	
    	dao = new UserDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action =request.getServletPath();
		switch(action)
		{
		case "/new": showNewForm(request, response); break;
		case "/insert": insertUser(request, response); break;
		case "/list": listUser(request, response); break;
		case "/login": login(request, response); break;
		case "/loginprocess": 
			try {
				loginProcess(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
		case "/logout": logout(request, response); break;
		}
		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	    dispatcher.forward(request, response);

	}
	
	public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException 
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		
		try(Connection connection = userDAO.getConnection())
		{
			PreparedStatement preparedStatement = connection.prepareStatement("Select * from users where email=? and password=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				HttpSession httpSession=(HttpSession) request.getSession();
				httpSession.setAttribute("status", "active");
				httpSession.setAttribute("email", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			    dispatcher.forward(request, response);

			}
			else
			{
				HttpSession httpSession=(HttpSession) request.getSession();
				httpSession.setAttribute("status", "inactive");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		
		}
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession httpSession=(HttpSession) request.getSession();
		httpSession.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);

	}
	public void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
    dispatcher.forward(request, response);
}

	public void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String role=request.getParameter("role");
		User user = new User(0, name, email, role, role, 0, role);
		dao.insertUser(user);
	
		response.sendRedirect("list");

	}
	
	public void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<User> users = dao.selectAllUsers();
		request.setAttribute("user", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.UserSystem;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserSystem users=new UserSystem(); 
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String destination = "index.jsp";
		if (command.equals("hello")) {
			destination = hello(request, response);
		}
		else if (command.equals("helloagain")) {
			destination = helloagain(request, response);
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private String helloagain(HttpServletRequest request, HttpServletResponse response) {
		Person person = (Person) request.getSession().getAttribute("user");
		person.setPassword("ttttttt");
		return "helloagain.jsp";
	}

	private String hello(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Person person = users.getUserWithName(name);
		// without sessions
		//request.setAttribute("user", person);
		// with sessions
		request.getSession().setAttribute("user", person);
		return "hello.jsp";
	}
	
}

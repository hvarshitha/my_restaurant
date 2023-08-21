package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.MyDao;
import DTO.Customer;

@WebServlet(urlPatterns = "/signup")
@MultipartConfig
public class Signup extends HttpServlet {
	private String country;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//name
		String name = req.getParameter("username");
//		resp.getWriter().print("<h1 style = 'color:red'> Name : " + name + "</h1>");
		
		//email
		String email = req.getParameter("email");
//		resp.getWriter().print("<h1> email : " + email + "</h1>");
		
		//password
		String password = req.getParameter("password");
//		resp.getWriter().print("<h1> password : " + password + "</h1>");
		
		//confirm password
		long mobile = Long.parseLong(req.getParameter("mobile"));
//		resp.getWriter().print("<h1> mobile : " + mobile + "</h1>");
		
		//DOB
		LocalDate date = LocalDate.parse(req.getParameter("date"));
//		resp.getWriter().print("<h1> date : " + date + "</h1>");
		
		//calculating the age
		int age = Period.between(date, LocalDate.now()).getYears();
//		resp.getWriter().print("<h1> age : " + age + "</h1>");
		
		//Gender
		String gender = req.getParameter("gender");
//		resp.getWriter().print("<h1> gender : " + gender + "</h1>");
		
		//logic for storing the photo
		Part pic = req.getPart("picture");
		byte picture[] = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		MyDao dao=new MyDao();
		
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null)
		{
		
		Customer c = new Customer();
	c.setAge(age);
	c.setDob(date);
	c.setEmail(email);
	c.setFullName(name);
	c.setMobile(mobile);
	c.setPassword(password);
	c.setPicture(picture);
	c.setCountry(country);
	
	dao.save(c);
	
	resp.getWriter().print("<h1 style='color:red'>Account Create Successfully</h1>");
	}
		else {
			resp.getWriter().print("<h1 style='color:brown'>Email and Mobile should be unique</h1>");

	}
}
}

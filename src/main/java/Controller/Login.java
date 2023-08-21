package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MyDao;
import DTO.Customer;

//mapping the url
@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	receive values from front end
		String email = req.getParameter("email");
		String password = req.getParameter("password");

//	verify if email exists
		MyDao dao = new MyDao();
		if (email.equals("admin@jsp.com") && password.equals("admin")) {
			resp.getWriter().print("<h1 style='color:green'>login Suceessfully</h1>");
//		this is logic to send to nextpage
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		} else {
			Customer customer = dao.fetchByEmail(email);
			if (customer == null) {
				resp.getWriter().print("<h1 style='color:blue'>Invaild email</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);

			} else {
				if (password.equals(customer.getPassword())) {
					resp.getWriter().print("<h1 style='color:green'>login Suceessfully</h1>");
//			this is logic to send to next page
					req.getRequestDispatcher("CustomerHome.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:green'>invalid password</h1>");
					req.getRequestDispatcher("login.html").include(req, resp);

				}
			}
		}
	}

}

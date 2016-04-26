package fabflix;
import fabflix.QueryToDatabase;
import fabflix.Cart;
import java.io.*; 
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;



@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
	
    public Login() {
        super();
    }
  
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");    // Response mime type
        session = request.getSession(true);
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		
		boolean checker = QueryToDatabase.login(email, password);
		//Customer customer = (Customer) session.getAttribute("customer");
		if(checker == false){
			response.sendRedirect("loginFail.html");
		}else{
			if(valid){
				response.sendRedirect("Main.html");
				//session.setAttribute("customer", customer);
				session.setAttribute("cart", new Cart());
			}
			else{
				response.sendRedirect("loginRecaptcha.html");
			}
		}
	}

	
}

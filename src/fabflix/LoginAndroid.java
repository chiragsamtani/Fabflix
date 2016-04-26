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
@WebServlet("/LoginAndroid")
public class LoginAndroid extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
	
    public LoginAndroid() {
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
		
		boolean checker = QueryToDatabase.login(email, password);
		
		if(checker == false){
			out.println("fail");
		}
		else{
			out.println("success");
		}	
		
	}

	
}

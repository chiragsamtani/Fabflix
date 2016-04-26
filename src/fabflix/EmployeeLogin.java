package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean visit = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(visit);
		if(visit == false){
        	visit = true;
        	response.sendRedirect("loginEmp.html");
        }
        else{
        	response.setContentType("text/html");
        	PrintWriter out = response.getWriter();
        	String email = request.getParameter("email");
        	String password = request.getParameter("password");
		
		
		if(QueryToDatabase.loginEmp(email, password)){
			response.sendRedirect("add.jsp");
		}else{
			response.sendRedirect("loginEmpFail.html");
		}
      }
	}
	

}

package fabflix;

import java.io.IOException; 
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddStar
 */
@WebServlet("/AddStar")
public class AddStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static java.sql.Date convertDate(java.util.Date jDate) {
        java.sql.Date sqlDate = null;
        if (jDate != null) {
            sqlDate = new Date(jDate.getTime());
        }
        return sqlDate;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//retrieve stars info using request.getParameter
		//QueryToDatabase.addStar(id, firstName, lastName, dob, photo_URL);
		response.setContentType("text/html");
		String defaultDOB = "0001/01/01";
		int starId = -1;
		String starFName = null;
		String starLName = null;
		Date dob = null;
		String starURL = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
		
		
		
		if(!(request.getParameter("starred").equals(""))){	
			starId = Integer.parseInt(request.getParameter("starred"));
			}

		
		if(!(request.getParameter("fname").equals("")) || request.getParameter("fname") != null){
			starFName = request.getParameter("fname");
			if(request.getParameter("lname") != null || !(request.getParameter("lname").equals(""))){
				starLName = request.getParameter("lname");
				System.out.println(starFName + " " + starLName);
				System.out.println("1.1");
			}else{
				starLName = starFName;
				starFName = "";
				System.out.println("1.2");
			}
		
			
		}
		if(!request.getParameter("dob").equals("")){
			try{

				dob = convertDate((java.util.Date) df.parse(request.getParameter("dob")));


			}catch(ParseException e){
			}
			
		
		}else{
			try {
				System.out.println("2.1");
				dob = convertDate((java.util.Date) df.parse(defaultDOB));
				
			} catch (ParseException e) {
			}
		}
		if(request.getParameter("purl") == null || request.getParameter("purl").equals("")){

			starURL = "";
		}else{
			starURL = request.getParameter("purl");


		}
		
		if(starFName != null && starLName != null && starId != -1){
			try {
				System.out.println("s:" + starLName + starId);
				int add = QueryToDatabase.insertStar(starId, starFName, starLName, dob, starURL);
				System.out.println(add);
				if(add > 0){
					response.sendRedirect("addSucceed.jsp");
				}else if(add == 0) {
					response.sendRedirect("addFailed.jsp");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		
		if(starFName.equals("") && starLName.equals("") && starId == -1){
			response.sendRedirect("addFailed.jsp");
		}
		//queryToDatabase.insertStar(starId, firstName, lastName, doB, photoURL);
		System.out.println(starId);
		System.out.println(starFName);
		System.out.println(starLName);
		
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

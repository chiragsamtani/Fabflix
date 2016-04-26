package fabflix;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovieAndStar
 */
@WebServlet("/AddMovieAndStar")
public class AddMovieAndStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieAndStar() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static java.sql.Date convertDate(java.util.Date jDate) {
        java.sql.Date sqlDate = null;
        if (jDate != null) {
            sqlDate = new Date(jDate.getTime());
        }
        return sqlDate;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String mtitle=null;
		int year = -1;
		String director = null;
		String banner_URL = null;
		String trailer_URL = null;
		String genre = null;
		
		String defaultDOB = "0001/01/01";
		int starId = -1;
		String starFName = null;
		String starLName = null;
		Date dob = null;
		String starURL = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
		
		
		if(!(request.getParameter("mtitle").equals("")) || request.getParameter("mtitle") != null){
			mtitle = request.getParameter("mtitle");
		}
		if(!(request.getParameter("mdirector").equals("")) || request.getParameter("mdirector") != null){
			director = request.getParameter("mdirector");
		}
		
		if(!request.getParameter("myear").equals("")){
			year = Integer.parseInt(request.getParameter("myear"));
		}
		
		if(!(request.getParameter("genre").equals("")) || request.getParameter("genre") != null){
			genre = request.getParameter("genre");
		}
		
		
		System.out.println("m title " + mtitle);
		System.out.println("m director " + director);
		System.out.println("m year " + year);
		System.out.println("genre " + genre);
		
		
		if(request.getParameter("burl") == null || request.getParameter("burl").equals("")){

			banner_URL = "";
		}else{
			banner_URL = request.getParameter("burl");

		}
		
		if(request.getParameter("turl") == null || request.getParameter("turl").equals("")){

			trailer_URL = "";
		}else{
			trailer_URL = request.getParameter("turl");


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
		
		
		
		
		if(starFName != null && starLName != null){
			try {
				System.out.println("s:" + starLName + starId);
				boolean add = QueryToDatabase.callProcedure(mtitle, year, director, banner_URL, trailer_URL, genre, starFName, starLName, dob, starURL);
				System.out.println("add TRUE VALUE:" + add);
				
				if(add){
					response.sendRedirect("addMovieStarSucceed.jsp");
				}else if(!add) {
					response.sendRedirect("addMovieStarFailed.jsp");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		

		//queryToDatabase.insertStar(starId, firstName, lastName, doB, photoURL);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

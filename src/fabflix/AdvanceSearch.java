package fabflix;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdvanceSearch
 */
@WebServlet("/AdvanceSearch")
public class AdvanceSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdvanceSearch() {
        super();
    }
    public Connection initConnection(){
 	   Connection con;
	       try{Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
         if (envCtx == null) 
         	System.out.println("envCtx is NULL");
         
	       DataSource ds = (DataSource) envCtx.lookup("jdbc/MovieDB");
			if (ds == null)
				System.out.println("ds is null.");
			con = ds.getConnection();
			return con;
	       }catch(Exception e){
	    	   return null;
	       }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        boolean subStr;
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String firstName = request.getParameter("s_first");
        String lastName = request.getParameter("s_last");
        String subString = request.getParameter("substr");
        if(subString != null && !subString.isEmpty())
        	subStr = true;
        else
        	subStr = false;
        ArrayList<Movie> m = getAdvanceSearchMovie(title, year, director, firstName, lastName, subStr);
        Result.feedMovies(m);
        getServletContext().getRequestDispatcher("/Result").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public ArrayList<Movie> getAdvanceSearchMovie(String title, String year, String director, String firstName, String lastName, boolean subString){
		String whereCondition = " WHERE true";
		if(title != null && !title.isEmpty()){
			if(subString){
				whereCondition += " AND movies.title LIKE " + "'" + "%" + title + "%" + "'";
			}else{
				whereCondition += " AND movies.title = " + "'" + title + "'";
			}
		}
		if(year != null && !year.isEmpty()){
			int yr = Integer.parseInt(year);
			whereCondition += " AND movies.year = " + yr ;
		}
		if(director != null && !director.isEmpty()){
			if(subString){
				whereCondition += " AND movies.director LIKE " + "'" + "%" + director + "%" + "'";
			}else{
				whereCondition += " AND movies.director = " + "'" + director + "'";
			}
			
		}
		if(firstName != null && !firstName.isEmpty()){
			if(subString){
				whereCondition += " AND stars.first_name LIKE " + "'" + "%" + firstName + "%" + "'";
			}else{
				whereCondition += " AND stars.first_name = " + "'" + firstName + "'";
			}
			
		}
		if(lastName != null && !lastName.isEmpty()){
			if(subString){
				whereCondition += " AND stars.last_name LIKE " + "'" + "%" + lastName + "%" + "'";
			}else{
				whereCondition += " AND stars.first_name = " + "'" + lastName + "'";
			}	
		}
		ArrayList<Movie> getResult = QueryToDatabase.getMovies(whereCondition);
		return getResult;
		
	}
}

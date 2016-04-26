package fabflix;

import java.io.IOException;

import java.util.ArrayList;


import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	HttpSession session;


	private ArrayList<ArrayList<Star>> fetchAllStars;
    public Search() {
        super();
    }

//    public Connection initConnection(){
//    	   Connection con;
//	       try{Context initCtx = new InitialContext();
//           Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            if (envCtx == null) 
//            	System.out.println("envCtx is NULL");
//            
//	       DataSource ds = (DataSource) envCtx.lookup("jdbc/MovieDB");
//			if (ds == null)
//				System.out.println("ds is null.");
//			con = ds.getConnection();
//			return con;
//	       }catch(Exception e){
//	    	   return null;
//	       }
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  doPost(request, response);
//        for(Movie c : m){
//        	fetchAllStars.add(QueryToDatabase.getStarsOfMovie(c.getTitle()));
//        }
//        for(ArrayList<Star> s: fetchAllStars){
//        	for (Star ss : s){
//        		System.out.println(ss.getFirst_name() + " " + ss.getLast_name());
//        	}
//        }
//        for(Movie c : m){
//        	System.out.println("Movie: " + c.getTitle());
//        	System.out.println(QueryToDatabase.getGenreOfMovie(c.getTitle()));
//        	for(Star s : QueryToDatabase.getStarsOfMovie(c.getTitle())){
//        		System.out.println(s.getFirst_name() + s.getLast_name());
//        	}
//        }
//        System.out.println("Stars:" + QueryToDatabase.getStarsOfMovie("Spider Man").get(0).getFirst_name());
//        if(req.toLowerCase().contains("id")){
//        	int id = Integer.parseInt(request.getParameter("id"));
//        	Star newStar = QueryToDatabase.getStarsInfo(id);
//        	System.out.println(newStar.getId());
//        	for (Movie c : newStar.getMovies()){
//        		System.out.println(c.getTitle());
//        	}
//        }
        
//        request.setAttribute("movie", m);
//        request.setAttribute("stars", s);
//        request.getRequestDispatcher("Result").forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");    // Response mime type
        session = request.getSession(true);
        //what did the user request to browse: genre or comedy
        String req = request.getQueryString().toString();
        ArrayList<Movie> m = new ArrayList<Movie>();
        fetchAllStars = new ArrayList<ArrayList<Star>>();

        
        if(req.toLowerCase().contains("title"))
        {
        	 String letter = request.getParameter("title");
        	 System.out.println(letter);
             String ifAlphabet = " WHERE movies.title LIKE " +   "'" + "%" + letter + "%" + "'";
        	 m = QueryToDatabase.getMovies(ifAlphabet);
        }
        else if(req.toLowerCase().contains("genre"))
        {
        	String genre = request.getParameter("genre");
        	System.out.println(genre);
        	m = QueryToDatabase.getMoviesByGenre(genre);
        }
        else if(req.toLowerCase().contains("letter")){
        	String letter = request.getParameter("letter");
        	System.out.println(letter);
            String ifAlphabet = " WHERE movies.title LIKE " + "'" + letter + "%" + "'";
            m = QueryToDatabase.getMovies(ifAlphabet);
            
        }
        //System.out.println(m.get(0).getTitle());
        //I have fetched the movies based on genre/alphabet
        //TODO: need to send it to Result.java in order for that servlet to process it
        //and send it to result.jsp
        Result.feedMovies(m);
        getServletContext().getRequestDispatcher("/Result").forward(request, response);
//        getServletContext().setAttribute("movList", m);
//        getServletContext().getRequestDispatcher("/Result").forward(request, response);
        //no need to fetch Stars from here because can fetch stars in Result. java
//        RequestDispatcher dis = request.getRequestDispatcher("Result");
//        dis.forward(request, response);
		
	}
	

}

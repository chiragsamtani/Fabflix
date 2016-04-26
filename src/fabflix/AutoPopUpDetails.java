package fabflix;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutoPopUpDetails
 */
@WebServlet("/AutoPopUpDetails")
public class AutoPopUpDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoPopUpDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
	    String id = request.getParameter("param");
	    System.out.println(id);
	    
	    
	    PrintWriter out = response.getWriter();
	    //get data from backend server
	    
	    ArrayList<Movie> movieReference = QueryToDatabase.getMovies(" WHERE movies.id = " + id);
	    int year = movieReference.get(0).getYear();
	    String banner_url = movieReference.get(0).getBannerURL();
	    ArrayList<Star> starsInMovies = QueryToDatabase.getStarsOfMovie(movieReference.get(0).getTitle());
	    
	    //parse to JSON
	    JsonArrayBuilder builder = Json.createArrayBuilder();
	    if(starsInMovies != null){
	    	for(int i = 0; i < starsInMovies.size(); i++){
	    		if(!starsInMovies.get(i).getFirst_name().equalsIgnoreCase(" ") || !starsInMovies.get(i).getLast_name().equalsIgnoreCase(" ")){
	    			builder.add(starsInMovies.get(i).getFirst_name() + " " + starsInMovies.get(i).getLast_name());
	    		}
	    	}
	    }
	    if(banner_url != null){
		    JsonObject result = Json.createObjectBuilder()
		    		.add("year", year)
		    		.add("banner", banner_url)
		    		.add("stars", builder)
		    		.add("success", true)
		    		.build();
		    System.out.println(result.toString());
		    out.println(result.toString());
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

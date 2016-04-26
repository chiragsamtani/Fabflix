package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchAndroid
 */
@WebServlet("/SearchAndroid")
public class SearchAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    
		String key = request.getParameter("searchkey");
	    System.out.println(key);
	    
		String[] split = key.toLowerCase().split("\\W+");
		ArrayList<String> keyWords = new ArrayList<String>();
		for(int i = 0; i < split.length; i++){
			 keyWords.add(split[i]);
		}
		ArrayList<Movie> result = QueryToDatabase.autoCompleteSearch(keyWords);
	    JsonArrayBuilder builder = Json.createArrayBuilder();
	    if(result != null){
	    	for(int i = 0; i < result.size(); i++){
	    			builder.add(result.get(i).getTitle());
	    	}
	    }
	    out.println(builder.build());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

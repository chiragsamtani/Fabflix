package fabflix;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ArrayList<Movie> jspMovie;
    private static HashMap<String, ArrayList<Star>> starsInMovies;
    private static HashMap<String, ArrayList<String>> genresInMovies;
    public int lim = 5;
    private int currPage = 0;
    public String sortBy = "titleasc";
    private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
    }
	ServletConfig servletConfig;
	public void init(ServletConfig config) throws ServletException{
		servletConfig = config;
		super.init();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(servletConfig);
//		if(jspMovie != null){
//			request.setAttribute("movList", jspMovie);
//			request.setAttribute("starMap", starsInMovies);
//			request.setAttribute("genreMap", genresInMovies);
//		}
//		if(request.getParameter("currPage") == null){
//			System.out.println("Printed Here?");
//			request.setAttribute("currPage", 0);
//			System.out.println(request.getAttribute("currPage"));
//		}
//		
		
		if(request.getParameter("limit") != null){
			lim = Integer.parseInt(request.getParameter("limit") );
		}
		if(request.getParameter("currPage") != null){
			currPage = Integer.parseInt(request.getParameter("currPage"));
		}
		if(request.getParameter("sortBy") != null){
			sortBy = request.getParameter("sortBy");
		}
		
		//sort first and start splicing if given limit, but first we sort by titleAsc
		if(sortBy.toLowerCase().contains("asctitle")){
				Collections.sort(jspMovie, new Comparator<Movie>() {
					public int compare(Movie m1, Movie m2) 
					{
						return (m1.getTitle().compareTo(m2.getTitle()));
					}
				});
		}
		else if(sortBy.toLowerCase().contains("desctitle")){
			Collections.sort(jspMovie, new Comparator<Movie>() {
				public int compare(Movie m1, Movie m2) 
				{
					return (m1.getTitle().compareTo(m2.getTitle()));
				}
			});
			Collections.reverse(jspMovie);
		}
		else if(sortBy.toLowerCase().contains("ascyear")){
			Collections.sort(jspMovie, new Comparator<Movie>() {
				public int compare(Movie m1, Movie m2) 
				{
					if(m1.getYear() < m2.getYear())
						return -1;
					else if(m1.getYear() > m2.getYear())
						return 1;
					else
						return 0;
					
				}
			});
		}
		else if(sortBy.toLowerCase().contains("descyear")){
			Collections.sort(jspMovie, new Comparator<Movie>() {
				public int compare(Movie m1, Movie m2) 
				{
					if(m1.getYear() < m2.getYear())
						return 1;
					else if(m1.getYear() > m2.getYear())
						return -1;
					else
						return 0;
					
				}
			});
		}	
		System.out.println(lim);
		System.out.println(currPage);
		int off = lim * currPage;
		ArrayList<Movie> newRes = new ArrayList<Movie>();
		HashMap<String, ArrayList<Star>> newStar = new HashMap<String, ArrayList<Star>>();
		HashMap<String, ArrayList<String>> newGenre = new HashMap<String, ArrayList<String>>();
		for(int i = off; i < off + lim; i++){
			if(jspMovie != null){
				if(i <= jspMovie.size() - 1){
					newRes.add(jspMovie.get(i));
					newStar.put(jspMovie.get(i).getTitle(), QueryToDatabase.getStarsOfMovie(jspMovie.get(i).getTitle()));
					newGenre.put(jspMovie.get(i).getTitle(), QueryToDatabase.getGenreOfMovie(jspMovie.get(i).getTitle()));
				}
			}
		}

		int maxPage = (int) Math.ceil(jspMovie.size() / lim);
		
		request.setAttribute("movList", newRes);
		request.setAttribute("starMap", newStar);
		request.setAttribute("genreMap", newGenre);
		request.setAttribute("currPage", currPage);
		request.setAttribute("limit", lim);
		request.setAttribute("maxPage", maxPage);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
		



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	public static void feedMovies(ArrayList<Movie> m){
		jspMovie = new ArrayList<Movie>();
		starsInMovies = new HashMap<String, ArrayList<Star>>();
		genresInMovies = new HashMap<String, ArrayList<String>>();
		for(Movie c : m){
			starsInMovies.put(c.getTitle(), QueryToDatabase.getStarsOfMovie(c.getTitle()));
		}
		for(Movie c : m){
			genresInMovies.put(c.getTitle(), QueryToDatabase.getGenreOfMovie(c.getTitle()));
		}
		jspMovie = m;
		
		
	}
	public void callFunction(){
		System.out.println("Hello");
	}

}

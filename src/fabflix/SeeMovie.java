package fabflix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeeMovie
 */
@WebServlet("/SeeMovie")
public class SeeMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SeeMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String movieId = null;
		if(request.getParameter("movieId") != null){
			movieId = request.getParameter("movieId");
		}
		int movId = Integer.parseInt(movieId);
		String whereCondition = " where movies.id = " + movId;
		Movie m = QueryToDatabase.getMovies(whereCondition).get(0);
		if(m != null){
			request.setAttribute("movieInfo", m);
			request.setAttribute("movieStars", QueryToDatabase.getStarsOfMovie(m.getTitle()));
			request.setAttribute("movieGenre", QueryToDatabase.getGenreOfMovie(m.getTitle()));
			request.getRequestDispatcher("movieInfo.jsp").forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

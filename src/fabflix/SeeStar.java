package fabflix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeeStar
 */
@WebServlet("/SeeStar")
public class SeeStar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SeeStar() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String starId = null;
		if(request.getParameter("starId") != null)
			starId = request.getParameter("starId");
		int id = Integer.parseInt(starId);
		Star getStar = QueryToDatabase.getStarsInfo(id);	
		if(getStar != null){
			request.setAttribute("starInfo", getStar);
//			request.setAttribute("moviesOfStar", getStar.getMovies());
			request.getRequestDispatcher("starInfo.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

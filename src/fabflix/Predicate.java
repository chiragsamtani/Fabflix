package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Predicate
 */
@WebServlet("/Predicate")
public class Predicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Predicate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<p>We used the % wildcard in our codebase, and we used it in the");
        out.println("QueryToDatabase.java file. We took advantage of the LIKE predicate");
        out.println("when searching for movies that start with a given letter or number.");
        out.println("So, given a letter, A -> find all movies that start with A");
        out.println("");
        out.println("In our getMovies method we specified a String parameter whereCondition.");
        out.println("We use this method in the advanceSearch servlet where we state (if we match");
        out.println("substring):");
        out.println("");
        out.println("	1. Search for movies that start with A, ");
        out.println("		use the LIKE predicate coupled with the wildcard: 'A%'");
        out.println("		So, our where condition looked something like:");
        out.println("			where movies.title LIKE 'A%'");
        out.println("");
        out.println("");		
        out.println("");
        out.println("	2. To search for movies that has a substring such as the word: 'term' ");
        out.println("	on it (which should return, The Terminal or The Terminator)");
        out.println("		use the LIKE predicate coupled wit the wildcard '%term%'");
        out.println("		Our where condition looked something like:");
        out.println("			where movies.title LIKE '%term'");
        out.println("		This returned the search of movies.title that contained the ");
        out.println("		pattern 'term' anywhere in the title of movies in the database.");
        out.println("");
        out.println("	3. For searching for stars first name and stars last name we also did ");
        out.println("	   a similar query to the one above (using %parameter%) and also ");
        out.println("	   (%parameter%) which will search the database for any stars that ");
        out.println("  has the given parameter contained somewhere in their first_name  ");
        out.println("	or last_name depending on which one they filled out.");
        out.println("");
        out.println("	As a note, whenever substring matching is checked (whenever the user asks ");
        out.println("for substring matching, we used the wildcard %parameter% on whichever fields"); 
        out.println("the user filled out. ");
        out.println("	Say the user is in the advanced search page, and the user enters ");
        out.println("	in the title name of the movie (not a complete title name, say: Avi), and ");
        out.println("enters in Leo for the star's first name. Then we will do a sql query on the ");
        out.println("database with the following whereCondition:");
        out.println("		");
        out.println("	WHERE movies.title LIKE '%Avi%' and stars.first_name LIKE '%Leo%'");
        out.println("");
        out.println("Every field that the user fields in will be considered and will be ");
        out.println("conditioned together with an AND. So, to sum up, our use of the LIKE-");
        out.println("predicate mainly consists of us using the % wildcard. One to use in the ");
        out.println("following case:");
        out.println("	'A%': gets all movies starting with name");
        out.println("	'%ABC%': gets all movies with ANY of the parameter having the pattern ABC ");
        out.println("anywhere in their corresponding attributes.");
        out.println("");
        out.println("With these two conditions in our WHERE statement, we were able to fulfill all ");
        out.println("of the browsing and search queries as requested in the assignment");
        out.println("");
        out.println("As a note, we used movies INNER JOIN to join the tables together, and get the ");
        out.println("names of the star in each corresponding movies.");
        out.println("");
        out.println("LIKE PREDICATE AND WILDCARD (JAVA FILES):");
        out.println("	Used in servlet: Search.java - for searching movies by alphabet");
        out.println("	Used in servlet: AdvancedSearch.java - for advancing searching");
        		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

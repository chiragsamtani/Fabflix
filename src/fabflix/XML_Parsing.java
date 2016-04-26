package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XML_Parsing
 */
@WebServlet("/XMLParsing")
public class XML_Parsing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XML_Parsing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<center><h1>XML Parsing Optimization</h1></center>");
        out.println("<p>We used a SAX Parser and used a File to take the input of the XML file. Using InputStream, InputStreamReader and InputSource we set the encoding to ISO-8859-1.</p>");
        out.println("<p>When parsing the XML file, we made some assumptions. We assumed that the only data relevant are the data context (type) that exist in the database MovieDB only.</p>");
        out.println("<p>For example: Awards of Movie are present in the XML file (movies.xml) but we don't add it to our database because awards isn't an attribute in any of the tables. </p>");
        out.println("<p>For date of birth, we only have the year of the stars, so by default we added the stars month and date as 01 and 01 respectively. So values inserted are: 1981-01-01. </p>");
        out.println("<p>To insert to the database, we combined the data from different xml files using a Map<Id, MovieInfo> and merge the data with an ArrayList in the JointHandler class</p>");
        out.println("<p>In the casts243.xml, we set that for every Film, we do a STORED PROCEDURE call using add_movie from Project 3 and using BATCH INSERT for every updates made to the DB.</p>");
        out.println("<p>With BATCH Insert, the process of parsing the XML and adding to the Database took relatively 7 minutes - 10 minutes.</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

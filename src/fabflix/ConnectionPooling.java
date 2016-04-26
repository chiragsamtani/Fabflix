package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionPooling
 */
@WebServlet("/ConnectionPooling")
public class ConnectionPooling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionPooling() {
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

        out.println("<p>We first modified our context.xml file so that the Driver doesn't need to called multiple times to register that driverManager.</p>");
        		out.println("<p>We used the JDBC connection pooling to open a database connection.</p>");
        				out.println("<p>At firstI find myself having to close a connection in my login servlet and then I would have to reopen the connection when I proceed to my main-menu page,</p>");
        						out.println("<p>instead of having a connection opened and access the database while the web-application is live.</p>");
        								out.println("<p>I find my prior method to be a concern because getting contents of disk can be very expensive, so instead we modified the context.xml file to connect to the movieDB database</p>");
        										out.println("<p>and referenced the context using the following code:</p>");
        			
        												out.println("<p>Context initCtx = new InitialContext();</p>");
        														out.println("<p>Context envCtx = (Context) initCtx.lookup(java:comp/env);</p>");
        																out.println("<p>if (envCtx == null) out.println (envCtx is NULL);</p>");
        		               // Look up our data source
        																		out.println("<p>DataSource ds = (DataSource) envCtx.lookup(jdbc/MovieDB);</p>");
        																				out.println("<p>The driver has already been registed to the driverManager in the context.xml file. So I just called this code in a function</p>") ;
        																						out.println("<p>called initConnetion() in one java class where I do all my SQL queries and pass on that query to the connection to get a result. The QueryDatabase class</p>"); 
        																								out.println("<p>is the only class that uses connection pooling.</p>");

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

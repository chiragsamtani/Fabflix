package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadBalancer
 */
@WebServlet("/LoadBalancer")
public class LoadBalancer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadBalancer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<center><h1>Steps in Project 5</h1></center>");
        out.println("<p>First we made a My-SQL replication on a master and slave instance, we correctly configured the slave instance to get all of the databases inserted from the master instance.</p>");
        out.println("<p>We also reinstalled MySQL to Master and Slave. Then, we create a new database based on the movie-dump.sql given on the website for both instance and also configured Tomcat for both master/slave.</p>");
        out.println("<p>We deployed both the .war files given as an example in the instructions (by allowing remote access to port 8080 to master and slave). We also made sure our Sessions were working in master/slave </p>");
        out.println("<p>We also setup apache and enabled Proxy Balancer for TomcatTest. Then we created a StickySession and configured the proxy on the fabflix-running instance to enable SessionBalancer so that it will only send requests to one of the backend instances. </p>");
        out.println("<p>We also modified the configuration so that write requests are sent to master instances, and then we reconfigured the master-slave replication to enable the fabflix moviedb database to run on the master and slave instances/</p>");
        out.println("<p>As far as Android goes, we just made sure to use a VolleyRequest to the servlet LoginAndroid for login which is a similar login to the webapp servlet without recaptcha, and passed the parameters with a Map. We also checked for error cases when input is empty.</p>");
        out.println("<p>We then have a search bar activity which asks the user for input and process it with another VolleyRequest to a similar servlet that does full-text search in WebApp and gets the result in JSONArray, parses it to an ArrayList and display it in a ListView</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

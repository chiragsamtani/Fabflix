package fabflix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signature
 */
@WebServlet("/Signature")
public class Signature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signature() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest    request,  HttpServletResponse    response)
            throws  IOException, ServletException                            {
                     // Output stream to STDOUT
                     PrintWriter    out = response.getWriter();
                     out.println(getSignature("/var/lib/tomcat7/webapps/fabflix/"));
                     out.close();
            }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    public static String getSignature(String dirPath){
        String[] cmd = {
                "/bin/sh",
                "-c",
                "/usr/bin/find "+dirPath+" -not -path \"*/signature/*\" -not -path \"*/META-INF/MANIFEST.MF\" -exec /usr/bin/md5sum {} + | /usr/bin/awk '{print $1}' | /usr/bin/sort | /usr/bin/md5sum"
        };
       String output = "";
       try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                output += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	return output;
    }
}

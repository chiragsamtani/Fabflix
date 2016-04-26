package fabflix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class Parse
 */
@WebServlet("/Parse")
public class Parse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext context = getServletContext();
		String movPath = context.getRealPath("/WEB-INF/xmldata/mains243.xml");
		String actPath = context.getRealPath("/WEB-INF/xmldata/actors63.xml");
		String joinPath = context.getRealPath("/WEB-INF/xmldata/casts124.xml");
		File mov = new File(movPath);
		File act = new File(actPath);
		File cast = new File(joinPath);
		
		InputStream inputStreamMov = new FileInputStream(mov);
		InputStream inputStreamAct = new FileInputStream(act);
		InputStream inputStreamCast = new FileInputStream(cast);
		
		InputStreamReader inputReader1 = new InputStreamReader(inputStreamMov, "ISO-8859-1");
		InputStreamReader inputReader2 = new InputStreamReader(inputStreamAct, "ISO-8859-1");
		InputStreamReader inputReader3 = new InputStreamReader(inputStreamCast, "ISO-8859-1");
		
		InputSource is1 = new InputSource(inputReader1);
		is1.setEncoding("ISO-8859-1");
		InputSource is2 = new InputSource(inputReader2);
		is2.setEncoding("ISO-8859-1");
		InputSource is3 = new InputSource(inputReader3);
		is3.setEncoding("ISO-8859-1");
		
		if (mov != null) {
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
				UserHandler userHandler = new UserHandler();
				saxParser.parse(is1, userHandler);
				TreeMap<String, Movie> m = userHandler.idToMovie;
				TreeMap<String, String> g = userHandler.idToGenre;
				CastsHandler castsHandler = new CastsHandler();
				saxParser.parse(is2, castsHandler);
				TreeMap<String, Star> s = castsHandler.starInfo;
				JointHandler jointHandler = new JointHandler(m, s, g);
				saxParser.parse(is3, jointHandler);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
//		try{
//		File inputFile = new File("xmldata/mains243.xml");
//		File castsFile = new File("xmldata/actors63.xml");
//		File joinFile = new File("xmldata/casts124.xml");
//		SAXParserFactory factory = SAXParserFactory.newInstance();
//		javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
//		UserHandler userHandler = new UserHandler();
//		saxParser.parse(inputFile, userHandler);
//		TreeMap<String, Movie> m = userHandler.idToMovie;
//		TreeMap<String, String> g = userHandler.idToGenre;
//		CastsHandler castsHandler = new CastsHandler();
//		saxParser.parse(castsFile, castsHandler);
//		TreeMap<String, Star> s = castsHandler.starInfo;
//		JointHandler jointHandler = new JointHandler(m, s, g);
//		saxParser.parse(joinFile, jointHandler);
//		}catch(Exception e){
//		e.printStackTrace();
//	}
}
	}



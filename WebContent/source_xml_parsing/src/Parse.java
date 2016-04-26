
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.TreeMap;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class Parse {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
	File mov = new File("mains243.xml");
	File act = new File("actors63.xml");
	File cast = new File("casts124.xml");
	
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
}

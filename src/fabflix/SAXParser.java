//package fabflix;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.TreeMap;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParserFactory;
//	
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//	
//import org.xml.sax.helpers.DefaultHandler;
//	
//public class SAXParser {
//	public static void main(String[] args){
//		try{
////			File inputFile = new File("xmldata/mains243.xml");
////			File castsFile = new File("xmldata/actors63.xml");
////			File joinFile = new File("xmldata/casts124.xml");
////			SAXParserFactory factory = SAXParserFactory.newInstance();
////			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
////			UserHandler userHandler = new UserHandler();
////			saxParser.parse(inputFile, userHandler);
////			TreeMap<String, Movie> m = userHandler.idToMovie;
////			TreeMap<String, String> g = userHandler.idToGenre;
////			CastsHandler castsHandler = new CastsHandler();
////			saxParser.parse(castsFile, castsHandler);
////			TreeMap<String, Star> s = castsHandler.starInfo;
////			JointHandler jointHandler = new JointHandler(m, s, g);
////			saxParser.parse(joinFile, jointHandler);
////		}catch(Exception e){
////			e.printStackTrace();
////		}
////	}
//	
//}

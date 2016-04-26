
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CastsHandler extends DefaultHandler {
	

	TreeMap<String, Star> starInfo = new TreeMap<String, Star>();
	Star singleStar;
	String tempVal;
	String idMap;
	Date date;
	String defaultDOB = "0001/01/01"; 
	int tempInt;
	SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
    public static java.sql.Date convertDate(java.util.Date jDate) {
        java.sql.Date sqlDate = null;
        if (jDate != null) {
            sqlDate = new Date(jDate.getTime());
        }
        return sqlDate;
    }
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase("actor")){
			singleStar = new Star();
			System.out.println("actor");
		}
	}
	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
			tempVal = new String(ch,start,length);
	}
	      
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("stagename")){
			idMap = tempVal;
			//System.out.println(tempVal);
		}
		else if(qName.equalsIgnoreCase("familyname")){
			singleStar.setLast_name(tempVal);
			//System.out.println(tempVal);
		}
		else if(qName.equalsIgnoreCase("firstname")){
			singleStar.setFirst_name(tempVal);
			//System.out.println(tempVal);
		}
		else if(qName.equalsIgnoreCase("dob")){
			if(tempVal.matches("[0-9]+")){
				//System.out.println(tempVal);
				//System.out.println(tempVal);
//				 Calendar cal = Calendar.getInstance();
//				 cal.set(Integer.parseInt(tempVal), 01, 01);
//				 java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
//				 System.out.println(date);
				try {
					date = convertDate((java.util.Date) df.parse(tempVal+"/01/01"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				 singleStar.setDob(date);
			}
			else{
				try {
					date = convertDate((java.util.Date) df.parse(defaultDOB));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				 singleStar.setDob(date);
			}
		}
		else if(qName.equalsIgnoreCase("actor")){
			starInfo.put(idMap, singleStar);
		}
	}
}

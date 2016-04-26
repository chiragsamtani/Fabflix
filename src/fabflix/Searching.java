//package fabflix;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.LinkedList;
//
//public class Searching {
//	private static Connection dbcon;
//	private static Statement statement;
//	private static final String loginUser = "mytestuser";
//	private static final String loginPassword = "mypassword";
//	private static final String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
//
//				
//	public static ArrayList<LinkedList<Movie>> getMoviesByAlphabet(String letter){
//		//String getGenre = "SELECT * FROM MOVIES";
//		String getGenre = "SELECT movies.title,  movies.id, movies.year, movies.director, movies.banner_url, movies.trailer_url, genres.name, stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url"
//				+ "FROM movies INNER JOIN genres_in_movies ON movies.id = genres_in_movies.movie_id "
//				+ "INNER JOIN genres ON genres.id = genres_in_movies.genre_id "
//				+ "INNER JOIN stars_in_movies on movies.id = stars_in_movies.movies_id "
//				+ "INNER JOIN stars on stars.id = stars_in_movies.stars_id "
//				+ "WHERE movies.title LIKE " + "'" + letter + "%" + "'";
//		
//		ArrayList<LinkedList<Movie>> alphabet = new ArrayList<LinkedList<Movie>>(26);
//		for(int i = 0; i<alphabet.size(); i++){
//			alphabet.add(i, new LinkedList<Movie>());
//
//		}
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//	        dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPassword);
//	        statement = dbcon.createStatement();
//	        ResultSet x = statement.executeQuery(getGenre);
//	        while(x.next()){
//	    		Movie m = new Movie(x.getString(1), x.getInt(2), x.getInt(3), x.getString(4), x.getString(5), x.getString(6));
//	    		String startsWith = m.getTitle().substring(0,1);
//	    		String a = "A";
//	    		int belong = Math.abs(a.compareTo(startsWith));
//	    		alphabet.get(belong).add(m);
//	           
//	        }
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return alphabet;
//		
//	}
//	public static void main(String[] args){
//		ArrayList<LinkedList<Movie>> s = getMoviesByAlphabet("A");
//		for(int i = 0; i < s.size(); i++){
//			for(Movie c : s.get(i)){
//				System.out.println(c.getTitle());
//			}
//		}
//		
//	}
//
//	
//}
//

package fabflix;

import java.sql.CallableStatement; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.ResultSetMetaData;

public class QueryToDatabase {
	private static Statement statement;
	private static Connection dbcon = initConnection();
	private static HttpSession session;
	public QueryToDatabase(){
		super();
		dbcon = initConnection();
	}
    public static Connection initConnection(){
 	   Connection con;
	       try{Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
         if (envCtx == null) 
         	System.out.println("envCtx is NULL");
         
	       DataSource ds = (DataSource) envCtx.lookup("jdbc/MovieDB");
			if (ds == null)
				System.out.println("ds is null.");
			con = ds.getConnection();
			return con;
	       }catch(Exception e){
	    	   return null;
	       }
 }
	public static ArrayList<Movie> autoCompleteSearch(ArrayList<String> keyWordList){
		//String alterQuery = "ALTER TABLE movies ADD FULLTEXT (title);";
		String query = "select movies.title, movies.id, movies.year, movies.director, movies.banner_url, movies.trailer_url from movies WHERE";
		//SELECT distinct (title) from movies WHERE MATCH(movies.title) AGAINST ('home') AND MATCH(movies.title) AGAINST ('a*' IN BOOLEAN MODE)
		//Good U
		//Last word should be the prefix
		//First word should be used as the 
		System.out.println(keyWordList);
		for(String keyWord : keyWordList){
			if(keyWordList.indexOf(keyWord) == keyWordList.size()-1){
				query += " MATCH(movies.title) AGAINST ('" + keyWord + "*' IN BOOLEAN MODE)";
						   
			}
			else{
				query += " MATCH(movies.title) AGAINST ('" + keyWord + "') AND";
			}
		}
		query += " order by movies.title ASC";
		query += " LIMIT 10;";
		ArrayList<Movie> searchList = new ArrayList<Movie>();
		//ArrayList<String> genr1eList = new ArrayList<String>();
		HashSet<String> movSet = new HashSet<String>();
		try {

				statement = dbcon.createStatement();
				ResultSet x = statement.executeQuery(query);
	        while(x.next()){
	    		Movie m = new Movie(x.getString(1), x.getInt(2), x.getInt(3), x.getString(4), x.getString(5), x.getString(6));
	            //eliminate duplicates
	    		if(movSet.add(x.getString(1)))
	    			searchList.add(m);
	        }

		}catch (Exception e) {
			e.printStackTrace();
		}
		return searchList;
		
	}
    
	public static ResultSet SelectData(String sql){
	//Select data from database, and return the result set 
		ResultSet result = null;
		try {
			statement = dbcon.createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
        	System.out.println("0 rows affected");
            System.out.println("Select Error, Make Sure your SQL Query is Correct");
        } catch (Exception e) {
        }
		return result;
	}
	
	public static LinkedList<String> showMetaData() throws SQLException{
        
		LinkedList<String> result = new LinkedList<String>();
		/**
		 * Providing metadata of database and for each table print out its attributes and the type of each attribute
		 */
		//getting the tables
		ResultSet getTables = SelectData("SHOW TABLES");
		LinkedList<String> tableNames = new LinkedList<String>();
		int index = 0;
		while(getTables.next()){
			//System.out.println("Table Name: " + getTables.getString(1));
			tableNames.add(getTables.getString(1));
			
		}
		ResultSet rs1;
		ResultSetMetaData metaData;
		while(!tableNames.isEmpty()){
		 int i = 1;		 
		 rs1 = SelectData("Select * from " + tableNames.peek() + ";");
		 metaData = rs1.getMetaData();
		 result.add("Table Name: " + tableNames.peek() + "\n");
			for(; i <= metaData.getColumnCount(); i++){
				result.add(String.format("Attribute Name: %-15s Attribute Type: %-15s\n",metaData.getColumnName(i),metaData.getColumnTypeName(i)));			
			}
			tableNames.pop();
		}
		
		return result;
	}
	
	public static int insertStar(int starId, String firstName, String lastName, Date dob, String URL) throws SQLException{
		int x = 0;
		statement = dbcon.createStatement();
		System.out.println("I'm here");
		String sql = "INSERT INTO stars VALUES(" + starId + ", \"" + firstName +"\", \""+ lastName + "\", \"" + dob + "\", \"" + URL + "\");"; 
		System.out.println(sql);
		try{
		 x = statement.executeUpdate(sql);
		} catch (Exception e) {
			x = 0;
			System.out.println("This is x "+ x);
		}
		System.out.println("This is x "+ x);
		return x;
		
	}
    
	
    public static ArrayList<Movie> getMovies(String whereCondition){
    	
		//String getGenre = "SELECT * FROM MOVIES";
		String query = "select movies.title,  movies.id, movies.year, movies.director, movies.banner_url, movies.trailer_url";
		query += " from movies INNER JOIN genres_in_movies ON movies.id = genres_in_movies.movie_id";
		query += " INNER JOIN genres ON genres.id = genres_in_movies.genre_id";
		query += " INNER JOIN stars_in_movies on movies.id = stars_in_movies.movies_id";
		query += " INNER JOIN stars on stars.id = stars_in_movies.stars_id";
		query += whereCondition;
		query += " order by movies.title ASC;";
		ArrayList<Movie> alphabet = new ArrayList<Movie>();
		//ArrayList<String> genreList = new ArrayList<String>();
		HashSet<String> movSet = new HashSet<String>();
		
		try {

				statement = dbcon.createStatement();
				ResultSet x = statement.executeQuery(query);
	        while(x.next()){
	    		Movie m = new Movie(x.getString(1), x.getInt(2), x.getInt(3), x.getString(4), x.getString(5), x.getString(6));
	            //eliminate duplicates
	    		if(movSet.add(x.getString(1)))
	    			alphabet.add(m);
	        }

		}catch (Exception e) {
			e.printStackTrace();
		}
		return alphabet;
		
	}
	public static ArrayList<Movie> getMoviesByGenre(String genre){
		//String getGenre = "SELECT * FROM MOVIES";
		String movGenre = "select movies.title,  movies.id, movies.year, movies.director, movies.banner_url, movies.trailer_url";
		movGenre += " from movies INNER JOIN genres_in_movies ON movies.id = genres_in_movies.movie_id";
		movGenre += " INNER JOIN genres ON genres.id = genres_in_movies.genre_id";
		movGenre += " WHERE genres.name = " + "'" + genre + "'";
		movGenre += " order by movies.title ASC;";
		ArrayList<Movie> movGen = new ArrayList<Movie>();
		//ArrayList<String> genreList = new ArrayList<String>();
		
		try {

				statement = dbcon.createStatement();
				ResultSet x = statement.executeQuery(movGenre);
	        while(x.next()){
	    		Movie m = new Movie(x.getString(1), x.getInt(2), x.getInt(3), x.getString(4), x.getString(5), x.getString(6));
	    		movGen.add(m);
	        }

		}catch (Exception e) {
			e.printStackTrace();
		}
		return movGen;
		
	}
	public static ArrayList<String> getGenreOfMovie(String movieName){
		ArrayList<String> genres = new ArrayList<String>();
		String getGenre = "select genres.name";
		getGenre += " from movies INNER JOIN genres_in_movies ON movies.id = genres_in_movies.movie_id";
				getGenre += " INNER JOIN genres ON genres.id = genres_in_movies.genre_id";
				getGenre += " WHERE movies.title = " + "'" + movieName + "'";
				getGenre += " order by genres.name;";
		try{
			
			statement = dbcon.createStatement();
			ResultSet s = statement.executeQuery(getGenre);
			while(s.next()){
				genres.add(s.getString(1));
			}
			return genres;
			
			
		}catch (Exception e){
			return null;
		}
		
		
	
	}
	public static ArrayList<Star> getStarsOfMovie(String movieName){
		String getGenre = "select stars.id, stars.first_name, stars.last_name, stars.dob , stars.photo_url";
		getGenre += " from movies INNER JOIN stars_in_movies on movies.id = stars_in_movies.movies_id";
				getGenre += " INNER JOIN stars on stars.id = stars_in_movies.stars_id";
				getGenre += " WHERE movies.title = " + "'" + movieName + "'";
				getGenre += " order by stars.first_name;";
		ArrayList<Star> starList = new ArrayList<Star>();
		//ArrayList<String> genreList = new ArrayList<String>();
		HashSet<String> starSet = new HashSet<String>();
		try{
			
			statement = dbcon.createStatement();
			ResultSet s = statement.executeQuery(getGenre);
	        while(s.next()){
	    		Star star = new Star(s.getInt(1), s.getString(2), s.getString(3), s.getDate(4), s.getString(5));
	            //eliminate duplicates
	    		if(starSet.add(s.getString(2)))
	    			starList.add(star);
	        }
	        return starList;
		}catch (Exception e){
			return null;
		}
		
	}
	public static Star getStarsInfo(int id){
		String getGenre = "select movies.id, movies.title, movies.year, stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url";
		getGenre += " from movies INNER JOIN stars_in_movies on movies.id = stars_in_movies.movies_id";
				getGenre += " INNER JOIN stars on stars.id = stars_in_movies.stars_id";
				getGenre += " WHERE stars.id = " + id;
//				getGenre += " WHERE stars.first_name = " + "'" + firstName + "'" + "AND " + "stars.last_name = " + "'" + lastName + "'";
				getGenre += " order by movies.title;";
		 
		 try{
			
			statement = dbcon.createStatement();
			ResultSet s = statement.executeQuery(getGenre);
			Star star = null;
			if(s.first()){
//			System.out.println(s.getString(5));
//			System.out.println(s.getInt(4));
				star = new Star(s.getInt(4), s.getString(5), s.getString(6), s.getDate(7), s.getString(8));
				star.addMovie(new Movie(s.getInt(1), s.getString(2), s.getInt(3), "", "", ""));
			}
//			System.out.println(s.getString(5));
//			System.out.println(star.getFirst_name());
			while (s.next())
			{
				star.addMovie(new Movie(s.getInt(1), s.getString(2), s.getInt(3), "", "", ""));
			}
			
			return star;
		 } 
		catch (Exception e){
			return null;
		}
		
	
	}
	public static boolean login(String email, String password){
		try{
			statement = dbcon.createStatement();
	        String query = "SELECT * FROM customers WHERE email = '" + email + "'" + " and password = '" + password + "'";
	        ResultSet rs = statement.executeQuery(query);       
	        if(rs.first()){
	        	System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getString(4));
	    		Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));	
	    		System.out.println(customer.getFirstName() + customer.getEmail());
	    		return true;
	        }
	        else{
	        	return false;
	        }
		} catch (SQLException ex) {
            while (ex != null) {
                System.out.println ("SQL Exception:  " + ex.getMessage ());
                ex = ex.getNextException ();
           
            }
            return false;
        }
		// end catch SQLException
		catch(Exception e){
			return false;
		}
	}
	public static boolean loginEmp(String email, String password){
		try{
			statement = dbcon.createStatement();
	        String query = "SELECT * FROM employee WHERE email = '" + email + "'" + " and password = '" + password + "'";
	        ResultSet rs = statement.executeQuery(query);       
	        if(rs.first()){
	    		return true;
	        }
	        else{
	        	return false;
	        }
		} catch (SQLException ex) {
            while (ex != null) {
                System.out.println ("SQL Exception:  " + ex.getMessage ());
                ex = ex.getNextException ();
           
            }
            return false;
        }
		// end catch SQLException
		catch(Exception e){
			return false;
		}
	}
	public static boolean callProcedure(String title, int year, String director, String banner_url, String trailer_url,
			String genre_name, String first_name, String last_name, Date dob, String photo_url) throws SQLException{
		try{
			CallableStatement cs = null;
			cs = dbcon.prepareCall("CALL add_movie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			cs.setString(1, title);
			cs.setInt(2, year);
			cs.setString(3, director);
			cs.setString(6, genre_name);
			cs.setString(7, first_name);
			cs.setString(8, last_name);
			cs.setDate(9, dob);

			if(banner_url == null)
				cs.setNull(4, java.sql.Types.VARCHAR);
			else
				cs.setString(4, banner_url);
			if(trailer_url == null)
				cs.setNull(5, java.sql.Types.VARCHAR);
			else
				cs.setString(5, trailer_url);
			if(photo_url == null)
				cs.setNull(10, java.sql.Types.VARCHAR);
			else
				cs.setString(10, photo_url);
			cs.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static void batchInsert(Movie movie, String genre, ArrayList<Star> stars) throws SQLException{
		CallableStatement cs = null;
		try{
			int[] rows = null;
			dbcon.setAutoCommit(false);
			cs = dbcon.prepareCall("CALL add_movie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			for(int i = 0; i < stars.size(); i++){
				if(stars.get(i) != null){
					cs.setString(1, movie.getTitle());
					cs.setInt(2, movie.getYear());
					cs.setString(3, movie.getDirector());
					cs.setString(6, genre);
					cs.setString(7, stars.get(i).getFirst_name());
					cs.setString(8, stars.get(i).getLast_name());
					cs.setDate(9, stars.get(i).getDob());
		
					if(movie.getBannerURL() == null)
						cs.setNull(4, java.sql.Types.VARCHAR);
					else
						cs.setString(4, movie.getBannerURL());
					if(movie.getTrailerURL() == null)
						cs.setNull(5, java.sql.Types.VARCHAR);
					else
						cs.setString(5, movie.getTrailerURL());
					if(stars.get(i).getPhoto_url() == null)
						cs.setNull(10, java.sql.Types.VARCHAR);
					else
						cs.setString(10, stars.get(i).getPhoto_url());
					cs.addBatch();
				}
				
			}
			rows = cs.executeBatch();
			dbcon.commit();
			System.out.println(rows[0]);
			System.out.println("BATCH SUCCESFUL");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(cs!=null){
				cs.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

}

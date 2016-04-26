import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;


public class QueryToDatabase {
	private static Connection dbcon;
	private static final String loginUser = "classta";
	private static final String loginPassword = "classta";
	private static final String loginUrl = "jdbc:mysql://localhost:3306/moviedb_project4_grading";

	public static void batchInsert(Movie movie, String genre, ArrayList<Star> stars) throws SQLException{
		CallableStatement cs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPassword);
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
			dbcon.close();
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

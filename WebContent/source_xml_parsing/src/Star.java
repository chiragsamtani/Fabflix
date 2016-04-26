
import java.sql.Date;  
import java.util.ArrayList;


public class Star {
	private int id;
	private String first_name;
	private String last_name;
	private Date dob;
	private String photo_url;
	ArrayList<Movie> listOfMovies;
	@SuppressWarnings("deprecation")
	public Star(){
		super();
		this.id = 0;
		this.first_name = "";
		this.last_name = "";
		this.dob = new Date(0000, 00, 00);
		this.photo_url = "";
		this.listOfMovies = new ArrayList<Movie>();
	}
	public Star(int id, String first_name, String last_name, Date dob, String photo_url) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.photo_url = photo_url;
		this.listOfMovies = new ArrayList<Movie>();
	}
	public int getId() {
		return id;
	}
	
	public void addMovie(Movie m){
		listOfMovies.add(m);
	}
	public ArrayList<Movie> getMovies(){
		return listOfMovies;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String firstName) {
		this.first_name = firstName;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		this.last_name = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoto_url() {
		return photo_url;
	}
}
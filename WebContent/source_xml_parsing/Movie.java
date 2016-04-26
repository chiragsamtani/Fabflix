package source_xml_parsing;

public class Movie {
	private int id;
	private String title;
	private int year;
	private String director;
	private String banner_url;
	private String trailer_url;
	public Movie(){
		this.id = 0;
		this.title = "";
		this.year = 0;
		this.director = "";
		this.banner_url = "";
		this.trailer_url = "";
	}
	public Movie(int id, String title,
			int year, String director,
			String banner_url, String trailer_url){
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
	}
	public Movie(String title, int id,
			int year, String director,
			String banner_url, String trailer_url){
		this.title = title;
		this.id = id;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
	}
	
	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public int getYear(){
		return year;
	}
	public String getDirector(){
		return director;
	}
	public String getBannerURL(){
		return banner_url;
	}
	public String getTrailerURL(){
		return trailer_url;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setYear(int year){
		this.year = year;
	}
	public void setDirector(String director){
		this.director = director;
	}
	public void setBannerURL(String banner_url){
		this.banner_url = banner_url;
	}
	public void setId(String trailer_url){
		this.trailer_url = trailer_url;
	}
	
}

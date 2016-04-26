package fabflix;

public class Bucket {
	private int quantity;
	private Movie movie;
	
	public Movie getMovie(){
		return movie;
	}
	public void setMovie(Movie m){
		this.movie = m;
	}
	public int getQuantiy(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public void addQuantity(int quantity){
		this.quantity += quantity;
	}
	public void incrementQuantity(){
		quantity++;
	}
	public void decrementQuantity(){
		quantity--;
	}
}

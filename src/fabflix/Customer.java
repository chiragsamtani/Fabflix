package fabflix;

public class Customer {
	private int id;
	private String first_name;
	private String last_name;
	private String cc_id;
	private String address;
	private String email;
	private String password;
	
	public Customer(int id, String first_name,
			String last_name, String cc_id,
			String address, String email, String password){
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.cc_id = cc_id;
		this.address = address;
		this.email = email;
		this.password = password;	
	}
	//getter methods
	public int getId(){
		return id;
	}

	public String getFirstName(){
		return first_name;
	}

	public String getLastName(){
		return last_name;
	}
	public String getCC_ID(){
		return cc_id;
	}
	
	public String getAddress(){
		return address;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword(){
		return password;
	}
	/**Setters if neccessary**/
	//Overriding data from data might not be necessary
	public void setId(int id){
		this.id = id;
	}	
	public void setFirstName(String firstName){
		this.first_name = firstName;
	}
	public void setLastName(String lastName){
		this.last_name = lastName;
	}
	public void setCCId(String cc_id){
		this.cc_id = cc_id;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPassword(String password){
		this.password = password;
	}

	
}

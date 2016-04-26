package fabflix;

import java.util.HashMap;
import java.util.Map.*;
import java.util.Set;
public class Cart {
	private HashMap<String, Integer> cart;
	public Cart()
	{
		cart = new HashMap<String, Integer>();
	}
	public void addtoCart(String title){
		System.out.println(title);
		System.out.println("Hello:" + cart.containsKey(title));
		if(cart.containsKey(title)){
			cart.put(title, cart.get(title) + 1);
		}else{
			cart.put(title, 1);
		}
		
//		System.out.println(quantity);
//		if(cart.containsKey(title )== null){
//			
//			cart.put(title, 1);
//		}else{
//			cart.put(title, quantity + 1);
//		}
	}
	public void updateCart(String title, int quantity){
		//overwrite the old value
		//does it exist
		Entry my;
		
		if(cart.get(title) != null)
			cart.put(title, quantity);
		//if it doesn't it's not in the cart
		//and do nothing
	}
	public void decrementFromCart(String title){
		Integer quantity = cart.get(title);
		if(quantity == null){
			cart.put(title, 1);
		}else{
			cart.put(title, quantity - 1);
		}
	}
	public void removeItemFromCart(String title){
		if(cart.containsKey(title)){
			cart.remove(title);
		}
	}
	public void emptyCart(){
		if(!cart.isEmpty()){
			cart.clear();
		}
	}
	public boolean containsItem(String title){
		return cart.containsKey(title);
	}
	public boolean keyEqualZero(String title){
		return cart.get(title)==0;
	}
	
	public HashMap<String, Integer> getCart(){
		return this.cart;
		
	}
	}
	

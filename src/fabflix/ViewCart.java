package fabflix;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewCart
 */
@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int quantity = 1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
		}
		String req = request.getQueryString().toString();
		String movTitle = "";

		if(request.getParameter("quantity") != null){
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		if(request.getParameter("movieTitle") != null){
			movTitle = request.getParameter("movieTitle");
			System.out.println(movTitle);
			cart.addtoCart(movTitle);
		}
		if(req.toLowerCase().contains("remove")){
			String toDelete = request.getParameter("remove");
			cart.removeItemFromCart(toDelete);
		}
//		else if(req.toLowerCase().contains("update")){
////			Entry<String, Integer> toChange = (Entry<String, Integer>) request.getParameter("update");
////			cart.updateCart(movTitle, toChange);
//			String toChange = request.getParameter("update");
//			String[] tC = toChange.split(",");
//			System.out.println(tC[0]);
//			System.out.println(tC[1]);
//			cart.updateCart(tC[0], Integer.parseInt(tC[1]));
		else if(req.toLowerCase().contains("empty")){
			cart.emptyCart();
		}else if(req.toLowerCase().contains("increment")){
			movTitle = request.getParameter("increment");
			cart.addtoCart(movTitle);
		}else if(req.toLowerCase().contains("decrement")){
			movTitle = request.getParameter("decrement");
			cart.decrementFromCart(movTitle);
			if((cart.keyEqualZero(movTitle))){
				cart.removeItemFromCart(movTitle);
			}
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("addToCart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

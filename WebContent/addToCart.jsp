<%@page language='java'%>
<%@page import='java.*'%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Set" %>
<%@page import="javax.servlet.*" %>
<%@page import="fabflix.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="main.css">
	<title>FabFlix Home</title>
	<script type="text/javascript" src="jquery_1.3.js" language="javascript"></script>
    <script src="jquery.autocomplete.js"></script> 
</head>
<body>


	<%
	Cart cart = (Cart) session.getAttribute("cart"); 
	HashMap<String, Integer> itemsInCart = cart.getCart();
	double totalPrice = 0.0;
	double price = 9.99;
	int defValue=1;
	if(request.getAttribute("quantity") != null){
		defValue = (Integer) request.getAttribute("quantity");
	}
	%>

	<div class="title">
		<h1>FabFlix</h1>
	</div>
	 <section class="searchbar">
		<form action="Search" method="get">   
			<input type="search" id="sTitle" placeholder="Search..." name="title" />
			<script>
				$("#sTitle").autocomplete("getData.jsp");
			</script>			
			<button>Search</button>
		</form>
	</section>
	<div class="tinyspacer">&nbsp;</div></td>
	<div id="nav">
	<nav>
	<ul>
		<li><a href="Main.html">Home</a></li>
		<li><a href="advanceSearch.html">Advanced Search</a></li>
 		   <ul style="float:right;list-style-type:none;">
    			<li><a class="active" href="addToCart.jsp">My Cart</a></li>
  		   </ul>
	</ul>
	</nav>
	</div>
	<div class="tinyspacer">&nbsp;</div>
    <tr>
      <td><div align="center">
          <table width="904" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td background="images/middle.png" width="978"><div class="main">
                <div class="content">
                  <h2>Shopping Cart:</h2>
                  <div align="center">
                    <table width="700" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="400"><div class="tinyspacer">&nbsp;</div></td>
                        <td width="75"><div class="tinyspacer">&nbsp;</div></td>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                      </tr>
                      <tr>
                        <td width="400" height="25"><b>Movie Title</b></td>
                        <td><b>Price</b></td>
                        <td><b>Qty</b></td>
                      </tr>
		     <%Set<Entry<String, Integer>> set = itemsInCart.entrySet();%>
                      <%for(Entry<String, Integer> item: set){%>
                      <tr>
                      	<td width="400" height="25"><b><%=item.getKey()%></b></td>
                      	<td><b>9.99</b></td>
                      	<td><b><input type="text" name="quantity" value="<%=item.getValue()%>" size= 8> </b></td>
						<%totalPrice += (double) (price*item.getValue()); %>
						<%String toBeSeparated= item.getKey() +","+ (item.getValue()+1); %>
						<td>
						<a href ="ViewCart?increment=<%=item.getKey()%>">
						<button>+1</button>
						</a></td>
						<td>
						<a href ="ViewCart?decrement=<%=item.getKey()%>">
						<button>-1</button>
						</a></td>
						<td>	
						<a href = "ViewCart?remove=<%=item.getKey()%>">
						<button>Delete</button>
						</a> </td>
                      </tr>
                      <%} %>
                      <tr>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td colspan="2"><div align="right">Grand Total: $ <%=totalPrice %></div></td>
                      </tr>
                      <tr>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                        <td><div class="tinyspacer">&nbsp;</div></td>
                      </tr>
                    </table>
                    <div class="tinyspacer">&nbsp;</div>
                    <div align="center"><a href="">Check Out</a> | <a href="ViewCart?empty=true">Empty Cart</a></div>
                    <div class="tinyspacer">&nbsp;</div>
                    </div>
                  &nbsp;<br />

                  <br />
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="footer" align="center"><a href="Main.html">Home</a> | <a href="advanceSearch.html">Advanced Search</a> | <a href="addToCart.jsp">My Cart</a>
	</div>
</body>
</html>

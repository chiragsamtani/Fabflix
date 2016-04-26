<%@page language='java'%>
<%@page import='java.*'%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
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
	<script>
	function whenerror(obj){
		var noimg="NIA.jpg";
		obj.src=noimg;
	}
	</script>

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
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	
	<div class="subtitle" style="padding-left: 20%">
		<h3>Movie's Information: </h3>
	</div>
	
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<%
		Movie movieInfo = (Movie) request.getAttribute("movieInfo"); 
		ArrayList<Star> movieStars = (ArrayList<Star>) request.getAttribute("movieStars");
		ArrayList<String> movieGenre = (ArrayList<String>) request.getAttribute("movieGenre");	
	%>
	
	<div class="movielist" align="center;">
	<div class="midtable" style="padding-left: 25%;">
	<div id="buttons">
	<table width="700" border="0" cellspacing="0" cellpadding="0">
	<tbody>
	<tr>

    <td width="350" rowspan="9"><div class="overlap"><img src="<%=movieInfo.getBannerURL()%>" onerror="this.onerror=null;whenerror(this);" width= 70%;></div>
    <br>
    <button class="buttongreen2" >
			<div class="button-title">Add to cart</div>
			<div class="price">$9.99</div>
	</button> 
	<td style="text-align:left; color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Title:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;"><%=movieInfo.getTitle() %></td>
    </tr>

    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Year:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;"><%=movieInfo.getYear() %></td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Director:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;"><%=movieInfo.getDirector() %></td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Movie id:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;"><%=movieInfo.getId() %></td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Stars:</td>
    <td style="text-align:left; color: white; text-decoration: underline;">
    <%for(Star s : movieStars){ int starId = s.getId(); String name = s.getFirst_name() + " " + s.getLast_name(); %>
    <a href="SeeStar?starId=<%=starId%>"><%=name%> </a> <%} %>
    </td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Genre:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">
    <%for(String g: movieGenre){ %><%=g%><%} %></td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Trailer:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;"><a href="<%=movieInfo.getTrailerURL()%>" style="text-decoration: underline;">Click here</a> to watch the movie trailer</td>
    </tr>
    <tr>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">Price:</td>
    <td style="text-align:left;color:white; font-family: 'Arial Rounded MT Bold', 'Shift', sans-serif;">$9.99</td>
    </tr>
    </tbody>
	</table>
	</div>
	</div>
	</div>
	</div>
	
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>

	<div class="footer" align="center"><a href="Main.html">Home</a> | <a href="advanceSearch.html">Advanced Search</a> | <a href="addToCart.jsp">My Cart</a>
	</div>
</body>
</html>
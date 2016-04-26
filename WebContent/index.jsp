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
	<script type="text/javascript" src="jquery_1.3.js" language="javascript"></script>
    <script src="jquery.autocomplete.js"></script> 
	<title>FabFlix Home</title>
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

	<center>
	   <div class="subtitle">
		<h3>Sort By: </h3>
		<a href="Result?sortBy=ascTitle" style="color: rgb(0,255,0);font-family: 'Arial Rounded MT Bold'">Title Ascending</a>
			<a href="Result?sortBy=descTitle" style="color: rgb(255,0,0); font-family: 'Arial Rounded MT Bold'" >Title Descending</a>
			<a href="Result?sortBy=ascYear" style="color: rgb(0,255,0); font-family: 'Arial Rounded MT Bold'">Ascending Year</a>
			<a href="Result?sortBy=descYear" style="color: rgb(255,0,0); font-family: 'Arial Rounded MT Bold'" >Descending Year</a>
		</div>
	</center>
	<div class="subtitle" style="padding-left: 20%">
		<h3>Available Movies: </h3>
	</div>
	<%
	ArrayList<Movie> movList = (ArrayList<Movie>) request.getAttribute("movList");
	HashMap<String, ArrayList<Star>> starList = (HashMap<String, ArrayList<Star>>) request.getAttribute("starMap");
	HashMap<String, ArrayList<String>> genreList = (HashMap<String, ArrayList<String>>) request.getAttribute("genreMap");
	int limit = 5;
	if(request.getAttribute("limit") != null){
		limit = (Integer) request.getAttribute("limit");
	}
	int currPage = 0;
	if(request.getAttribute("currPage") != null){
		currPage = (Integer) request.getAttribute("currPage");
	}
	int maxPage = 5;
	if(request.getAttribute("maxPage") != null){
		maxPage = (Integer) request.getAttribute("maxPage");
	}
	%>
	<div class="movielist" align="center;" >
	<div id="buttons">
	<%for(int test = 0; test<movList.size();test++){%>
	<%String img= movList.get(test).getBannerURL();%>
	<%String title= movList.get(test).getTitle() + "(" + movList.get(test).getYear() + ")";%>
	<%String director= movList.get(test).getDirector();%>
	<%int movieId = movList.get(test).getId(); %>
	<img id="triggerPop" src="<%= img%>" onmouseover="mOver(<%=movieId %>)" onmouseout="mOut(<%=movieId %>)" onerror="this.onerror=null;whenerror(this);" style="width: 10%; "><br></br>
		
		
		<div class="info" id="<%=movieId %>" name="<%=movieId %>" align="right" style="display:none">
			<table width="400" border="0" cellspacing="0" cellpadding="10">
			<tr>
				<td style="text-align:left;" height="25">Stars:</td>

            	<td class="stars" id="Stars" style="display: inline-block;"width="100%"></td>
				<td width="150" height="25"></td>
				<td>&nbsp;</td>
				<td width="200" rowspan="5" valign="top"><img id="pop_banner" src= "syrtyesr" onerror="this.onerror=null;whenerror(this);" width=80%;>&nbsp;</td>
        	</tr>
        	<tr>
        		<td style="text-align: left;" height="25">Year:</td>
        		<td class="mov_year" id="Year" style="text-align: left;">2003</td>
        	</tr>
    		</table>
		</div>

		<p><b><%=title%></b></p>
		<p><i>Director: <%=director%></i></p>
		<p><a href="SeeMovie?movieId=<%=movieId%>" style="color: white; text-decoration: underline;">&gt; more info</a></p>

	
		<script>
		function mOver(id){
			var ajaxRequest;  // The variable that makes Ajax possible!
			// Create a function that will receive data sent from the server
			if (window.XMLHttpRequest) { // Mozilla, Safari, ...
				ajaxRequest = new XMLHttpRequest();
	            if (ajaxRequest.overrideMimeType) {
	            	ajaxRequest.overrideMimeType('text/html');
	                // See note below about this line
	            }
	        }
	        else if (window.ActiveXObject) { // IE
	            try {
	            	ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
	            } 
	            catch (e) {
	                try {
	                	ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
	                } 
	                catch (e) {}
	            }
	        }
			ajaxRequest.open("GET", "AutoPopUpDetails?param="+id, true);
			ajaxRequest.onreadystatechange = function(){
				if(ajaxRequest.readyState == 4){
					//document.getElementById(id).style.display = 'inline';
					var result = eval('('+ajaxRequest.responseText+')'); 
					var allDivTd = document.getElementById(id).getElementsByTagName("td");
					var year = allDivTd[6];
					year.innerHTML  = result.year;
					var stars = allDivTd[1];
					stars.innerHTML  = result.stars
					var allImage = document.getElementById(id).getElementsByTagName("img");
					var image = allImage[0];
					image.src = result.banner;
					document.getElementById(id).style.display = 'inline';
				}
				else if(ajaxRequest.readyState == 0){
					return;
				}
			}
			ajaxRequest.send(null);
			
			
		}
		function mOut(id){
			document.getElementById(id).style.display = 'none';
		}
	
	</script>
	
	<a href= "ViewCart?movieTitle=<%=title%>">
	<button class="buttongreen2" >
	<div class="button-title">Add to cart</div>
	<div class="price">$9.99</div>
	</button> 
	</a>
	<br></br>
	<br></br>
	<HR COLOR="white" width= 30%>
	<br></br>
	<%}%>
		
	</div>
	</div>
	<div class="pages">
	<div class="pagebutton">


	<%if(currPage != 0){ %>
		<a href="Result?currPage=<%=currPage - 1%>">
			<button class="buttongreen2">Prev Page</button>
		</a>
	<%} %>

	<%if(currPage < maxPage){ %>
		<a href="Result?currPage=<%=currPage + 1%>">
			<button class="buttongreen2">Next Page</button>
		</a>
	<%} %>
	
	<br></br>

	</div>
	
	
	<div class="resultlimit">
	<b style="color: white"> No. of Page Results: </b>
	<a href="Result?limit=5" style="text-decoration: underline;"> 5</a> <b>|</b>
	<a href="Result?limit=10" style="text-decoration: underline;"> 10</a> <b>|</b>
	<a href="Result?limit=15" style="text-decoration: underline;"> 15</a> <b>| </b>
	<a href="Result?limit=20" style="text-decoration: underline;"> 20</a> <b>| </b>
	<a href="Result?limit=25" style="text-decoration: underline;"> 25</a> 
	</div>
	</div>
	
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>

	<div class="footer" align="center"><a href="">Home</a> | <a href="">About Us</a> | <a href="">Register an Account</a> | <a href="">Privacy Policy</a> | <a href="">Check Out</a>
	</div>
</body>
</html>	
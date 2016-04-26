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
	<title>FabFlix | Star</title>
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

	<%
	Star starInfo = (Star) request.getAttribute("starInfo");
	ArrayList<Movie> movieOfStar = (ArrayList<Movie>) request.getAttribute("movieOfStar");
	
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
	
	<div align="center">
	
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
          <table width="904" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <div class="content">
                  <h2>Star Profile</h2>
                  <div align="center">
				  
	<div class="tinyspacer">&nbsp;</div>
                    <table width="700" border="0" cellspacing="0" cellpadding="0">
                      <tr>
						<td style="text-align:left;" height="25">Name:</td>
						<%String name= starInfo.getFirst_name() + " " + starInfo.getLast_name(); %>
                        <td style="text-align:left;"><%=name %></td>
						
						<td width="100" height="25"></td>
						<td>&nbsp;</td>
						<td width="250" rowspan="5" valign="top"><img src= "<%=starInfo.getPhoto_url()%>" onerror="this.onerror=null;whenerror(this);" width=80%;>&nbsp;</td>
                      </tr>
      
                      <tr>
                        <td style="text-align:left;" height="25">Date of Birth:</td>
                        <td style="text-align:left;"><%=starInfo.getDob() %></td>
                      </tr>
                      <tr>
                        <td style="text-align:left;" height="25">ID:</td>
                        <td style="text-align:left;"><%=starInfo.getId() %></td>
                      </tr>
                      <tr>

                      </tr>
                    </table>
                    &nbsp;<br />	
                    </div>
                  &nbsp;<br />
                  <br />
                  &nbsp;<br />
                 </td>
            </tr>
          </table>
        </div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="tinyspacer">&nbsp;</div>
	<div class="footer" align="center"><a href="Main.html">Home</a> | <a href="advanceSearch.html">Advanced Search</a> | <a href="addToCart.jsp">My Cart</a>
	</div>
</body>
</html>
<%@page language='java'%>
<%@page import='java.*'%>
<%@page import="java.util.LinkedList" %>
<%@page import="javax.servlet.*" %>
<%@page import="fabflix.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.Date" %>
<%@page import = "java.sql.ResultSet"%>
<%@page import ="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.Statement" %>


<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="main.css">
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DashBoard</title>
    </head>
    <body>
    	<%LinkedList<String> metadata = (LinkedList<String>) QueryToDatabase.showMetaData() ;%>  
       	<div class="addstars">
			<h1>FabFlix DashBoard</h1>  
    	<form method="post" action="AddStar">
    	
            <table border="1" width="100%" cellpadding="5" top=50%;>
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
		    <tr>
			<td>Star ID</td>
			<td><input type="text" name="starred" value"" /></td>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="fname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Date of Birth</td>
                        <td><input type="text" name="dob" value="0101/01/01" /></td>
                    </tr>
                    <tr>
                        <td>Photo URL</td>
                        <td><input type="text" name="purl" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit" /></td>
                    </tr>

                </tbody>
            </table>
            </form>
       
	      </div>
	      
	      <div class = "moviestar">
	      <p><h3> ADD MOVIE AND STAR: </h3></p>
	      <a href="addMovieAndStar.jsp" style="align: middle; color: red;"> Click Here! </a>
	      </div>
	      
		<div class = "metadata" >
			<h1> FabFlix MetaData</h1>
            <%for(int i = 0; i<metadata.size(); i++){%>
            <p><%=metadata.get(i)%></p>
            <%}%>
            
            </div>    
 	
    </body>
</html>
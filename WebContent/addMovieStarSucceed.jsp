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
       	<h1 style="color: red; text-align: center;">Successfully added movie and star!</h1>
       	<div class="addstars">
			<h1>FabFlix DashBoard</h1>  
    	<form method="post" action="AddMovieAndStar">
    	            <table border="1" width="100%" cellpadding="5" top=50%;>
                <thead>
                    <tr>
                        <th colspan="2">Movie's Information</th>
                    </tr>
                </thead>
                <tbody>
                
             <tr>
                        <td>Title</td>
                        <td><input type="text" name="mtitle" value="" /></td>
                    </tr>
                    <tr>
                        <td>Year</td>
                        <td><input type="text" name="myear" value="" /></td>
                    </tr>
                    <tr>
                        <td>Director</td>
                        <td><input type="text" name="mdirector" value="" /></td>
                    </tr>
                    <tr>
                        <td>Banner URL</td>
                        <td><input type="text" name="burl" value="" /></td>
                    </tr>
		    <tr>
			<td>Trailer URL</td>
			<td><input type="text" name="turl" value"" /></td>
			</tr>
			 <tr>
			<td>Genre</td>
			<td><input type="text" name="genre" value"" /></td>
			</tr>
                   

                </tbody>
    		</table>
			<br></br>    	
            <table border="1" width="100%" cellpadding="5" top=50%;>
                <thead>
                    <tr>
                        <th colspan="2">Star's information</th>
                    </tr>
                </thead>
                <tbody>
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
                        <td><input type="text" name="dob" value="0001/01/01" /></td>
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
	      

	      

 	
    </body>
</html>
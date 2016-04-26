<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page language='java'%>
<%@page import='java.*'%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.servlet.*" %>
<%@page import="fabflix.*"%>

<%
 
	String query = request.getParameter("q");
	String[] split = query.toLowerCase().split("\\W+");
	ArrayList<String> keyWords = new ArrayList<String>();
	for(int i = 0; i < split.length; i++){
		 keyWords.add(split[i]);
	}
	ArrayList<Movie> movieReturn = QueryToDatabase.autoCompleteSearch(keyWords);
	for(int i = 0; i < movieReturn.size(); i++){
			out.println(movieReturn.get(i).getTitle());
	}
	
	
%>
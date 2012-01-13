<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="menu.jsp" />

<%
	String reg = (String) request.getAttribute("reg");
	if (reg != null && (reg.compareTo("true") == 0))
		out.println("<p>Register succeed</p>");
	else if (reg != null && reg.length() > 0)
		out.println("<p>Register failed</p>");
	else
		out.println("<a href=\"/epimarket/app/register\">Don\'t have an account yet, click here</a>");
%>


<p>Test de page de login</p>
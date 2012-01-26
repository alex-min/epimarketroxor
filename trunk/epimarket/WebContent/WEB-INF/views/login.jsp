<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<form method="POST" action="loginaction">
<table>
<tr>
<td><input name="login" class="post"/></td>
<td><input name="password" class="post"/></td>
<td><input class="submitform post" type="submit" value="Add Author"></td>
</tr>
</table>
</form>
			
			
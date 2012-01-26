<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="menu.jsp" />

<%
	String reg = (String) request.getAttribute("reg");
	if (reg != null && (reg.compareTo("true") == 0))
		out.println("<p>Register succeed</p>");
	else if (reg != null && reg.length() > 0)
		out.println("<p>Register failed</p>");
	else
		out.println("<a href=\"/epimarket/app/register\">Don\'t have an account yet ?, click here</a>");
%>

<form method="POST" action="login">
<table>
<tr>
<td><spring:message code="user.login"/></td>
<td><input name="login" class="post"/></td>
<tr>
<td><spring:message code="user.password"/></td>
<td><input name="password" class="post" type="password"/></td>
<tr><td><input class="submitform post" type="submit" value="Login"></td></tr>
</table>
</form>
			
			
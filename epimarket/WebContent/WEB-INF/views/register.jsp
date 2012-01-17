<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="menu.jsp" />

<p> Test de page d'inscription </p>



<div>
	<form:form method="POST" action="registration" commandName="command">
	<table>
		<tr>
			<td>Login</td>
			<td><form:input path="login"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><form:input path="password"/></td>
		</tr>
		<tr>
			<td>Age:</td>
			<td>
			</td>
		</tr>
		<tr>
		<td>
			<input class="submitform" type="submit" value="Inscription">
		</td>
		</tr>
	</table>
	</form:form>


</div>


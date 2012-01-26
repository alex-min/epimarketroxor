<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="menu.jsp" />


<div>
	<form:form method="POST" action="registration" commandName="command">
	<table>
		<tr>
			<td><spring:message code="user.login"/></td>
			<td><form:input path="login"/></td>
		</tr>
		<tr>
			<td><spring:message code="user.password"/></td>
			<td><form:input path="password"/>
			<!--  add -->
			</td>
		</tr>
		<tr>
			<td><spring:message code="user.email"/></td>
			<td><form:input path="mail"/>
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


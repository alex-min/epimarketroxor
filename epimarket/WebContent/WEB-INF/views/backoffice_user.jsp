<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"></jsp:include>

<p>Temporary</p>
<h1>User Management</h1>
<div style="border: 1px solid">
<p>User List</p>
<table>
	<tr>
		<th><spring:message code="user.id"/></th>
		<th><spring:message code="user.login"/></th>
		<th><spring:message code="user.email"/></th>
		<th><spring:message code="user.rights"/></th>
	</tr>
	<c:forEach items="${users }" var="current">
	<tr>
	<td>${current.id }</td>
	<td>${current.login }</td>
	<td>${current.mail }</td>
	<td class="autoreplace" abbr="rights">${current.rights }</td>
	<td><a href="delete/${current.id}/">
	<spring:message code="backoffice.user.delete"/>
	</a></td>
	</tr>
	</c:forEach>	
</table>
</div>
<div style="border: 1px solid">
	<p>Add book</p>
	<table>
		<tr>
			<th><spring:message code="user.login"/></th>
			<th><spring:message code="user.email"/></th>
			<th><spring:message code="user.password"/></th>
			<th><spring:message code="user.rights"/></th>
		</tr>
		<tr>
			<form:form method="POST" action="useraddaction" commandName="useradd">
			<td><form:input path="login"/></td>
			<td><form:input path="mail"/></td>
			<td><form:input path="password"/></td>
			<td><form:input id="rightsautocomplete" class="ac_input" path="rights" /></td>
			<td><input class="submitform" type="submit" value="Add User"></td>
			</form:form>
		</tr>
	</table>
</div>
<script type="text/javascript" src="/epimarket/js/autocomplete.js"></script>
<script type="text/javascript" src="/epimarket/js/backoffice_book.js"></script>
<script type="text/javascript">
AddCompletion("rights", false, 0);
</script>
${sessionScope.lasterror }


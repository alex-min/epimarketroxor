<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="adminmenu.jsp"></jsp:include>

<h1>User Management</h1>
<div style="border: 1px solid">
<p class="e-pannel">User List</p>
<table abbr="user">
	<tr>
		<th ><spring:message code="user.id"/></th>
		<th abbr="login"><spring:message code="user.login"/></th>
		<th abbr="mail"><spring:message code="user.email"/></th>
		<th abbr="rights"><spring:message code="user.rights"/></th>
	</tr>
	<c:forEach items="${users }" var="current">
	<tr>
	<td><div>${fc:escapeXml(current.id) }</div></td>
	<td><div class="editable">${fc:escapeXml(current.login) }</div></td>
	<td><div class="editable">${fc:escapeXml(current.mail) }</div></td>
	<td><div class="editable replacenext" abbr="rights">${current.rights }</div></td>
	<td><div class="autoreplace" abbr="rights">${current.rights }</div></td>
	<td><a href="delete/${current.id}/">
	<spring:message code="backoffice.user.delete"/>
	</a></td>
	</tr>
	</c:forEach>	
</table>
</div>
<div style="border: 1px solid">
	<p class="e-pannel">Add book</p>
	<table>
		<tr>
			<th><spring:message code="user.login"/></th>
			<th><spring:message code="user.email"/></th>
			<th><spring:message code="user.password"/></th>
			<th><spring:message code="user.rights"/></th>
		</tr>
		<tr>
			<form:form method="POST" action="useraddaction" commandName="useradd">
			<td><form:input path="login" class="post"/></td>
			<td><form:input path="mail" class="post"/></td>
			<td><form:input path="password" class="post"/></td>
			<td><form:input id="rightsautocomplete" class="ac_input post" path="rights" /></td>
			<td><input class="submitform post" type="submit" value="Add User"></td>
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


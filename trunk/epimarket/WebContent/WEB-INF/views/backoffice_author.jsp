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

<h1>Author Management</h1>
<div style="border: 1px solid">
<p>Author List</p>
<table abbr="author">
	<tr>
		<th ><spring:message code="author.id"/></th>
		<th abbr="firstName"><spring:message code="author.firstName"/></th>
		<th abbr="lastName"><spring:message code="author.lastName"/></th>
	</tr>
	<c:forEach items="${authors }" var="current">
	<tr>
	<td><div>${fc:escapeXml(current.id) }</div></td>
	<td><div class="editable">${fc:escapeXml(current.firstName) }</div></td>
	<td><div class="editable">${fc:escapeXml(current.lastName) }</div></td>
	<td><a href="delete/${current.id}/">
	<spring:message code="backoffice.author.delete"/>
	</a></td>
	</tr>
	</c:forEach>	
</table>
</div>
<div style="border: 1px solid">
	<p>Add Author</p>
	<table>
		<tr>
			<th><spring:message code="author.firstName"/></th>
			<th><spring:message code="author.lastName"/></th>
		</tr>
		<tr>
			<form:form method="POST" action="authoraddaction" commandName="authoradd">
			<td><form:input path="firstName" class="post"/></td>
			<td><form:input path="lastName" class="post"/></td>
			<td><input class="submitform post" type="submit" value="Add Author"></td>
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


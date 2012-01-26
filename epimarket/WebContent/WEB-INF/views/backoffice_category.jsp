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

<h1>Cateory Management</h1>
<div style="border: 1px solid">
<p class="e-pannel">Category List</p>
<table abbr="category">
	<tr>
		<th ><spring:message code="category.id"/></th>
		<th abbr="name"><spring:message code="category.name"/></th>
	</tr>
	<c:forEach items="${categories }" var="current">
	<tr>
	<td><div>${fc:escapeXml(current.id) }</div></td>
	<td><div class="editable">${fc:escapeXml(current.name) }</div></td>
	<td><a href="delete/${current.id}/">
	<spring:message code="backoffice.category.delete"/>
	</a></td>
	</tr>
	</c:forEach>	
</table>
</div>
<div style="border: 1px solid">
	<p class="e-pannel">Add book</p>
	<table>
		<tr>
			<th><spring:message code="category.name"/></th>
		</tr>
		<tr>
			<form:form method="POST" action="categoryaddaction" commandName="categoryadd">
			<td><form:input path="name" class="post"/></td>
			<td><input class="submitform post" type="submit" value="Add Category"></td>
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


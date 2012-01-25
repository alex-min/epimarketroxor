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

<h1>Book Management</h1>
<div style="border: 1px solid">
<p>Book List</p>
<table>
	<tr>
		<th><spring:message code="market.table.cover_picture"/></th>
		<th><spring:message code="market.table.book_id"/></th>
		<th><spring:message code="market.table.title"/></th>
		<th>Author</th>
	</tr>
	<c:forEach items="${books }" var="current">
	<tr>
	<td>
		<c:choose>
		<c:when test="${current.picture != null && current.picture}"></c:when>
		<c:otherwise><img src="/epimarket/resources/empty-book.jpg" /></c:otherwise>
		</c:choose>
	</td>
	<td>${current.id }</td>
	<td>${current.title }</td>
	<td>${current.author.firstName } ${current.author.lastName }</td>
	<td><a href="delete/${current.id}/">
	<spring:message code="backoffice.delete"/>
	</a></td>
	</tr>
	</c:forEach>	
</table>
</div>
<div style="border: 1px solid">
	<p>Add book</p>
	<table>
		<tr>
			<th><spring:message code="market.table.cover_picture"/></th>
			<th><spring:message code="market.table.stock"/></th>
			<th><spring:message code="market.table.title"/></th>
			<th>Author</th>
			<th>Category</th>
		</tr>
		<tr>
			<form:form method="POST" action="bookaddaction" commandName="bookadd">
			<td><form:input id="pictureautocomplete" class="ac_input" path="picture"/></td>
			<td><form:input path="stock"/></td>
			<td><form:input path="title"/></td>
			<td><form:input path="author.id" id="authorautocomplete" class="ac_input"/></td>
			<td><form:input path="category.id" id="categoryautocomplete" class="ac_input"/></td>
			<td><input class="submitform" type="submit" value="Add Book"></td>
			</form:form>
		</tr>
	</table>
</div>
<script type="text/javascript" src="/epimarket/js/autocomplete.js"></script>
<script type="text/javascript" src="/epimarket/js/backoffice_book.js"></script>
<script type="text/javascript">
AddCompletion("category", true, 2);
AddCompletion("author", true, 2);
AddCompletion("picture", true, 2);
</script>


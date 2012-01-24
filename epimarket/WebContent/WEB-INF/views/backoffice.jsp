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

<div>
<p>User management</p>
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
<div>
	<form:form method="POST" action="bookaddaction" commandName="bookadd">
	<form:input path="picture"/>
	<form:input path="stock"/>
	<form:input path="title"/>
	<form:input path="author"/>
	<input class="submitform" type="submit" value="Inscription">
	</form:form>
</div>

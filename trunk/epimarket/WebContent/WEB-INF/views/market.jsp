<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="menu.jsp" />

<table>
<tr>
<th><spring:message code="market.table.cover_picture"/></th>
<th><spring:message code="market.table.book_id"/></th>
<th><spring:message code="market.table.title"/></th>
<th><spring:message code="market.table.stock"/></th>
<th></th>
</tr>
<c:forEach items="${books}" var="current">
<tr>
<td>
	<c:choose>
	<c:when test="${current.picture != null && current.picture}"></c:when>
	<c:otherwise><img src="/epimarket/resources/empty-book.jpg" /></c:otherwise>
	</c:choose>
</td>
<td>${current.id }</td>
<td>${current.title }</td>
<td>
	<c:choose>
	<c:when test="${current.stock == 0 }">
		<spring:message code="market.out_of_stock"/>
	</c:when>
	<c:otherwise>
		${current.stock }
	</c:otherwise>
	</c:choose>
	
</td>
<td>
<c:if test="${sessionScope.webdata.user.logged == true}">
<a href="market/book/${current.id }/addCart"><spring:message code="market.addToCart"/></a>
</c:if>
</td>
</tr>

</c:forEach>

</table>

<jsp:include page="footer.jsp" />

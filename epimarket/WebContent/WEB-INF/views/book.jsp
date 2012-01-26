<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="menu.jsp" />

${fc:escapeXml(bookId.title) }

<p>
	<c:if test="${added == true}">
		Added to Cart
	</c:if>
</p>

<p>
	<c:if test="${bookId.stock > 0}">
		<a href="addCart">add to cart</a>
	</c:if>
	<c:if test="${bookId.stock <= 0}">
		Not Available
	</c:if>
</p>

<!--<c:if
	test="${ sessionScope.webData != null || 
	sessionScope.webData.getUser().isLogged() == true}">-->

<!--</c:if>
-->

<jsp:include page="footer.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="menu.jsp" />

<table>
	<tr>
		<th><spring:message code="market.table.title"/></th>
		<th><spring:message code="market.table.author"/></th>
		<th><spring:message code="market.table.stock"/></th>
		<th><spring:message code="market.removeOne"/></th>
		<th><spring:message code="market.removeAll"/></th>
	</tr>

	<c:if test="${listCart != null && listCart.isEmpty() == false}">
		<c:forEach items="${listCart}" var="curr">
			<tr>
				<td>${fc:escapeXml(curr.title) }</td>
				<td>${fc:escapeXml(curr.author.lastName) } ${fc:escapeXml(curr.author.firstName) }</td>
				<td>${cartMap.get(curr.id)}</td>
				<td><a href="removeOne/${curr.id}">click</a></td>
				<td><a href="removeAll/${curr.id}">click</a></td>
			</tr>
		</c:forEach>
	</c:if>
</table>

	<c:if test="${listCart == null || listCart.isEmpty() == true }">
		<spring:message code="market.cartEmpty"/>.
	</c:if>
	<c:if test="${listCart != null && listCart.isEmpty() == false }">
		<a href="checkout/">Cliquez ici pour terminer votre commande.</a>
	</c:if>
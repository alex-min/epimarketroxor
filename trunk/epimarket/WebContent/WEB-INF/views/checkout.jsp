<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	

<jsp:include page="menu.jsp" />

<p>Test de checkout du panier d'achat</p>

<p>
	<c:if test="${lNotAvailable != null && lNotAvailable.isEmpty() == false}">
		<c:if test="${lNotAvailable.size() > 1}">
			"Not enough stock for these items : "<br />
		</c:if>
		<c:if test="${lNotAvailable.size() == 1 }">
			Not enough stock for this item :<br />
		</c:if>
		<c:forEach items="${lNotAvailable}" var="curr">
			${fc:escapeXml(curr) }<br />
		</c:forEach>
		<br />
		Veuillez retirez les articles manquant de votre commande. 
	</c:if>

	<c:if test="${lNotAvailable == null || lNotAvailable.isEmpty() == true }">
		Total de votre commande :
		<f:formatNumber type="number" pattern="#.##" value="${fullPrice}" />
		<br />
		<a href="validation">Cliquez ici pour valider votre commande</a>
	</c:if>
</p>

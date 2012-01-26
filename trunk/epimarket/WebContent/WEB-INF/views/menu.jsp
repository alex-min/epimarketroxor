<%@ page import="com.epimarket.webdata.WD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="header.jsp" />


<ul>
	${sessionScope.webdata.user.logged }
	<c:if test="${ sessionScope.webdata == null || 
	sessionScope.webdata.user.logged == false}">
	<li><a href="/epimarket/app/login"><spring:message code="menu.login"/></a></li>
	<li><a href="/epimarket/app/register"><spring:message code="menu.register"/></a></li>
	</c:if>
	
	<li><a href="/epimarket/app/market"><spring:message code="menu.market"/></a></li>
	
	<c:if test="${ sessionScope.webdata == null || 
	sessionScope.webdata.user.logged == false}">
		<li><a href="/epimarket/app/market/cart/"><spring:message code="menu.cart"/></a></li>
	</c:if>

</ul>
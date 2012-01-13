<%@page import="com.epimarket.webdata.WD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
		
<jsp:include page="header.jsp" />

<ul>
	<c:if test="${ sessionScope.webData.getUser().isLogged() == false}">
	<li><a href="/epimarket/app/login">Login</a></li>
	</c:if>
	
	<li><a href="/epimarket/app/market">Market</a></li>
	
	<c:if test="${ sessionScope.webData.getUser().isLogged() == false}">
		<li><a href="/epimarket/app/market/cart">Cart</a></li>
	</c:if>

</ul>
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

<div class="topbar">
<div class="topbar-inner">
<div class="container">
<ul class="nav">
		<li><a href="/epimarket/app/"><spring:message code="admin.back"/></a></li>
		<li><a href="/epimarket/app/admin/book/"><spring:message code="admin.book"/></a></li>
		<li><a href="/epimarket/app/admin/user/"><spring:message code="admin.user"/></a></li>
		<li><a href="/epimarket/app/admin/category/"><spring:message code="admin.category"/></a></li>
		<li><a href="/epimarket/app/admin/author/"><spring:message code="admin.author"/></a></li>
</ul>
</div>
</div>
</div>

    
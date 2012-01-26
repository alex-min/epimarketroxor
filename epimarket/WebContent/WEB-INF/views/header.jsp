<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/epimarket/js/jquery.js"></script>
<script type="text/javascript" src="/epimarket/js/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="/epimarket/style/jquery.autocomplete.css" type="text/css" />
<link rel="stylesheet" href="/epimarket/style/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/epimarket/style/style.css" type="text/css" />
	
<title>${fc:escapeXml(webtitle) }</title>
</head>
<body>
<a href="/epimarket/app/" alt="banner" >
<img src="/epimarket/resources/banner.png" class="banner"/>
</a>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="menu.jsp" />

<p>stats</p>

<div>
<img src="http://chart.apis.google.com/chart?chxl=0:${barBottom}|1:${max1}&chxt=x,y&chs=800x300&cht=bvg&chd=t:${barValue1}&chg=20,50&chtt=${bTitle1}&chma=25"/>
</div>
<div>
<img src="http://chart.apis.google.com/chart?chxl=0:${barBottom}|1:${max2}&chxt=x,y&chs=800x300&cht=bvg&chd=t:${barValue2}&chg=20,50&chtt=${bTitle2}"/>
</div>

<div>
<img src="https://chart.googleapis.com/chart?cht=p3&chs=750x300&chd=t:${pieValue}&chl=${pieName}&chtt=${pieTitle}" border="0"/>
</div>

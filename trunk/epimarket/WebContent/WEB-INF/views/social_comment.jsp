<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="market.jsp" />
<table>
  <tr>
    <th>Login</th>
    <th>Comment</th>
  </tr>
  <c:forEach items="${comments }" var="com">
  <tr>
  	<td> ${com.user.login }</td>
  	<td> ${com.comment }</td>
  </tr>
  </c:forEach>
  <tr>
  <c:if test="${sessionScope.webdata.user.logged == true }">
   <td> ${ sessionScope.webdata.user.user.login }</td>
   <td> <form action="add" method="POST" >
   	<input type="text" name="comment"/>
   	<input type="hidden" name="id" value="${books[0].id }"/>
   </form>
   
   </td>
  </c:if>
  </tr>
</table>

<jsp:include page="footer.jsp"></jsp:include>

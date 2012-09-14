<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quotes</title>
    <style>
    	.error {
    		color: #ff0066;
    		font-weitgh: bold;
    	}
    	.result {
    		color: #006633;
    	}
    </style>
</head>
<body>

<form method="post" action="trader">
Enter quote: <input type="text" name="quote" value=""/><span class="error"><c:out value="${error}"/></span><br/>
<input type="submit" value="Send">
</form>

<c:if test="${not empty stock}">
<p class="result">
	<c:out value="${stock.symbol}"/>: <c:out value="${stock.quote}"/>
</p>
</c:if>

</body>
</html>
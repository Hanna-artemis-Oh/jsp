<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
	<h2>useBean 액션 태그</h2>
	<h3>자바빈즈 생성하기</h3>
	<jsp:useBean id="person" class="common.Person" scope="request" />
	
	<h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
	<jsp:setProperty property="name" name="person" value="임꺽정" />
	<jsp:setProperty property="age" name="person" value="41" />
	
	<h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
	<ul>
		<li><jsp:getProperty property="name" name="person" /></li>
		<li><jsp:getProperty property="age" name="person" /></li>
	</ul>
</body>
</html>
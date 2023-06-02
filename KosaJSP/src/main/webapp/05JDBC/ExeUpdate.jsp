<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
    <%
	    // DB에 연결
	    JDBConnect jdbc = new JDBConnect();
    	
	    
	    // 테스트용 입력값 준비 
	    String id = "test1";
	    String pass = "1111";
	    String name = "테스트1회원";
	
	    // 쿼리문 생성
	    String sql = "INSERT INTO members VALUES (?, ?, ?, sysdate)";  
	    PreparedStatement psmt = jdbc.conn.prepareStatement(sql);  
	    psmt.setString(1, id);
	    psmt.setString(2, pass);
	    psmt.setString(3, name);
	
	    // 쿼리 수행
	    int inResult = psmt.executeUpdate(); 
	    out.println(inResult + "행이 입력되었습니다.");
	
	    // 연결 닫기
	    jdbc.close(); 
    %>
</body>
</html>
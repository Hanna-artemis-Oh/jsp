package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import common.JDBConnect;

public class BoardDAO {
	
	public JDBConnect jdbc;

	public BoardDAO(ServletContext application) {
		jdbc = new JDBConnect(application);
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM boards WHERE 1 = 1 ";
		if (map.get("searchWord") != null) {
			query += "AND " + map.get("searchField") + " "
				  + "LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			jdbc.stmt = jdbc.conn.createStatement();
			jdbc.rs = jdbc.stmt.executeQuery(query);
			jdbc.rs.next();
			totalCount = jdbc.rs.getInt(1);			
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생!!!");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		String query = "SELECT * FROM boards WHERE 1 = 1 ";
		if (map.get("searchWord") != null) {
			query += "AND " + map.get("searchField") + " "
				  + "LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY num DESC ";
		
		try {
			jdbc.stmt = jdbc.conn.createStatement();
			jdbc.rs = jdbc.stmt.executeQuery(query);
			
			while(jdbc.rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(jdbc.rs.getString("num"));
				dto.setTitle(jdbc.rs.getString("title"));
				dto.setContent(jdbc.rs.getString("content"));
				dto.setPostdate(jdbc.rs.getDate("postdate"));
				dto.setId(jdbc.rs.getString("id"));
				dto.setVisitcount(jdbc.rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생!!!");
			e.printStackTrace();
		}
		
		return bbs;
	}
}

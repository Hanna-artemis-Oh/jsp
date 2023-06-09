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
	
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO boards ( "
						 + "num, title, content, id, visitcount) "
						 + "VALUES ( "
						 + "seq_board_num.NEXTVAL, ?, ?, ?, 0)";
			
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, dto.getTitle());
			jdbc.psmt.setString(2, dto.getContent());
			jdbc.psmt.setString(3, dto.getId());
			
			result = jdbc.psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query = "SELECT b.*, m.name "
					 + "FROM boards b JOIN members m "
					 + "ON b.id = m.id "
					 + "WHERE num=?";
		
		try {
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, num);
			jdbc.rs = jdbc.psmt.executeQuery();
			
			if (jdbc.rs.next()) {
				dto.setNum(jdbc.rs.getString(1));
				dto.setTitle(jdbc.rs.getString(2));
				dto.setContent(jdbc.rs.getString("content"));
				dto.setPostdate(jdbc.rs.getDate("postdate"));
				dto.setId(jdbc.rs.getString("id"));
				dto.setVisitcount(jdbc.rs.getString(6));
				dto.setName(jdbc.rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void updateVisitCount(String num) {
		String query = "UPDATE boards "
					 + "SET visitcount=visitcount+1 "
				     + "WHERE num=?";
		
		try {
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, num);
			jdbc.psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE boards "
						 + "SET title=?, content=? "
						 + "WHERE num=?";
			
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, dto.getTitle());
			jdbc.psmt.setString(2, dto.getContent());
			jdbc.psmt.setString(3, dto.getNum());
			
			result = jdbc.psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "DELETE boards WHERE num=?";
			
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, dto.getNum());
			
			result = jdbc.psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		String query = "";
		query += "SELECT * "
			   + "FROM (SELECT ROWNUM AS rn, p.*"
			   + "      FROM (SELECT *"
			   + "            FROM boards"
			   + "            WHERE 1 = 1 ";
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") 
				   + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "      ORDER BY num DESC"
			   + "      ) p "
			   + ") "
			   +"WHERE rn BETWEEN ? AND ?";
		try {
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, map.get("start").toString());
			jdbc.psmt.setString(2, map.get("end").toString());
			
			jdbc.rs = jdbc.psmt.executeQuery();
			
			while (jdbc.rs.next()) {
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
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
}

package membership;

import common.JDBConnect;

public class MemberDAO {
	
	public JDBConnect jdbc;
	
	public MemberDAO(String drv, String url, String id, String pw) {
		jdbc = new JDBConnect(drv, url, id, pw);
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			jdbc.psmt = jdbc.conn.prepareStatement(query);
			jdbc.psmt.setString(1, uid);
			jdbc.psmt.setString(2, upass);
			jdbc.rs = jdbc.psmt.executeQuery();
			
			if (jdbc.rs.next()) {
				dto.setId(jdbc.rs.getString("id"));
				dto.setPass(jdbc.rs.getString("pass"));
				dto.setName(jdbc.rs.getString(3));
				dto.setRegidate(jdbc.rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

}

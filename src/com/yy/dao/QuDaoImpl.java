package com.yy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QuDaoImpl extends BaseDaoImpl {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	public JSONArray search() {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {

			pstmt = conn.prepareStatement("select * from qu");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("qid", rs.getInt("qid"));
				content.put("qname", rs.getString("qname"));
				list.add(content);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

}

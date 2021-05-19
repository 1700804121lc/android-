package com.yy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yy.entity.User;
import com.yy.entity.YiSheng;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class YiShengDaoImpl extends BaseDaoImpl {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	/**
	 * 医生查询患者挂号信息
	 * @param ysid
	 * @return
	 */
	public JSONArray search(int ysid) {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {

			pstmt = conn.prepareStatement("select * from guahao g left join users u on u.uid = g.uid left join qu q on q.qid=u.qid where g.ysid= "+ysid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("gid", rs.getInt("gid"));
				content.put("kid", rs.getInt("kid"));
				content.put("statu", rs.getInt("statu"));
				content.put("uid", rs.getInt("uid"));
				content.put("time", rs.getString("time"));
				content.put("ping", rs.getString("ping"));
				
				content.put("name", rs.getString("name"));
				content.put("icon", rs.getString("icon"));
				content.put("sex", rs.getString("sex"));
				content.put("phone", rs.getString("phone"));
				content.put("years", rs.getString("years"));
				content.put("sumary", rs.getString("sumary"));
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
	
	/**
	 * 用户查询预约的医生
	 * @param uid
	 * @return
	 */
	public JSONArray UserSearchYuYue(int uid) {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {
			
			pstmt = conn.prepareStatement("select * from guahao g left join yisheng u on u.ysid = g.ysid  where g.uid= "+uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("gid", rs.getInt("gid"));
				content.put("kid", rs.getInt("kid"));
				content.put("statu", rs.getInt("statu"));
				content.put("uid", rs.getInt("uid"));
				content.put("time", rs.getString("time"));
				content.put("ping", rs.getString("ping"));
				
				content.put("name", rs.getString("name"));
				content.put("icon", rs.getString("icon"));
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
	
	/**
	 * 更新
	 * @param c
	 * @return
	 */
	public boolean update_guahao(int gid,int statu) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("update guahao set statu=? where gid='"
							+ gid + "'");

			pstmt.setInt(1, statu);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	public boolean update_guahao(int gid,String ping) {
		conn = this.getConnection();
		try {
			pstmt = conn
			.prepareStatement("update guahao set ping=?,statu=? where gid='"
					+ gid + "'");
			
			pstmt.setString(1, ping);
			pstmt.setInt(2, 4);
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	
	
	
	
	
	public JSONArray searchBaoGao(int gid,int type) {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {
			
			pstmt = conn.prepareStatement("select * from baogao  where gid= "+gid+" and type="+type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("gid", rs.getInt("gid"));
				content.put("bid", rs.getInt("bid"));
				content.put("content", rs.getString("content"));
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
	
	public boolean addBaoGao(int gid,int type,String content) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into baogao(gid,type,content)values(?,?,?)");
			pstmt.setInt(1, gid);
			pstmt.setInt(2,type);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	public boolean deleteBaoGao(int bid) {
		conn = this.getConnection();
		try {
			pstmt = conn.prepareStatement("delete from baogao where bid=" + bid);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	
	
	
	
	
	public YiSheng loginYS(String user, String password) {
		conn = this.getConnection();

		try {
			pstmt = conn
					.prepareStatement("select * from yisheng where user = '"
							+ user + "' and pswd='"+password+"'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				YiSheng content = new YiSheng();
				content.setYsid(rs.getInt("ysid"));
				content.setKid(rs.getInt("kid"));
				content.setYid(rs.getInt("yid"));
				content.setGua(rs.getInt("gua"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));
				content.setJianjie(rs.getString("jianjie"));
				content.setTechang(rs.getString("techang"));
				content.setUser(rs.getString("user"));
				content.setPswd(rs.getString("pswd"));
				return content;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return null;

	}
	
	public boolean update_icon(int id, String icon) {
		conn = this.getConnection();
		try {
			pstmt = conn.prepareStatement("update yisheng set icon=? where ysid='"
					+ id + "'");
			pstmt.setString(1, icon);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	
	public boolean update_message(YiSheng c) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("update yisheng set name=?,jianjie=?,techang=?,gua=?,pswd=? where ysid='"
							+ c.getYsid() + "'");

			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getJianjie());
			pstmt.setString(3, c.getTechang());
			pstmt.setInt(4, c.getGua());
			pstmt.setString(5, c.getPswd());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	
	
	
	//预约社区人员
	public boolean SaveSheQuYuYue(int uid,int suid,String content,String time) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into sqyy(uid,suid,content,time,statu)values(?,?,?,?,?)");
			pstmt.setInt(1, uid);
			pstmt.setInt(2,suid);
			pstmt.setString(3, content);
			pstmt.setString(4, time);
			pstmt.setInt(5, 1);
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	
	/**
	 * 用户查询预约的社区人员
	 * @param uid
	 * @return
	 */
	public JSONArray UserSearchSQYuYue(int uid) {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {
			
			pstmt = conn.prepareStatement("select * from sqyy g left join users u on u.uid = g.suid  where g.uid= "+uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("sid", rs.getInt("sid"));
				content.put("suid", rs.getInt("suid"));
				content.put("statu", rs.getInt("statu"));
				content.put("uid", rs.getInt("uid"));
				content.put("time", rs.getString("time"));
				content.put("content", rs.getString("content"));
				
				content.put("name", rs.getString("name"));
				content.put("icon", rs.getString("icon"));
				content.put("sumary", rs.getString("sumary"));
				content.put("sex", rs.getString("sex"));
				content.put("phone", rs.getString("phone"));
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
	
	public JSONArray SheQuSearchYuYue(int uid) {
		conn = this.getConnection();
		JSONArray list = new JSONArray();
		try {
			
			pstmt = conn.prepareStatement("select * from sqyy g left join users u on u.uid = g.uid  where g.suid= "+uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject content = new JSONObject();
				content.put("sid", rs.getInt("sid"));
				content.put("suid", rs.getInt("suid"));
				content.put("statu", rs.getInt("statu"));
				content.put("uid", rs.getInt("uid"));
				content.put("time", rs.getString("time"));
				content.put("content", rs.getString("content"));
				
				content.put("name", rs.getString("name"));
				content.put("icon", rs.getString("icon"));
				content.put("sumary", rs.getString("sumary"));
				content.put("sex", rs.getString("sex"));
				content.put("phone", rs.getString("phone"));
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
	
	public boolean update_sqyy(int id,int statu) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("update sqyy set statu=? where sid='"
							+ id + "'");

			pstmt.setInt(1, statu);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	public boolean update_sqyy(int id,String ping) {
		conn = this.getConnection();
		try {
			pstmt = conn
			.prepareStatement("update sqyy set ping=?,statu=? where id='"
					+ id + "'");
			
			pstmt.setString(1, ping);
			pstmt.setInt(2, 4);
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}
	
	
}

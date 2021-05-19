package com.yy.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yy.entity.Get;
import com.yy.entity.Keshi;
import com.yy.entity.User;
import com.yy.entity.Yao;
import com.yy.entity.YiSheng;
import com.yy.entity.Yiyuan;

public class DaoImpl extends BaseDaoImpl {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	/**
	 * 根据用户名查询用户是否注册
	 * 
	 * @return
	 */
	public boolean isCreate(String user) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from users where user='"
					+ user + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		} finally {
			this.closeAll(rs, pstmt, conn);

		}

	}
	
	public boolean update_icon(int uid, String icon) {
		conn = this.getConnection();
		try {
			pstmt = conn.prepareStatement("update users set icon=? where uid='"
					+ uid + "'");
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

	/**
	 * 保存用户信息
	 * 
	 * @param news
	 */
	public boolean Save(User jg) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into users(user,pswd,name,phone,years,sex,sumary,type,qid,icon)values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, jg.getUser());
			pstmt.setString(2, jg.getPswd());
			pstmt.setString(3, jg.getName());
			pstmt.setString(4, jg.getPhone());
			pstmt.setString(5, jg.getYears());
			pstmt.setString(6, jg.getSex());
			pstmt.setString(7, jg.getSumary());
			pstmt.setString(8, jg.getType());
			pstmt.setInt(9, jg.getQid());
			pstmt.setString(10, jg.getIcon());
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
	 * 根据用户名和密码判断是否登录成功
	 * 
	 * @return
	 */
	public User login(String user, String password) {
		conn = this.getConnection();
		User content = null;
		try {

			pstmt = conn.prepareStatement("select u.*,q.qname from users u left join qu q on q.qid=u.qid where u.user='"
					+ user + "'and u.pswd='" + password + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content = new User();
				content.setUid(rs.getInt("uid"));
				content.setQid(rs.getInt("qid"));
				content.setUser(rs.getString("user"));
				content.setPswd(rs.getString("pswd"));
				content.setName(rs.getString("name"));
				content.setPhone(rs.getString("phone"));
				content.setYears(rs.getString("years"));
				content.setSex(rs.getString("sex"));
				content.setSumary(rs.getString("sumary"));
				content.setType(rs.getString("type"));
				content.setQname(rs.getString("qname"));
				content.setIcon(rs.getString("icon"));

			}
			return content;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return content;

	}
	

	public ArrayList<User> searchSheQu(int qid) {
		conn = this.getConnection();
		ArrayList<User> list = new ArrayList<User>();
		try {

			pstmt = conn.prepareStatement("select u.*,q.qname from users u left join qu q on q.qid=u.qid where u.qid="
					+ qid +" and type = 2");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User content = new User();
				content.setUid(rs.getInt("uid"));
				content.setQid(rs.getInt("qid"));
				content.setUser(rs.getString("user"));
				content.setPswd(rs.getString("pswd"));
				content.setName(rs.getString("name"));
				content.setPhone(rs.getString("phone"));
				content.setYears(rs.getString("years"));
				content.setSex(rs.getString("sex"));
				content.setSumary(rs.getString("sumary"));
				content.setType(rs.getString("type"));
				content.setQname(rs.getString("qname"));
				content.setIcon(rs.getString("icon"));
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
	 * 修改资料
	 * 
	 * @param news
	 */
	public boolean update_message(User c) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("update users set name=?,sex=?,phone=?,years=?,sumary=? where uid='"
							+ c.getUid() + "'");

			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getSex());
			pstmt.setString(3, c.getPhone());
			pstmt.setString(4, c.getYears());
			pstmt.setString(5, c.getSumary());

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
	 * 修改用户密码
	 * 
	 * @param news
	 */
	public boolean update_pswd(int uid, String pswd) {
		conn = this.getConnection();
		try {
			pstmt = conn.prepareStatement("update users set pswd=? where uid='"
					+ uid + "'");
			pstmt.setString(1, pswd);
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
	 * 获取医院资料
	 * 
	 * @return
	 */
	public ArrayList<Yiyuan> search_yy() {
		conn = this.getConnection();
		ArrayList<Yiyuan> list = null;

		try {
			list = new ArrayList<Yiyuan>();
			pstmt = conn.prepareStatement("select * from yiyuan");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yiyuan content = new Yiyuan();
				content.setYid(rs.getInt("yid"));

				content.setJianjie(rs.getString("jianjie"));

				content.setDizhi(rs.getString("dizhi"));

				content.setName(rs.getString("name"));

				content.setPhone(rs.getString("phone"));

				content.setIcon(rs.getString("icon"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取医院资料
	 * 
	 * @return
	 */
	public ArrayList<Yiyuan> search_yy(String gjz) {
		conn = this.getConnection();
		ArrayList<Yiyuan> list = null;
		// "select * from yao where name like '%"+ gjz + "%'"
		try {
			list = new ArrayList<Yiyuan>();
			pstmt = conn
					.prepareStatement("select * from yiyuan where name like '%"
							+ gjz + "%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yiyuan content = new Yiyuan();
				content.setYid(rs.getInt("yid"));

				content.setJianjie(rs.getString("jianjie"));

				content.setDizhi(rs.getString("dizhi"));

				content.setName(rs.getString("name"));

				content.setPhone(rs.getString("phone"));

				content.setIcon(rs.getString("icon"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取科室资料
	 * 
	 * @return
	 */
	public ArrayList<Keshi> search_ks(int yid) {
		conn = this.getConnection();
		ArrayList<Keshi> list = null;

		try {
			list = new ArrayList<Keshi>();
			pstmt = conn.prepareStatement("select * from keshi where yid="
					+ yid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Keshi content = new Keshi();
				content.setKid(rs.getInt("kid"));
				content.setYid(rs.getInt("yid"));
				content.setName(rs.getString("name"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取科室资料
	 * 
	 * @return
	 */
	public ArrayList<Keshi> search_ks(String gjz) {
		conn = this.getConnection();
		ArrayList<Keshi> list = null;

		try {
			list = new ArrayList<Keshi>();
			pstmt = conn
					.prepareStatement("select * from keshi  where name like '%"
							+ gjz + "%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Keshi content = new Keshi();
				content.setKid(rs.getInt("kid"));
				content.setYid(rs.getInt("yid"));
				content.setName(rs.getString("name"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取医生资料
	 * 
	 * @return
	 */
	public ArrayList<YiSheng> search_ys(int yid, int kid) {
		conn = this.getConnection();
		ArrayList<YiSheng> list = null;

		try {
			list = new ArrayList<YiSheng>();
			pstmt = conn.prepareStatement("select * from yisheng where yid="
					+ yid + " and kid=" + kid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				YiSheng content = new YiSheng();
				content.setYsid(rs.getInt("ysid"));
				content.setKid(rs.getInt("kid"));
				content.setYid(rs.getInt("yid"));
				content.setGua(rs.getInt("gua"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));
				content.setJianjie(rs.getString("jianjie"));
				content.setTechang(rs.getString("techang"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取医生资料
	 * 
	 * @return
	 */
	public ArrayList<YiSheng> search_ys(String gjz) {
		conn = this.getConnection();
		ArrayList<YiSheng> list = null;

		try {
			list = new ArrayList<YiSheng>();
			pstmt = conn
					.prepareStatement("select * from yisheng where name like '%"
							+ gjz + "%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				YiSheng content = new YiSheng();
				content.setYsid(rs.getInt("ysid"));
				content.setKid(rs.getInt("kid"));
				content.setYid(rs.getInt("yid"));
				content.setGua(rs.getInt("gua"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));
				content.setJianjie(rs.getString("jianjie"));
				content.setTechang(rs.getString("techang"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 是否挂号
	 * 
	 * @return
	 */
	public boolean search_gua(int ysid, int uid) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from guahao where ysid="
					+ ysid + " and uid=" + uid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return false;

	}

	/**
	 * 查询挂号时间
	 * 
	 * @return
	 */
	public String search_guaTime(int kid, int uid) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from guahao where kid="
					+ kid + " and uid=" + uid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("time");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return null;

	}

	/**
	 * 查询所有挂号时间
	 * 
	 * @return
	 */
	public ArrayList<String> search_guaTimes(int uid) {
		conn = this.getConnection();
		ArrayList<String> list = null;
		try {
			list = new ArrayList<String>();
			pstmt = conn.prepareStatement("select * from guahao where uid="
					+ uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String time = rs.getString("time");
				list.add(time);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 预约挂号
	 * 
	 * @param news
	 */
	public boolean Save(int uid, int ysid, String time, int kid,int statu) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into guahao(uid,ysid,time,kid,statu)values(?,?,?,?,?)");
			pstmt.setInt(1, uid);
			pstmt.setInt(2, ysid);
			pstmt.setString(3, time);
			pstmt.setInt(4, kid);
			pstmt.setInt(5, statu);
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
	 * 取消预约
	 * 
	 * @param news
	 */
	public boolean delete_gua(int uid, int ysid) {
		conn = this.getConnection();
		try {
			pstmt = conn.prepareStatement("delete from guahao where uid=" + uid
					+ " and ysid=" + ysid);
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
	 * 修改医生剩余挂号数
	 * 
	 * @param news
	 */
	public boolean update_gua(int ysid, int tsid) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("select gua from yisheng  where ysid='"
							+ ysid + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int guas = rs.getInt("gua");
				if (tsid == 1) {
					guas -= 1;
				} else {
					guas += 1;
				}

				pstmt = conn
						.prepareStatement("update yisheng set gua=? where ysid='"
								+ ysid + "'");
				pstmt.setInt(1, guas);
				pstmt.executeUpdate();
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			this.closeAll(null, pstmt, conn);
		}
		return false;
	}

	/**
	 * 获取医生资料
	 * 
	 * @return
	 */
	public ArrayList<YiSheng> search_ys(int uid) {
		conn = this.getConnection();
		ArrayList<YiSheng> list = null;

		try {
			list = new ArrayList<YiSheng>();
			pstmt = conn.prepareStatement("select * from guahao where uid="
					+ uid);
			rs = pstmt.executeQuery();
			ArrayList<Integer> ilist = new ArrayList<Integer>();
			ArrayList<Integer> ilist2 = new ArrayList<Integer>();
			ArrayList<String> tlist = new ArrayList<String>();
			while (rs.next()) {
				int ysid = rs.getInt("ysid");
				int statu = rs.getInt("statu");
				ilist.add(ysid);
				ilist2.add(statu);
				String time = rs.getString("time");
				tlist.add(time);
			}
			for (int i = 0; i < ilist.size(); i++) {
				pstmt = conn
						.prepareStatement("select * from yisheng where ysid="
								+ ilist.get(i));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int yid = rs.getInt("yid");
					int kid = rs.getInt("kid");
					YiSheng content = new YiSheng();
					content.setYsid(ilist.get(i));
					content.setStatu(ilist2.get(i));
					content.setKid(kid);
					content.setYid(yid);
					content.setGua(rs.getInt("gua"));
					content.setName(rs.getString("name"));
					content.setIcon(rs.getString("icon"));
					content.setJianjie(rs.getString("jianjie"));
					content.setTechang(rs.getString("techang"));
					content.setTime(tlist.get(i));

					list.add(content);
				}
			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取医院名字
	 * 
	 * @return
	 */
	public String search_yy_name(int yid) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from yiyuan where yid="
					+ yid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				return name;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return null;

	}

	/**
	 * 获取医院名字
	 * 
	 * @return
	 */
	public String search_ks_name(int kid) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from keshi where kid="
					+ kid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				return name;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return null;

	}

	/**
	 * 根据type获取药品
	 * 
	 * @return
	 */
	public ArrayList<Yao> search_yao(String type) {
		conn = this.getConnection();
		ArrayList<Yao> list = null;

		try {
			list = new ArrayList<Yao>();
			pstmt = conn.prepareStatement("select * from yao where type='"
					+ type + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yao content = new Yao();
				content.setYid(rs.getInt("yid"));
				content.setType(type);
				content.setMoney(rs.getDouble("money"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	public ArrayList<Yao> search_yao() {
		conn = this.getConnection();
		ArrayList<Yao> list = null;

		try {
			list = new ArrayList<Yao>();
			pstmt = conn.prepareStatement("select * from yao");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yao content = new Yao();
				content.setYid(rs.getInt("yid"));
				content.setType(rs.getString("type"));
				content.setMoney(rs.getDouble("money"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	public Yao search_yao(int yid) {
		conn = this.getConnection();

		try {

			pstmt = conn.prepareStatement("select * from yao where yid=" + yid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Yao content = new Yao();
				content.setYid(rs.getInt("yid"));
				content.setType(rs.getString("type"));
				content.setMoney(rs.getDouble("money"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));
				return content;

			}
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return null;

	}

	/**
	 * 根据关键字获取药品数据
	 * 
	 * @return
	 */
	public ArrayList<Yao> search_yao_gjz(String gjz) {
		conn = this.getConnection();
		ArrayList<Yao> list = null;

		try {
			list = new ArrayList<Yao>();
			pstmt = conn
					.prepareStatement("select * from yao where name like '%"
							+ gjz + "%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yao content = new Yao();
				content.setYid(rs.getInt("yid"));
				content.setType(rs.getString("type"));
				content.setMoney(rs.getDouble("money"));
				content.setName(rs.getString("name"));
				content.setIcon(rs.getString("icon"));

				list.add(content);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	

}

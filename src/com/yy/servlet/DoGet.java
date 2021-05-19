package com.yy.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.yy.dao.DaoImpl;
import com.yy.dao.QuDaoImpl;
import com.yy.dao.YiShengDaoImpl;
import com.yy.entity.Base64Coder;
import com.yy.entity.Get;
import com.yy.entity.Keshi;
import com.yy.entity.User;
import com.yy.entity.Yao;
import com.yy.entity.YiSheng;
import com.yy.entity.Yiyuan;

public class DoGet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		JSONObject array = new JSONObject();
		DaoImpl ndi = new DaoImpl();
		YiShengDaoImpl ydi = new YiShengDaoImpl();
		if (action.equals("save")) {// 保存注册用户
			String json = request.getParameter("json");

			User s = new User();
			s = (User) new Gson().fromJson(json, User.class);

			if (!ndi.isCreate(s.getUser())) {
				if (ndi.Save(s)) {
					array.put("code", "success");
					array.put("msg", "注册成功");
				} else {
					array.put("code", "failure");
					array.put("msg", "注册失败");
				}
			} else {
				array.put("code", "failure");
				array.put("msg", "该用户名已注册");
			}

		} else if (action.equals("login")) {// 登录
			String user = request.getParameter("user");
			String pswd = request.getParameter("pswd");
			if (ndi.login(user, pswd) != null) {
				array.put("code", "success");
				array.put("msg", "登录成功");
				array.put("data", ndi.login(user, pswd));
			} else {
				array.put("code", "failure");
				array.put("msg", "用户名或密码输入有误");
			}
		} else if (action.equals("loginYS")) {// 登录
			String user = request.getParameter("user");
			String pswd = request.getParameter("pswd");
			if (ydi.loginYS(user, pswd) != null) {
				array.put("code", "success");
				array.put("msg", "登录成功");
				array.put("data", ydi.loginYS(user, pswd));
			} else {
				array.put("code", "failure");
				array.put("msg", "用户名或密码输入有误");
			}
		}

		else if (action.equals("update_message")) {

			String json = request.getParameter("json");

			User s = new User();
			s = (User) new Gson().fromJson(json, User.class);
			System.out.println(s.getUser());

			if (ndi.update_message(s)) {
				array.put("code", "success");
				array.put("msg", "修改成功");
			} else {
				array.put("code", "failure");
				array.put("msg", "修改失败");
			}

		} else if (action.equals("up_icon")) {
			String icon = request.getParameter("filename");
			String uid = request.getParameter("uid");
			if (uid != null) {
				ndi.update_icon(Integer.parseInt(uid), icon);
			}
			byte[] b = Base64Coder.decodeLines(request.getParameter("file"));
			String filepath = "c:/yiyuan/";
			File file = new File(filepath);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file.getPath() + "/"
					+ icon);

			fos.write(b);
			fos.flush();
			fos.close();
			array.put("msg", "图片上传成功");

		} 

		else if (action.equals("update_password")) {
			String suid = request.getParameter("uid");
			int uid = Integer.parseInt(suid);
			String pswd = request.getParameter("pswd");
			if (ndi.update_pswd(uid, pswd)) {
				array.put("code", "success");
				array.put("msg", "密码修改成功");
			} else {
				array.put("code", "failure");
				array.put("msg", "密码修改失败");
			}

		}else if (action.equals("search_yy")) {
			ArrayList<Yiyuan> list = ndi.search_yy();
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "医院资料获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "医院资料获取失败");
			}
		} else if (action.equals("search_shequ")) {// 查询社区人员列表
			ArrayList<User> list = ndi.searchSheQu(Integer.parseInt(request.getParameter("qid")));
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("data", list);
			} else {
				array.put("code", "failure");
			}
		} else if (action.equals("search_ks")) {
			String suid = request.getParameter("yid");

			int yid = Integer.parseInt(suid);
			ArrayList<Keshi> list = ndi.search_ks(yid);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "科室资料获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "科室资料获取失败");
			}
		} else if (action.equals("search_ys")) {
			String suid = request.getParameter("yid");
			int yid = Integer.parseInt(suid);
			String kuid = request.getParameter("kid");
			int kid = Integer.parseInt(kuid);
			String uuid = request.getParameter("uid");
			int uid = Integer.parseInt(uuid);
			ArrayList<YiSheng> list = ndi.search_ys(yid, kid);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int ysid = list.get(i).getYsid();
					if (ndi.search_gua(ysid, uid)) {
						list.get(i).setIsgua(1);
					} else {
						list.get(i).setIsgua(0);
					}
				}
				array.put("code", "success");
				array.put("msg", "医生资料获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "医生资料获取失败");
			}
		} else if (action.equals("save_gua")) {// 预约挂号
			String suid = request.getParameter("ysid");
			int ysid = Integer.parseInt(suid);
			int statu = Integer.parseInt(request.getParameter("statu"));
			String uuid = request.getParameter("uid");
			int uid = Integer.parseInt(uuid);
			String ukid = request.getParameter("kid");
			int kid = Integer.parseInt(ukid);
			String time = request.getParameter("time");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date nowDate = null;
			long lnowTime = 0;
			try {
				nowDate = sdf.parse(time);
				lnowTime = nowDate.getTime();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<String> list = ndi.search_guaTimes(uid);
			boolean bool = false;
			for (int i = 0; i < list.size(); i++) {
				try {
					Date lateDate = sdf.parse(list.get(i));
					long llateTime = lateDate.getTime();
					long dTime = Math.abs((llateTime - lnowTime));
					if (dTime < 3600000) {
						bool = true;
						break;

					} else {
						bool = false;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bool) {// 说明有小于一个小时的
				array.put("code", "failure");
				array.put("msg", "预约失败,预约间隔不得小于1个小时");
			} else {
				String str = ndi.search_guaTime(kid, uid);

				if (str == null) {
					if (ndi.Save(uid, ysid, time, kid,statu)) {
						array.put("code", "success");
						array.put("msg", "预约成功");
					} else {
						array.put("code", "failure");
						array.put("msg", "预约失败");
					}
				} else {

					try {

						Date lateDate = sdf.parse(str);
						if (isSameDate(nowDate, lateDate)) {
							array.put("code", "failure");
							array.put("msg", "预约失败,同一天一个科室只能挂一个号");
						} else {
							if (ndi.Save(uid, ysid, time, kid,statu)) {
								array.put("code", "success");
								array.put("msg", "预约成功");
							} else {
								array.put("code", "failure");
								array.put("msg", "预约失败");
							}
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} else if (action.equals("delete_gua")) {// 取消挂号
			String suid = request.getParameter("ysid");
			int ysid = Integer.parseInt(suid);
			String uuid = request.getParameter("uid");
			int uid = Integer.parseInt(uuid);
			if (ndi.delete_gua(uid, ysid)) {
				array.put("code", "success");
				array.put("msg", "预约挂号取消成功");
			} else {
				array.put("code", "failure");
				array.put("msg", "预约挂号取消失败");
			}
		} else if (action.equals("update_gua")) {// 修改挂号数量
			String suid = request.getParameter("ysid");
			int ysid = Integer.parseInt(suid);
			String stsid = request.getParameter("tsid");
			int tsid = Integer.parseInt(stsid); // 1减 0加
			if (ndi.update_gua(ysid, tsid)) {
				array.put("code", "success");
				array.put("msg", "预约挂号取消成功");
			} else {
				array.put("code", "failure");
				array.put("msg", "预约挂号取消失败");
			}
		} else if (action.equals("search_gua")) {// 查询挂号
			String suid = request.getParameter("uid");
			int uid = Integer.parseInt(suid);
			ArrayList<YiSheng> list = ndi.search_ys(uid);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int yid = list.get(i).getYid();
					int kid = list.get(i).getKid();
					int ysid = list.get(i).getYsid();
					if (ndi.search_gua(ysid, uid)) {
						list.get(i).setIsgua(1);
					} else {
						list.get(i).setIsgua(0);
					}
					String yname = ndi.search_yy_name(yid);
					String kname = ndi.search_ks_name(kid);
					list.get(i).setYname(yname);
					list.get(i).setKname(kname);
				}
				array.put("code", "success");
				array.put("msg", "挂号信息查询成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "暂无挂号信息");
			}
		} else if (action.equals("search_yao_type")) {// 根据type获取药品
			String type = request.getParameter("type");
			ArrayList<Yao> list = ndi.search_yao(type);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		} else if (action.equals("search_yao")) {// 获取药品
			ArrayList<Yao> list = ndi.search_yao();
			ArrayList<Yao> lists = new ArrayList<Yao>();
			if (list.size() > 0) {
				System.out.println(list.size() - 1 + "");
				for (int i = 0; i < 4; i++) {
					Random random = new Random();
					int r = random.nextInt(list.size() - 1);
					lists.add(list.get(r));
					list.remove(list.get(r));
				}

				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", lists);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		} else if (action.equals("search_yao_gjz")) {// 根据type获取药品
			String content = request.getParameter("content");
			ArrayList<Yao> list = ndi.search_yao_gjz(content);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		} else if (action.equals("SaveSheQuYuYue")) {// 保存预约 社区人员信息

			int uid = Integer.parseInt(request.getParameter("uid"));
			int suid = Integer.parseInt(request.getParameter("suid"));
			ydi.SaveSheQuYuYue(uid, suid, request.getParameter("content"), request.getParameter("time"));
			array.put("code", "success");
			
		} else if (action.equals("search_yy_gjz")) {// 根据关键字查询医院
			String content = request.getParameter("content");
			ArrayList<Yiyuan> list = ndi.search_yy(content);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		} else if (action.equals("search_ks_gjz")) {// 根据关键字查询科室
			String content = request.getParameter("content");
			ArrayList<Keshi> list = ndi.search_ks(content);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		} else if (action.equals("search_ys_gjz")) {// 根据关键字查询医生
			String content = request.getParameter("content");
			ArrayList<YiSheng> list = ndi.search_ys(content);
			if (list.size() > 0) {
				array.put("code", "success");
				array.put("msg", "药品获取成功");
				array.put("data", list);
			} else {
				array.put("code", "failure");
				array.put("msg", "药品获取失败");
			}
		}
		else if(action.equals("searchQu")){
			array.put("code", "success");
			array.put("data", new QuDaoImpl().search());
		}
		
		else if(action.equals("YiShengSearchYuYue")){
			JSONArray arr= ydi.search(Integer.parseInt(request.getParameter("ysid")));
			if(arr.size()==0){
				array.put("code", "fail");
			}else{
				array.put("code", "success");
				array.put("data", arr);
			}
			
		}
		else if(action.equals("UserSearchSQYuYue")){
			JSONArray arr= ydi.UserSearchSQYuYue(Integer.parseInt(request.getParameter("uid")));
			if(arr.size()==0){
				array.put("code", "fail");
			}else{
				array.put("code", "success");
				array.put("data", arr);
			}
			
		}
		
		else if(action.equals("UserSearchYuYue")){
			JSONArray arr= ydi.UserSearchYuYue(Integer.parseInt(request.getParameter("uid")));
			if(arr.size()==0){
				array.put("code", "fail");
			}else{
				array.put("code", "success");
				array.put("data", arr);
			}
			
		}
		else if(action.equals("SheQuSearchYuYue")){
			JSONArray arr= ydi.SheQuSearchYuYue(Integer.parseInt(request.getParameter("uid")));
			if(arr.size()==0){
				array.put("code", "fail");
			}else{
				array.put("code", "success");
				array.put("data", arr);
			}
			
		}
		else if(action.equals("add_baogao")){
			int gid = Integer.parseInt(request.getParameter("gid"));
			int type = Integer.parseInt(request.getParameter("type"));
			String content = request.getParameter("content");
			ydi.addBaoGao(gid, type, content);
			array.put("code", "success");
		}else if(action.equals("search_baogao")){
			int gid = Integer.parseInt(request.getParameter("gid"));
			int type = Integer.parseInt(request.getParameter("type"));
			JSONArray arr= ydi.searchBaoGao(gid,type);
			if(arr.size()==0){
				array.put("code", "fail");
			}else{
				array.put("code", "success");
				array.put("data", arr);
			}
		}
		else if(action.equals("del_baogao")){
			int bid = Integer.parseInt(request.getParameter("bid"));
			ydi.deleteBaoGao(bid);
			array.put("code", "success");
		}
		
		else if(action.equals("update_guahao")){
			int gid = Integer.parseInt(request.getParameter("gid"));
			int statu = Integer.parseInt(request.getParameter("statu"));
			if(statu==4){
				String ping = request.getParameter("ping");
				ydi.update_guahao(gid, ping);
			}else{
				ydi.update_guahao(gid, statu);
			}
			array.put("code", "success");
		}
		else if(action.equals("update_sqyy")){
			int id = Integer.parseInt(request.getParameter("sid"));
			int statu = Integer.parseInt(request.getParameter("statu"));
			if(statu==4){
				String ping = request.getParameter("ping");
				ydi.update_sqyy(id, ping);
			}else{
				ydi.update_sqyy(id, statu);
			}
			array.put("code", "success");
		}
		
		else if (action.equals("up_ys_icon")) {
			String icon = request.getParameter("filename");
			String id = request.getParameter("ysid");
			if (id != null) {
				ydi.update_icon(Integer.parseInt(id), icon);
			}
			byte[] b = Base64Coder.decodeLines(request.getParameter("file"));
			String filepath = request.getRealPath("/img");
			File file = new File(filepath);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file.getPath() + "/"
					+ icon);

			fos.write(b);
			fos.flush();
			fos.close();
			array.put("msg", "图片上传成功");

		} else if (action.equals("update_ys_message")) {

			String json = request.getParameter("json");

			YiSheng s = new YiSheng();
			s = (YiSheng) new Gson().fromJson(json, YiSheng.class);
			System.out.println(s.getUser());

			if (ydi.update_message(s)) {
				array.put("code", "success");
				array.put("msg", "修改成功");
			} else {
				array.put("code", "failure");
				array.put("msg", "修改失败");
			}

		} 
		System.out.println(array.toString());
		out.print(array);
		out.flush();
		out.close();
	}

	/**
	 * 判断两个时间是否是同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
						.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
}

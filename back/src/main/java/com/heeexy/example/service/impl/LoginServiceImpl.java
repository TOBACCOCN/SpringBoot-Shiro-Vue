package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.LoginDao;
import com.heeexy.example.service.LoginService;
import com.heeexy.example.service.PermissionService;
import com.heeexy.example.service.UserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.MD5Util;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.constants.ErrorEnum;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zyh
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		JSONObject jsonObj = userService.selectByUserName(username);
		if (jsonObj == null) {
			return CommonUtil.errorJson(ErrorEnum.E_401);
		}
		String password = MD5Util.encode((jsonObject.getString("password") + jsonObj.getString("salt")).getBytes());
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(token);
			info.put("result", "success");
		} catch (AuthenticationException e) {
			info.put("result", "fail");
		}
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Override
	public JSONObject getUser(String username, String password) {
		return loginDao.getUser(username, password);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getString("username");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permissionService.getUserPermission(username);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		info.put("userPermission", userPermission);
		return CommonUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return CommonUtil.successJson();
	}
}

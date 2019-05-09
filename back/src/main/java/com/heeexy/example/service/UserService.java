package com.heeexy.example.service;

import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: zyh
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
public interface UserService {
	/**
	 * 用户列表
	 */
	JSONObject listUser(JSONObject jsonObject);

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	JSONObject getAllRoles();

	/**
	 * 添加用户
	 * @throws NoSuchAlgorithmException 
	 */
	JSONObject addUser(JSONObject jsonObject);

	/**
	 * 修改用户
	 */
	JSONObject updateUser(JSONObject jsonObject);
	
	/**
	 * 删除用户
	 */
	JSONObject deleteUser(JSONObject jsonObject);

	/**
	 * 角色列表
	 */
	JSONObject listRole(JSONObject jsonObject);

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	JSONObject listAllPermission();

	/**
	 * 添加角色
	 */
	JSONObject addRole(JSONObject jsonObject);

	/**
	 * 修改角色
	 */
	JSONObject updateRole(JSONObject jsonObject);

	/**
	 * 删除角色
	 */
	JSONObject deleteRole(JSONObject jsonObject);
	
	/**
	 * 权限列表
	 */
	JSONObject listPermission(JSONObject jsonObject);
	
	/**
	 * 添加权限
	 */
	JSONObject addPermission(JSONObject jsonObject);
	
	/**
	 * 修改权限
	 */
	JSONObject updatePermission(JSONObject jsonObject);
	
	/**
	 * 删除权限
	 */
	JSONObject deletePermission(JSONObject jsonObject);

	JSONObject selectByUserName(String username);
}

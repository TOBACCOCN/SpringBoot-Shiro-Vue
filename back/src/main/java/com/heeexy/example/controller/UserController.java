package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zyh
 * @description: 用户/角色/权限相关controller
 * @date: 2017/11/2 10:19
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 用户列表
	 */
	@RequiresPermissions("user:list")
	@GetMapping("/list")
	public JSONObject listUser(HttpServletRequest request) {
		return userService.listUser(CommonUtil.request2Json(request));
	}

	/**
	 * 新增用户
	 * @throws NoSuchAlgorithmException 
	 */
	@RequiresPermissions("user:add")
	@PostMapping("/addUser")
	public JSONObject addUser(@RequestBody JSONObject requestJson) throws NoSuchAlgorithmException {
		CommonUtil.hasAllRequired(requestJson, "username, password, nickname, roleId");
		return userService.addUser(requestJson);
	}

	/**
	 * 更新用户
	 */
	@RequiresPermissions("user:update")
	@PostMapping("/updateUser")
	public JSONObject updateUser(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "nickname, roleId, userId");
		return userService.updateUser(requestJson);
	}
	
	/**
	 * 更新用户
	 */
	@RequiresPermissions("user:delete")
	@PostMapping("/deleteUser")
	public JSONObject deleteUser(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "userId");
		return userService.deleteUser(requestJson);
	}

	@RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
	@GetMapping("/getAllRoles")
	public JSONObject getAllRoles() {
		return userService.getAllRoles();
	}

	/**
	 * 角色列表
	 */
	@RequiresPermissions("role:list")
	@GetMapping("/listRole")
	public JSONObject listRole(HttpServletRequest request) {
		return userService.listRole(CommonUtil.request2Json(request));
	}

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	@RequiresPermissions("role:list")
	@GetMapping("/listAllPermission")
	public JSONObject listAllPermission() {
		return userService.listAllPermission();
	}

	/**
	 * 新增角色
	 */
	@RequiresPermissions("role:add")
	@PostMapping("/addRole")
	public JSONObject addRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleName, permissions");
		return userService.addRole(requestJson);
	}

	/**
	 * 修改角色
	 */
	@RequiresPermissions("role:update")
	@PostMapping("/updateRole")
	public JSONObject updateRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleId, roleName, permissions");
		return userService.updateRole(requestJson);
	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("role:delete")
	@PostMapping("/deleteRole")
	public JSONObject deleteRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleId");
		return userService.deleteRole(requestJson);
	}
	
	/**
	 * 权限列表
	 */
	@RequiresPermissions("permission:list")
	@GetMapping("/listPermission")
	public JSONObject listPermission(HttpServletRequest request) {
		return userService.listPermission(CommonUtil.request2Json(request));
	}
	
	/**
	 * 新增权限
	 */
	@RequiresPermissions("permission:add")
	@PostMapping("/addPermission")
	public JSONObject addPermission(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "menuCode, menuName, permissionCode, permissionName, requiredPermission");
		return userService.addPermission(requestJson);
	}
	
	/**
	 * 修改权限
	 */
	@RequiresPermissions("permission:update")
	@PostMapping("/updatePermission")
	public JSONObject updatePermission(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "menuCode, menuName, permissionCode, permissionName, requiredPermission, permissionId");
		return userService.updatePermission(requestJson);
	}
	
	/**
	 * 删除权限
	 */
	@RequiresPermissions("permission:delete")
	@PostMapping("/deletePermission")
	public JSONObject deletePermission(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "permissionId");
		return userService.deletePermission(requestJson);
	}
}

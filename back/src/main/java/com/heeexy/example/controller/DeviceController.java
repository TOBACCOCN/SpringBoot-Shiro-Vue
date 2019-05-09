package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.DeviceService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zyh
 * @description: 设备相关Controller
 * @date: 2017/10/24 16:04
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	/**
	 * 查询设备列表
	 */
	@RequiresPermissions("device:list")
	@GetMapping("/listDevice")
	public JSONObject listDevice(HttpServletRequest request) {
		return deviceService.listDevice(CommonUtil.request2Json(request));
	}

	/**
	 * 新增设备
	 */
//	@RequiresPermissions("device:add")
	@PostMapping("/addDevice")
	public JSONObject addDevice(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "sn");
		return deviceService.addDevice(requestJson);
	}

	/**
	 * 修改设备
	 */
	@RequiresPermissions("device:update")
	@PostMapping("/updateDevice")
	public JSONObject updateDevice(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "id,sn");
		return deviceService.updateDevice(requestJson);
	}
	
	/**
	 * 删除设备
	 */
	@RequiresPermissions("device:delete")
	@PostMapping("/deleteDevice")
	public JSONObject deleteDevice(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "id");
		return deviceService.removeDevice(requestJson);
	}
}

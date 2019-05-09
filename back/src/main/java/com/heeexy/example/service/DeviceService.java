package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: zyh
 * @date: 2017/10/24 16:06
 */
public interface DeviceService {
	/**
	 * 新增设备
	 */
	JSONObject addDevice(JSONObject jsonObject);

	/**
	 * 设备列表
	 */
	JSONObject listDevice(JSONObject jsonObject);

	/**
	 * 更新设备
	 */
	JSONObject updateDevice(JSONObject jsonObject);
	
	/**
	 * 删除设备
	 */
	JSONObject removeDevice(JSONObject jsonObject);
}

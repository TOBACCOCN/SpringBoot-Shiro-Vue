package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: zyh
 * @description: 设备Dao层
 * @date: 2017/10/24 16:06
 */
public interface DeviceDao {
	/**
	 * 新增设备
	 */
	int addDevice(JSONObject jsonObject);

	/**
	 * 统计设备总数
	 */
	int countDevice(JSONObject jsonObject);

	/**
	 * 设备列表
	 */
	List<JSONObject> listDevice(JSONObject jsonObject);

	/**
	 * 更新设备
	 */
	int updateDevice(JSONObject jsonObject);
	
	/**
	 * 删除设备
	 */
	int removeDevice(JSONObject jsonObject);
}

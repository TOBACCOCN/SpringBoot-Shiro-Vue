package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DeviceDao;
import com.heeexy.example.service.DeviceService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zyh
 * @date: 2017/10/24 16:07
 */
@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	/**
	 * 新增设备
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addDevice(JSONObject jsonObject) {
		deviceDao.addDevice(jsonObject);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msg", "http://0e1bb0a7.ngrok.io/TeamViewerHost.apk");
		return CommonUtil.successJson(jsonObj);
	}

	/**
	 * 设备列表
	 */
	@Override
	public JSONObject listDevice(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);
		int count = deviceDao.countDevice(jsonObject);
		List<JSONObject> list = deviceDao.listDevice(jsonObject);
		return CommonUtil.successPage(jsonObject, list, count);
	}

	/**
	 * 更新设备
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateDevice(JSONObject jsonObject) {
		deviceDao.updateDevice(jsonObject);
		return CommonUtil.successJson();
	}
	
	/**
	 * 删除设备
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject removeDevice(JSONObject jsonObject) {
		deviceDao.removeDevice(jsonObject);
		return CommonUtil.successJson();
	}
}

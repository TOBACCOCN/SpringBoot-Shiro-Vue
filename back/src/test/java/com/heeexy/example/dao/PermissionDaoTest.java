package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.MD5Util;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PermissionDaoTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(PermissionDaoTest.class);

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void test() {
        JSONObject user = permissionDao.getUserPermission("user");
        logger.info(">>>>> user: [{}]", user);
    }

}
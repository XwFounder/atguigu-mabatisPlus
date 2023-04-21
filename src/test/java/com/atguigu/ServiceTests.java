package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class ServiceTests {

    @Resource //按名称装配，找不到与名称匹配的bean，则按照类型装配
    private UserMapper userMapper;

}

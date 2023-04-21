package com.atguigu.service;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

//业务逻辑层
public interface UserService extends IService<User> {
    List<User> selectAllByName(String name);
}

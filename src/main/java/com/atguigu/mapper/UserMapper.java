package com.atguigu.mapper;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllByName(String name);


//    测试逻辑删除
//    logDeleteByid()
    /**
     * 查询 : 根据年龄查询用户列表，分页显示
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位
     * @param age       年龄
     * @return 分页对象
     */
    IPage<User> selectPageByPage(Page<?> page, Integer age);
}

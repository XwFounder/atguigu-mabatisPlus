package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 22180
 * @description TODO
 * @date 2023/4/20 20:47
 */
@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;
    @Test
    public void testSelectPage(){
        //创建分页参数
        Page<User> pageParam = new Page<>(1,3);
        //执行分页查询
        userMapper.selectPage(pageParam, null);
        //查看分页参数的成员
        System.out.println(pageParam);
    }
    @Test
    public void testSelectPageVo(){
        Page<User> pageParam = new Page<>(1,5);
        userMapper.selectPageByPage(pageParam, 18);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);
    }

}

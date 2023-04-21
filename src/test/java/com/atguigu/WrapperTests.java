package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 22180
 * @description TODO
 * @date 2023/4/21 16:00
 */
@SpringBootTest
public class WrapperTests {
    @Resource
    private UserMapper userMapper;

    /**
     *
     * @description QueryWrapper
     * @author 22180
     * @date 2023/4/21 16:01
     */
    @Test
    public void test1() {
//        组装查询条件,查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
        QueryWrapper<User> queryWrap = new QueryWrapper<>();
        queryWrap.like("name","n")
                .between("age",10,20)
                .isNotNull("email");
        List<User> user  = userMapper.selectList(queryWrap);
        user.forEach(System.out ::println);
    }
    /**
    * 组装排序条件
     * 按年龄降序查询用户，如果年龄相同则按id升序排列
    */
    @Test
    public void test2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    /**
    * 动态组装查询条件,查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
    */
    @Test
    public void test8() {

        //定义查询条件，有可能为null（用户未输入）
        String name = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name","n");
        }
        if(ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    /**
    *组装删除条件,删除email为空的用户
    */
    @Test
    public void test3() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper); //条件构造器也可以构建删除语句的条件
        System.out.println("delete return count = " + result);
    }
    /**
    * LambdaXxxWrapper
    */
    @Test
    public void test9() {

        //定义查询条件，有可能为null（用户未输入）
        String name = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                //避免使用字符串表示字段，防止运行时错误
                .like(StringUtils.isNotBlank(name), User::getName, "n")
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    /**
    * 实现子查询,查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     UpdateWrapper
     */
    @Test
    public void test7() {
        //组装set子句
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age",18)
                .set("email","user@atguigu.com")
                .like("name","n")
                .and(i -> i.lt("age", 18).or().isNull("email"));
        //lambda表达式内的逻辑优先运算
        //这里必须要创建User对象，否则无法应用自动填充。如果没有自动填充，可以设置为null
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result);

    }

}

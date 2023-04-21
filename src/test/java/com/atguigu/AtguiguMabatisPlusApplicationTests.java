package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class AtguiguMabatisPlusApplicationTests {
    //@Autowired //按类型装配。是spring的注解
    @Resource //按名称装配，找不到与名称匹配的bean，则按照类型装配
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }
    @Test
    void testSelectList() {
        //selectList()方法的参数：封装了查询条件
        //null：无任何查询条件
        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("test");
        user.setAge(18);
        user.setEmail("goole@gmeail.com");

        //不设置email属性，则生成的动态sql中不包括email字段
        int result = userMapper.insert(user);
//        System.out.println("影响的行数：" + result); //影响的行数
//        System.out.println("id：" + user.getId()); //id自动回填
    }
    @Test
    public void testSelect(){

        //按id查询
        User user = userMapper.selectById(1);

        //按id列表查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

        //按条件查询
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Helen"); //注意此处是表中的列名，不是类中的属性名
        map.put("age", 18);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);
    }
    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(1L);
        user.setAge(28);

        //注意：update时生成的sql自动是动态sql
        int result = userMapper.updateById(user);
        System.out.println("影响的行数：" + result);
    }
    @Test
    public void testDelete(){
        int result = userMapper.deleteById(5);
        System.out.println("影响的行数：" + result);
    }
    @Test
    public void testSaveBatch(){

        // SQL长度有限制，海量数据插入单条SQL无法实行，
        // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("Helen" + i);
            user.setAge(10 + i);
            users.add(user);
        }
        System.out.println(users.toString());
//        userService.saveBatch(users);
    }
    @Test
    public void testSelectAllByName(){
        List<User> users = userMapper.selectAllByName("Helen");
        users.forEach(System.out::println);
    }
}

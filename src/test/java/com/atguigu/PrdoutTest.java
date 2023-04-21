package com.atguigu;

import com.atguigu.mapper.ProductMapper;
import com.atguigu.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 22180
 * @description TODO
 * @date 2023/4/21 15:41
 */
@SpringBootTest

public class PrdoutTest {
    @Autowired(required = false)
    private ProductMapper productMapper;
    @Test
    public void testConcurrentUpdate() {

        //1、小李
        Product p1 = productMapper.selectById(1L);

        //2、小王
        Product p2 = productMapper.selectById(1L);

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
    // 如果小王修改失败的话，可以进行重试
        if (result2 == 0 ) {
            p2 = productMapper.selectById(1l);
            p2.setPrice(p2.getPrice()-30);
            productMapper.updateById(p2);
        }
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }
}

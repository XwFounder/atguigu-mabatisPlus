package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "user")
public class User {
    @TableId(type = IdType.ASSIGN_ID,value = "id") // 雪花算法
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(value = "create_time",fill = FieldFill.INSERT) // file 标记为填充字段
    private LocalDateTime createTime;
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField(value = "isdlete",exist = true)
    private Integer deleted; // 0 false 没删除  1 true 已删除
}

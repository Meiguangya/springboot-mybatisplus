package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author meiguangya
 */
@Data
public class User {
    @TableId(type = IdType.UUID)
    private String id;
    private String name;
    private Integer age;
    private String email;
    @TableField(exist = false)
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    private Integer version;

    @TableLogic
    @TableField(select = false)
    private Integer deleted;

    public User() {
    }

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}

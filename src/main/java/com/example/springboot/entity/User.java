package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
}

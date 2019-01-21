package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@Data
@TableName(value = "student")
public class Student {
    @TableId("student_id")
    Long studentId;
    @TableId("student_name")
    String studentName;

}

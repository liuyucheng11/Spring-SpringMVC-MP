package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

}

package com.studentAdmin.controller;

import com.studentAdmin.dao.mapper.StudentMapper;
import com.studentAdmin.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@Controller
public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    @RequestMapping("/insertStudent.do")
    public void insertStudent(){
        Student a = new Student();
        a.setStudentId(10001L);
        a.setStudentName("a");
        studentMapper.insert(a);

    }
}

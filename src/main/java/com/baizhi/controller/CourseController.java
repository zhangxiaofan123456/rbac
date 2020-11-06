package com.baizhi.controller;

import com.baizhi.annotation.NeedRole;
import com.baizhi.constant.GlobalConstant;
import com.baizhi.constant.PermissionConstant;
import com.baizhi.entity.Course;
import com.baizhi.entity.Response;
import com.baizhi.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;




    @GetMapping("/getAll")
    @NeedRole(PermissionConstant.QUERY)
    public Response getAll_ADMIN(){
        List<Course> allCourses=iCourseService.getAll();
        if (allCourses==null&&allCourses.size()<0){
            return Response.operationResultIsEmpty("数据为空",null);
        }else {
            return Response.success("查询数据成功",allCourses);
        }
    }



    @PostMapping("/managerInsert")
    @NeedRole(PermissionConstant.MANAGER_INSERT)
    public Response managerInsert_ADMIN(@RequestBody @Valid Course course, BindingResult bindingResult){
        List<Course> courses = iCourseService.getAll();
        for (int i = 0; i < courses.size(); i++) {
            Course cours= courses.get(i);
            if (cours.equals(course)){
                return new Response(400,"主任增加课程与现有课程重复",null);
            }
        }
        course.setStatus(GlobalConstant.StatusConstant.COURSE_REVIEW_COMPLETED);
        iCourseService.insert(course);
        return Response.success("主任增加课程成功",null);

    }

    @PostMapping("/teacherInsert")
    @NeedRole(PermissionConstant.TEACHER_INSERT)
    public Response teacherInsert_ADMIN(@RequestBody @Valid Course course, BindingResult bindingResult){
        List<Course> courses = iCourseService.getAll();
        for (int i = 0; i < courses.size(); i++) {
            Course cours= courses.get(i);
            if (cours.equals(course)){
                return new Response(400,"老师增加课程与现有课程重复",null);
            }
            if (i==courses.size()-1){
                course.setStatus(GlobalConstant.StatusConstant.COURSE_UNDER_REVIEW);
                course.setOperation(GlobalConstant.OperationConstant.OPERATION_C);
                iCourseService.insert(course);
                return new Response(200,"老师增加课程成功,等待审批",null);
            }
        }
        return new Response(200,"过程结束",null);
    }


}

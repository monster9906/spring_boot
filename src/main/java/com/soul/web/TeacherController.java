package com.soul.web;

import com.soul.domain.Teacher;
import com.soul.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 符浩灵
 * @date 2019/9/2 10:19
 */
@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 获取全部教师信息
     * @return
     */
    @GetMapping("/teachers")
    public List<Teacher> getAll(){
        return teacherService.findAll();
    }

    /**
     * 分页查询讲师信息
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/query")
    public ResponseEntity<?> findByPage(Integer page, Integer pageSize){
        Map map = new HashMap();
        if(page==null||page<=0){
            page=0;
        }else {
            page -=1;
        }
        Page<Teacher> teachers = teacherService.findByPage(page, pageSize);
        map.put("total",teachers.getTotalElements());//返回列表总条数
        map.put("rows",teachers.getContent());//返回列表数据
        return ResponseEntity.ok(map);
    }

    /**
     * 分页查询2
     */
    @PostMapping("/query2")
    public Page<Teacher> findByPage2(Integer page, Integer pageSize){
        if(page==null||page<=0){
            page=0;
        }else {
            page -=1;
        }
        teacherService.findByPage(page, pageSize);
        return teacherService.findByPage(   page, pageSize);
    }

    /**
     * 新增教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/teachers")
    public Map<String,Object> addTeacher(Teacher teacher){
        Map<String,Object> map = new HashMap();
        if(teacherService.add(teacher) != null){
            map.put("code",200); // 添加成功
            map.put("teacher",teacherService.add(teacher));
        }else{
            map.put("code",500); // 添加失败
        }
        return map;
    }

    /**
     * 删除单个教师信息
     * @param id
     */
    @DeleteMapping("/teachers/{id}")
    public Map<String,Object> del(@PathVariable Long id){
        Map<String,Object> map = new HashMap<>();
        if(teacherService.removeTeacher(id)){
            map.put("statu", "200");
            map.put("msg", "ok");
        }else{
            map.put("statu", "500");
            map.put("msg", "no");
        }
        return map;
    }

    /**
     * 修改某条教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/teachers/update")
    public Teacher update(Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

}

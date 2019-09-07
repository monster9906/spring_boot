package com.soul.service;

import com.soul.domain.Teacher;
import com.soul.domain.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/9/2 10:51
 */
@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    /**
     * 查询所有教师信息
     */
    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    /**
     * 新增教师信息
     */
    public Teacher add(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * 删除教师信息
     */
    public boolean removeTeacher(Long id){
        if(teacherRepository.existsById(id)){
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
    /**
     * 修改教师信息
     */
    public Teacher updateTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public Page<Teacher> findByPage(Integer page, int pageSize) {
        Pageable pageable = new PageRequest(page,pageSize);
        return teacherRepository.findAll(pageable);
    }
}

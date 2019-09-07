package com.soul.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 符浩灵
 * @date 2019/9/2 20:28
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/teacher")
    public String upload() {
        return "teacher/teacherManger";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("lifePhoto") MultipartFile file) {
        Map map = new HashMap();
        if (file.isEmpty()) {
            map.put("error","上传失败，请选择文件");
            return map;
        }

        String fileName = file.getOriginalFilename();
        String filePath = "F:\\develop\\idea_space\\spring_boot_demo\\src\\main\\resources\\static\\image\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            map.put("url","image\\"+fileName);
            return map;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        map.put("error","上传失败");
        return map;
    }
}

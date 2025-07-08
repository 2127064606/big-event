package org.example.bigevent.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadOssController {

    @Autowired
    private UserService userService;
    @PostMapping
    public Result upload(@RequestBody MultipartFile file){
        log.info("file:{}",file);
        String userPic = userService.addUserPic(file);
        return Result.success(userPic);
    }
}

package com.jacken.springbootuploadfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class UpLoadController {

    @RequestMapping("/index")
    public  String toupLoad(){
        return "upload";
    }
    @RequestMapping(value = "/upload",method =  RequestMethod.POST )
    @ResponseBody
    public  String uploadFile(MultipartFile file, HttpServletRequest request){
        try {
            //创建文件存放的路径
            String realPath = request.getServletContext().getRealPath("/upload");
            File fileDir = new File(realPath);
            if(!fileDir.exists()){
                fileDir.mkdirs();
                //生成服务器端文件生成的名字 文件的后缀
                String fileSuffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                //文件的前缀
                String fileName= UUID.randomUUID().toString()+fileSuffix;
                //上传
                file.transferTo(new File(fileDir+"/"+fileName));


            }
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        }


        return  "文件上传成功！";
    }
}

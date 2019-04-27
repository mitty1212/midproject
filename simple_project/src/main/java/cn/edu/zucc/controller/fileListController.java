package cn.edu.zucc.controller;


//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.jni.Buffer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.util.http.parser.MediaType;
//import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.MultiIdentifierLoadAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.misc.IOUtils;

//import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value="/uploads")
public class fileListController {
    @Autowired
    ResourceLoader loader;
    private static final Logger log = LoggerFactory.getLogger(fileListController.class);

    @PostMapping("/uploadfile")
    @ResponseBody
    public Map<String,String> upload(@RequestParam("file")MultipartFile file) throws IOException {
        log.info("[文件类型] - [0]",file.getContentType());
        log.info("[文件名称] - [0]",file.getOriginalFilename());
        log.info("[文件大小] - [0]",file.getSize());
        file.transferTo(new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+file.getOriginalFilename()));
        Map<String,String> result = new HashMap<>(16);
        result.put("ContentType",file.getContentType());
        result.put("fileName",file.getOriginalFilename());
        result.put("fileSize",file.getSize()+"");
        return result;
    }

    @PostMapping("/getFileList")
    @ResponseBody
    public ArrayList<String> getFileList() throws IOException {

        //file.transferTo(new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+file.getOriginalFilename()));
        File file = new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest");
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0;i < file.list().length;i++){
            arrayList.add(file.list()[i]);
        }
        return arrayList;
    }

    @PostMapping("/deleteFileByName")
    @ResponseBody
    public String deleteFileByName(@RequestBody String name) throws IOException {
        //file.transferTo(new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+file.getOriginalFilename()));
        File file = new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+name.substring(5));
        if(!file.exists()){
            return "文件不存在";
        }else{
            file.delete();
            return "文件删除成功";
        }
    }

    @PostMapping("/downloadFileByName")
    @ResponseBody
    public ResponseEntity downloadFileByName(@RequestBody String name) throws IOException {
        //file.transferTo(new File("C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+file.getOriginalFilename()));
        String filePath = "C:\\Users\\mitty\\Desktop\\信息系统分析设计与开发\\uploadtest\\"+name.substring(5);
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");

        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");

        headers.add("Pragma", "no-cache");

        headers.add("Expires", "0");

        headers.add("Last-Modified", new Date().toString());

        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }


}

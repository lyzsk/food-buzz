package cn.sichu.fda.controller;

import cn.sichu.fda.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author sichu
 * @date 2022/12/21
 **/
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${food-delivery-app.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file 跟前端的name="file"对应
     * @return Result<String></>
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        // file上传后是一个 .tmp 文件存在AppData里, 后缀改成 jpg/png 就能以图片访问, 如果不转存这个临时文件,
        // 本次upload后就会自动删除
        log.info(file.toString());
        String originalFilename = file.getOriginalFilename();
        String suffix =
            originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream =
                new FileInputStream(new File(basePath + name));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

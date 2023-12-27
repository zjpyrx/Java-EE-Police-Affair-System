package org.group05.police.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.group05.police.components.NonStaticResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class Video {

    @RequestMapping(value = "/getVideo/video", method = RequestMethod.GET)
    @ResponseBody
    public void getVideo(HttpServletRequest request, HttpServletResponse response, @RequestParam("number") String videoID) {
        // 视频路径
        String file = "D:/"+videoID+".mp4";
        System.out.println(file);
        try {
            //	获得视频文件的输入流
            FileInputStream inputStream = new FileInputStream(file);
            // 创建字节数组，数组大小为视频文件大小
            byte[] data = new byte[inputStream.available()];
            // 将视频文件读入到字节数组中
            inputStream.read(data);
            String diskfilename = "final.mp4";
            // 设置返回数据格式
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
            System.out.println("data.length " + data.length);
            response.setContentLength(data.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            // 获得 response 的字节流
            OutputStream os = response.getOutputStream();
            // 将视频文件的字节数组写入 response 中
            os.write(data);
            //先声明的流后关掉！
            os.flush();
            os.close();
            inputStream.close();

        } catch (Exception e) {

        }
    }
}

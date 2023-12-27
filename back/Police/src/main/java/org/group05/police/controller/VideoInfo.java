package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.VideoRequest;
import org.group05.police.DTO.VideoResponse;
import org.group05.police.tools.SessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class VideoInfo {
    @PostMapping("api/videoInfo")
    public VideoResponse videoInfo(@RequestBody VideoRequest input) {
        Map<String,String> params=new HashMap<>();
        params.put("videoID",input.getVideoID());
        SqlSession sqlSession = SessionFactory.getSqlSession();
        Integer isExist=sqlSession.selectOne("VideoMapper.findVideo",params);
        if (isExist==null)
        {
            VideoResponse videoResponse=new VideoResponse();
            videoResponse.setOk("fail");
            return videoResponse;
        }
        else
        {
            VideoResponse videoResponse=new VideoResponse();
            videoResponse.setOk("ok");
            return videoResponse;
        }
    }
}

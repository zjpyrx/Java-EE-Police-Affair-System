package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.PoliceAccountImpl;
import org.group05.police.DAO.Impl.PolicemenImpl;
import org.group05.police.DTO.LoginRequest;
import org.group05.police.DTO.LoginResponse;
import org.group05.police.tools.SessionFactory;
import org.group05.police.tools.TokenFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
    @PostMapping("api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return new LoginResponse(false, "数据库连接失败",null);
        }
        PolicemenImpl policemenImpl = new PolicemenImpl();
        PoliceAccountImpl policeAccountImpl=new PoliceAccountImpl();
        if (policemenImpl.login(loginRequest.getUsername(), loginRequest.getPassword(), sqlSession)){
            int myAuthority=policeAccountImpl.getAuthority(loginRequest.getUsername(), sqlSession);
            String token= TokenFactory.createToken(Integer.parseInt(loginRequest.getUsername()), loginRequest.getUsername(),myAuthority);
            return new LoginResponse(true, "登录成功",token);
        }
        return new LoginResponse(false, "登录失败",null);
    }
}

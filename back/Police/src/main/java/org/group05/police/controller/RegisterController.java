package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.PolicemenImpl;
import org.group05.police.DTO.RegisterRequest;
import org.group05.police.DTO.RegisterResponse;
import org.group05.police.DTO.ValidationRequest;
import org.group05.police.tools.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterController {

    String projectPath = System.getProperty("user.dir");

    @PutMapping("api/Register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){

        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return new RegisterResponse(false, "数据库连接失败");
        }
        PolicemenImpl policemenImpl = new PolicemenImpl();
        Map<String,String> regRequest = new HashMap<>();
        // 将registerRequest中的数据提取到字典
        regRequest.put("id_number",registerRequest.getId_number());
        regRequest.put("birthday",registerRequest.getBirthday());
        regRequest.put("email",registerRequest.getEmail());
        regRequest.put("gender",registerRequest.getGender());
        regRequest.put("nation",registerRequest.getNation());
        regRequest.put("phone_number",registerRequest.getPhone_number());
        regRequest.put("police_name",registerRequest.getPolice_name());
        regRequest.put("police_number",registerRequest.getPolice_number());
        regRequest.put("position",registerRequest.getPosition());
        regRequest.put("status",registerRequest.getStatus());

        
        boolean success = policemenImpl.register(regRequest, sqlSession);
        if (success) {

            // 保存图片
            try {
                String base64Image = registerRequest.getImage();
                String policeNumber = registerRequest.getPolice_number();
                SaveImage(base64Image, policeNumber);
            } catch (Exception e) {
                return new RegisterResponse(false, "图片上传失败");
            }

            return new RegisterResponse(true, "注册成功");
        } else {
            return new RegisterResponse(false, "注册失败，可能是用户名已存在或其他数据库错误");
        }
    }


    @PostMapping("api/Register")
    public ResponseEntity<String> validateRegistration(@RequestBody Map<String, String> validationMap) {
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库连接失败");
        }
        PolicemenImpl policemenImpl = new PolicemenImpl();
        try {
            boolean isValid = policemenImpl.validate(validationMap, sqlSession);
            return isValid ? ResponseEntity.ok("ok") : ResponseEntity.ok("fail");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not");
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    private void SaveImage(String base64Image, String policeNumber){
        byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
        Path imagePath = Paths.get(projectPath + "/img/" + policeNumber + ".jpg");

        try (FileOutputStream imageOutFile = new FileOutputStream(imagePath.toFile())) {
            imageOutFile.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
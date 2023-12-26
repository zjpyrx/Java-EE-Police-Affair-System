package org.group05.police.controller;

import java.util.List;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.CitizenInfoImpl;
import org.group05.police.DTO.CitizenInfoRequest;
import org.group05.police.DTO.CitizenInfoResponse;
import org.group05.police.tools.SessionFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CitizenInfoController {
    @GetMapping("api/getCitizenInfo")
    public List<CitizenInfoResponse> getCitizenInfo(@RequestParam(required = false) String citizenID,
                                                    @RequestParam(required = false) String citizenName,
                                                    @RequestParam(required = false) String citizenSex,
                                                    @RequestParam(required = false) String citizenAddress) {
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            List<CitizenInfoResponse> rt=new ArrayList<>();
            CitizenInfoResponse r=new CitizenInfoResponse();
            r.setSuccess(false);
            r.setMessage("数据库连接失败");
            rt.add(r);
            return rt;
        }

        CitizenInfoImpl citizenInfoImpl=new CitizenInfoImpl();
        return citizenInfoImpl.getCitizens(citizenID, citizenName, citizenSex, citizenAddress,sqlSession);
    }

    @PostMapping("api/addCitizen")
    public ResponseEntity<String> addCitizen(@RequestBody CitizenInfoRequest citizenInfo) {
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库连接失败");
        }
        if (citizenInfo.getCitizenID().isEmpty() || citizenInfo.getCitizenName().isEmpty()
                || citizenInfo.getCitizenSex().isEmpty() || citizenInfo.getCitizenAddress().isEmpty()
                || citizenInfo.getMotherID().isEmpty() || citizenInfo.getFatherID().isEmpty()) {
            System.out.println("新增公民信息不全！");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增公民信息不全！");
        }
        else if(citizenInfo.getCitizenID().equals(citizenInfo.getMotherID()) || citizenInfo.getCitizenID().equals(citizenInfo.getFatherID())
                || citizenInfo.getFatherID().equals(citizenInfo.getMotherID())){
            System.out.println("不同人身份证号不能相同！");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("不同人身份证号不能相同！");
        }
        CitizenInfoImpl citizenInfoImpl=new CitizenInfoImpl();
        String res=citizenInfoImpl.addCitizen(citizenInfo,sqlSession);
        if(res.equals("成功")){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else{
            System.out.println(res);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/api/delCitizen")
    public ResponseEntity<String> deleteCitizen(@RequestParam String citizenID) {
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库连接失败");
        }
        System.out.println(citizenID);
        CitizenInfoImpl citizenInfoImpl=new CitizenInfoImpl();
        String res=citizenInfoImpl.deleteCitizen(citizenID,sqlSession);
        if(res.equals("成功")){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else{
            System.out.println(res);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
    @PutMapping("/api/updCitizen")
    public ResponseEntity<String> updateCitizen(@RequestBody CitizenInfoRequest citizenInfo) {
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库连接失败");
        }
        if (citizenInfo.getCitizenID().isEmpty() || citizenInfo.getCitizenName().isEmpty()
                || citizenInfo.getCitizenSex().isEmpty() || citizenInfo.getCitizenAddress().isEmpty()
                || citizenInfo.getMotherID().isEmpty() || citizenInfo.getFatherID().isEmpty()) {
            System.out.println("新增公民信息不全！");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增公民信息不全！");
        }
        CitizenInfoImpl citizenInfoImpl=new CitizenInfoImpl();
        String res=citizenInfoImpl.updateCitizen(citizenInfo,sqlSession);
        if(res.equals("成功")){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else{
            System.out.println(res);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}

package org.group05.police.controller;


import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.PoliceAccountImpl;
import org.group05.police.DAO.Impl.PolicemenImpl;
import org.group05.police.DAO.Impl.PolicemenInfoImpl;
import org.group05.police.DTO.PolicemenInfoRequest;
import org.group05.police.DTO.PolicemenInfoResponse;
import org.group05.police.DTO.PolicemenInfoUpdateRequest;
import org.group05.police.tools.SessionFactory;
import org.group05.police.tools.TokenFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PolicemenInfoController {
    @GetMapping("api/policemenInfo")
    public ResponseEntity<List<PolicemenInfoResponse>> getPolicemenInfo(
            @RequestParam(required = false) String policemenID,
            @RequestParam(required = false) String policemenName,
            @RequestParam(required = false) String policemenIDNum,
            @RequestParam(required = false) String policemenYear,
            @RequestParam(required = false) String policemenSex,
            @RequestParam(required = false) String policemenNation,
            @RequestParam(required = false) String policemenStatus,
            @RequestParam(required = false) String policemenPosition){
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        PolicemenInfoRequest policemenInfoRequest = new PolicemenInfoRequest();
        policemenInfoRequest.setPolicemenID(policemenID);
        policemenInfoRequest.setPolicemenName(policemenName);
        policemenInfoRequest.setPolicemenIDNum(policemenIDNum);
        policemenInfoRequest.setPolicemenYear(policemenYear);
        policemenInfoRequest.setPolicemenSex(policemenSex);
        policemenInfoRequest.setPolicemenNation(policemenNation);
        policemenInfoRequest.setPolicemenStatus(policemenStatus);
        policemenInfoRequest.setPolicemenPosition(policemenPosition);

        PolicemenInfoImpl policemenInfoImpl=new PolicemenInfoImpl();
        List<PolicemenInfoResponse> res=new ArrayList<>();
        res=policemenInfoImpl.getPolicemenInfo(policemenInfoRequest,sqlSession);


        return ResponseEntity.status(HttpStatus.OK).body(res);
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
    @DeleteMapping("api/delPolicemen")
    public ResponseEntity<String> deletePolicemen(@RequestParam String policemenID){
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        System.out.println(policemenID);
        PolicemenInfoImpl policemenInfoImpl=new PolicemenInfoImpl();
        String res = policemenInfoImpl.deletePolicemen(policemenID,sqlSession);
        if(res.equals("成功")){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else{
            System.out.println(res);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
    @PutMapping("/api/updPolicemen")
    public ResponseEntity<String> updatePolicemen(@RequestBody PolicemenInfoUpdateRequest policemenInfoUpdateRequest){
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        System.out.println("policemenInfoUpdateRequest");
        System.out.println(policemenInfoUpdateRequest.getPolicemenID());

        if (policemenInfoUpdateRequest.getPolicemenID().isEmpty() || policemenInfoUpdateRequest.getPolicemenName().isEmpty()
                || policemenInfoUpdateRequest.getPolicemenSex().isEmpty() || policemenInfoUpdateRequest.getPolicemenYear().isEmpty()
                || policemenInfoUpdateRequest.getPolicemenMonth().isEmpty() || policemenInfoUpdateRequest.getPolicemenDay().isEmpty()
                || policemenInfoUpdateRequest.getPolicemenNation().isEmpty() || policemenInfoUpdateRequest.getPolicemenIDNum().isEmpty()
                || policemenInfoUpdateRequest.getPolicemenPhone().isEmpty() || policemenInfoUpdateRequest.getPolicemenEmail().isEmpty()) {
            System.out.println("修改警员信息失败！信息不全！");
            return ResponseEntity.badRequest().body("修改警员信息失败！信息不全！");
        }


        PolicemenInfoImpl policemenInfoImpl=new PolicemenInfoImpl();
        String res = policemenInfoImpl.updatePolicemen(policemenInfoUpdateRequest,sqlSession);
        if(res.equals("成功")){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else{
            System.out.println(res);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}

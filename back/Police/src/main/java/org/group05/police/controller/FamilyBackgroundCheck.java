package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.FamilyImpl;
import org.group05.police.DAO.Impl.PolicemenImpl;
import org.group05.police.DAO.entity.Crime;
import org.group05.police.DTO.FamilyCheckRequest;
import org.group05.police.DTO.FamilyCheckResponse;
import org.group05.police.DTO.Info;
import org.group05.police.tools.SessionFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FamilyBackgroundCheck {
    @PostMapping("api/FamilybgCheck")
    public FamilyCheckResponse FamilybgCheck(@RequestBody FamilyCheckRequest input) {
        String inputText = input.getInputText(); // 从请求的JSON数据中获取输入的字符串
        FamilyCheckResponse queryResult = new FamilyCheckResponse(); // 创建返回的JSON数据对象

        SqlSession sqlSession = SessionFactory.getSqlSession();

        Map<String, String> params = new HashMap<>();
        params.put("target", inputText);
        FamilyImpl familyImpl = new FamilyImpl();
        List<Crime> crimes = familyImpl.queryCenterCrime(params, sqlSession);
        if (crimes.isEmpty()) {
            return queryResult;
        } else {
            Info tmp = new Info();
            for (Crime crime : crimes) {
                tmp.setName(crime.getCitizenName());
                tmp.setID(crime.getID_num());
                tmp.setGender(crime.getGender());
                String readyType = crime.getCaseType();
                if (notExist(readyType, tmp.getCrimeType())) {
                    // 类型不重复
                    tmp.getCrimeType().add(readyType);
                }
                queryResult.people.add(tmp);
                queryResult.relationship.add("");
                searchParent(queryResult, tmp.getID(),sqlSession);
                searchChild(queryResult, tmp.getID(),sqlSession);
            }
        }
        return queryResult;
    }
    private boolean notExist(String type, List<String> types) {
        for(String i : types)
        {
            if (i.equals(type))
                return false;
        }
        return true;
    }
    private void searchChild(FamilyCheckResponse output, String targetID,SqlSession sqlSession)
    {
        Map<String,String> params = new HashMap<>();
        params.put("target",targetID);
        FamilyImpl familyImpl = new FamilyImpl();
        List<Crime> crimes = familyImpl.queryChildCrime(params,sqlSession);
        Info[] tmp = new Info[2];
        tmp[0] = new Info();
        tmp[1] = new Info();
        if (!crimes.isEmpty()){
            Crime first= crimes.get(0);
            // 去掉第一个
            crimes.remove(0);
            tmp[0].setID(first.getID_num());
            tmp[0].setName(first.getCitizenName());
            tmp[0].setGender(first.getGender());
            tmp[0].getCrimeType().add(first.getCaseType());
        }
        for (Crime crime:crimes){
            tmp[1].setID(crime.getID_num());
            tmp[1].setName(crime.getCitizenName());
            tmp[1].setGender(crime.getGender());
            String readyType = crime.getCaseType();
            if (tmp[0].getID().equals(tmp[1].getID())){
                // 还是同一个人
                if (notExist(readyType, tmp[0].getCrimeType())) {
                    // 类型不重复
                    tmp[0].getCrimeType().add(readyType);
                }
            }
            else{
                output.people.add(tmp[0]);
                output.relationship.add(tmp[0].getGender().equals("F")?"女儿":"儿子");
                // 先存储信息到结果，再挪信息
                tmp[0] = tmp[1];
                tmp[0].getCrimeType().add(readyType);
            }
        }
        // 全部读完以后，最后一个人在[0]，未写入
        if (tmp[0].getName() != null)
        {
            output.people.add(tmp[0]);
            output.relationship.add(tmp[0].getGender().equals("F")?"女儿":"儿子");
        }
    }
    private void searchParent(FamilyCheckResponse output, String targetID,SqlSession sqlSession)
    {
        Map<String,String> params = new HashMap<>();
        params.put("target",targetID);
        FamilyImpl familyImpl = new FamilyImpl();
        List<Crime> crimes = familyImpl.queryFatherCrime(params,sqlSession);
        Info tmp = new Info();
        for (Crime crime:crimes){
            tmp.setID(crime.getID_num());
            tmp.setName(crime.getCitizenName());
            tmp.setGender(crime.getGender());
            String readyType = crime.getCaseType();
            if (notExist(readyType, tmp.getCrimeType())) {
                // 类型不重复
                tmp.getCrimeType().add(readyType);
            }
        }
        output.people.add(tmp);
        output.relationship.add("父亲");

        // 母亲
        List<Crime> motherCrime = familyImpl.queryMotherCrime(params,sqlSession);
        Info tmpM=new Info();
        for (Crime crime:motherCrime){
            tmpM.setID(crime.getID_num());
            tmpM.setName(crime.getCitizenName());
            tmpM.setGender(crime.getGender());
            String readyType = crime.getCaseType();
            if (notExist(readyType, tmpM.getCrimeType())) {
                // 类型不重复
                tmpM.getCrimeType().add(readyType);
            }
        }
        output.people.add(tmpM);
        output.relationship.add("母亲");

    }
}

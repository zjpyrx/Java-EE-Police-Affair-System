package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.CitizenInfoMapper;
import org.group05.police.DTO.CitizenInfoResponse;
import org.group05.police.DTO.CitizenInfoRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitizenInfoImpl implements CitizenInfoMapper{
    @Override
    public List<CitizenInfoResponse> getCitizens(String citizenID, String citizenName, String citizenSex, String citizenAddress, SqlSession sqlSession){
        Map<String, Object> params = new HashMap<>();
        params.put("citizenID", citizenID);
        params.put("citizenName", citizenName);
        params.put("citizenSex", citizenSex);
        params.put("citizenAddress", citizenAddress);
        return sqlSession.selectList("CitizenMapper.getCitizens", params);
    }
    @Override
    public String addCitizen(CitizenInfoRequest citizenInfo,SqlSession sqlSession){
        Long res=sqlSession.selectOne("CitizenMapper.selectID",citizenInfo);
        System.out.println(res);
        if(res!=0){
            System.out.println("该身份证号码已存在！请重新输入！");
            return "该身份证号码已存在！请重新输入！";
        }
        res=sqlSession.selectOne("CitizenMapper.selectMotherID",citizenInfo);
        System.out.println(res);
        if(res==0){
            System.out.println("母亲身份证号码不存在！请重新输入！");
            return "母亲身份证号码不存在！请重新输入！";
        }
        res=sqlSession.selectOne("CitizenMapper.selectFatherID",citizenInfo);
        System.out.println(res);
        if(res==0){
            System.out.println("父亲身份证号码不存在！请重新输入！");
            return "父亲身份证号码不存在！请重新输入！";
        }
        int rowsAffected = sqlSession.insert("CitizenMapper.addCitizen", citizenInfo);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            sqlSession.commit();
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("插入数据时发生错误");
            return "插入数据时发生错误";
        }
    }

    public String deleteCitizen(String citizenID,SqlSession sqlSession){
        Map<String, Object> params = new HashMap<>();
        params.put("citizenID",citizenID);
        Long res=sqlSession.selectOne("CitizenMapper.selectID",params);
        System.out.println(res);
        if(res==0){
            System.out.println("该身份证号码不存在！请重新输入！");
            return "该身份证号码不存在！请重新输入！";
        }
        int rowsAffected=sqlSession.delete("CitizenMapper.deleteCitizen",params);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            sqlSession.commit();
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("删除数据时发生错误");
            return "删除数据时发生错误";
        }
    }

    public String updateCitizen(CitizenInfoRequest citizenInfo,SqlSession sqlSession){
        Long res=sqlSession.selectOne("CitizenMapper.selectID",citizenInfo);
        System.out.println(res);
        if(res==0){
            System.out.println("该身份证号码不存在！请重新输入！");
            return "该身份证号码不存在！请重新输入！";
        }
        int rowsAffected = sqlSession.update("CitizenMapper.updateCitizen", citizenInfo);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            sqlSession.commit();
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("更新数据时发生错误");
            return "更新数据时发生错误";
        }
    }
}

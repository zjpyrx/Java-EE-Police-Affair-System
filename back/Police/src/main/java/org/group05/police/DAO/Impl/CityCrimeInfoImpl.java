package org.group05.police.DAO.Impl;

import io.swagger.models.auth.In;
import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.CityCrimeInfoMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityCrimeInfoImpl implements CityCrimeInfoMapper {
    public List<String> GetDistrictName(String cityName, SqlSession sqlSession){
        Map<String, Object> params = new HashMap<>();
        params.put("cityName",cityName);
        return sqlSession.selectList("CityCrimeInfoMapper.getDistrictNames", params);
    }
    public List<Integer>  GetDistrictPopulations(List<String> districtNames,SqlSession sqlSession){
        return sqlSession.selectList("CityCrimeInfoMapper.getPopulationByDistrictNames", districtNames);
    }
    public List<Integer> GetDistrictCrimeNums(List<String> districtNames,SqlSession sqlSession){
        return sqlSession.selectList("CityCrimeInfoMapper.getCrimeNumByDistrictNames", districtNames);
    }
    public List<List<Object>> GetDistrictCrimeType(List<String> districtNames,SqlSession sqlSession){
        List<List<Object>> res = new ArrayList<>();
        for(String districtName : districtNames){
            Map<String,String> param=new HashMap<>();
            param.put("districtName",districtName);
            Map<String,Object> temp=sqlSession.selectOne("CityCrimeInfoMapper.getDistrictCrimeTypes", param);
            System.out.println("犯罪单个地名");
            System.out.println(temp);
            List<Object> singleResult = new ArrayList<>();
            singleResult.add(temp.get("count1"));
            singleResult.add(temp.get("count2"));
            singleResult.add(temp.get("count3"));
            singleResult.add(temp.get("count4"));
            singleResult.add(temp.get("count5"));
            singleResult.add(temp.get("count6"));
            res.add(singleResult);
        }
        return res;
    }
    public List<Map<String, Object>> GetDistrictCrimeTime(List<String> districtNames,SqlSession sqlSession){
        List<Map<String, Object>> res=new ArrayList<>();//每张map的字段值：（时间值）,数量
        List<Map<String, Object>> temp=new ArrayList<>();//每张map的字段值：count，time_period

        for(String districtName : districtNames){
            temp=sqlSession.selectList("CityCrimeInfoMapper.getDistrictCrimeTime",districtName);
            System.out.println("根据一个地名搜索出的：");
            System.out.println(temp);

            Map<String, Object> singleDistrict=new HashMap<>();
            for(Map<String, Object> singlePeriod : temp){
                singleDistrict.put(singlePeriod.get("TIME_PERIOD").toString(),singlePeriod.get("count"));
            }
            System.out.println("singleDistrict"+singleDistrict);
            res.add(singleDistrict);
        }
        System.out.println("res:"+res);
        return res;
    }
}
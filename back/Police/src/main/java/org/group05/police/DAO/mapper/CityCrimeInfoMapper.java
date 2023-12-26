package org.group05.police.DAO.mapper;


import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface CityCrimeInfoMapper {
    public List<String> GetDistrictName(String cityName, SqlSession sqlSession);
    public List<Integer>  GetDistrictPopulations(List<String> districtNames,SqlSession sqlSession);
    public List<Integer> GetDistrictCrimeNums(List<String> districtNames,SqlSession sqlSession);
    public List<List<Object>> GetDistrictCrimeType(List<String> districtNames,SqlSession sqlSession);
    public List<Map<String, Object>> GetDistrictCrimeTime(List<String> districtNames,SqlSession sqlSession);
}

package org.group05.police.DAO.mapper;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.entity.Crime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FamilyMapper {
    public List<Crime> queryCenterCrime(Map<String,String> params, SqlSession sqlSession);
    public List<Crime> queryChildCrime(Map<String,String> params, SqlSession sqlSession);
    public List<Crime> queryFatherCrime(Map<String,String> params, SqlSession sqlSession);
    public List<Crime> queryMotherCrime(Map<String,String> params, SqlSession sqlSession);
}

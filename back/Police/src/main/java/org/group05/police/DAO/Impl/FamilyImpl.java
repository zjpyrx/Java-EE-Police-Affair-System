package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.entity.Crime;
import org.group05.police.DAO.mapper.FamilyMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyImpl implements FamilyMapper {
    @Override
    public List<Crime> queryCenterCrime(Map<String,String> params, SqlSession sqlSession) {
        return sqlSession.selectList("FamilyMapper.queryMe", params);
    }

    @Override
    public List<Crime> queryChildCrime(Map<String,String> params, SqlSession sqlSession) {
        return sqlSession.selectList("FamilyMapper.queryChild", params);
    }

    @Override
    public List<Crime> queryFatherCrime(Map<String,String> params, SqlSession sqlSession) {
        return sqlSession.selectList("FamilyMapper.queryFather", params);
    }

    @Override
    public List<Crime> queryMotherCrime(Map<String,String> params, SqlSession sqlSession) {
        return sqlSession.selectList("FamilyMapper.queryMother", params);
    }
}

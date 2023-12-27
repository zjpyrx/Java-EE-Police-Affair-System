package org.group05.police.DAO.mapper;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface PolicemenMapper {
    public boolean login(String id, String pwd, SqlSession sqlSession);
    public boolean register(Map<String, String> regRequest, SqlSession sqlSession);
    public boolean validate(Map<String, String> validationRequest, SqlSession sqlSession);
}

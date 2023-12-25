package org.group05.police.DAO.mapper;

import org.apache.ibatis.session.SqlSession;

public interface PoliceAccountMapper {
    public int getAuthority(String id, SqlSession sqlSession);
}

package org.group05.police.DAO.mapper;

import org.apache.ibatis.session.SqlSession;

public interface PolicemenMapper {
    public boolean login(String id, String pwd, SqlSession sqlSession);
}

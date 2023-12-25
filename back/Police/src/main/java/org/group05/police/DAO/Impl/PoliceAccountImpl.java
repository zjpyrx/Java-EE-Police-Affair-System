package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.PoliceAccountMapper;

public class PoliceAccountImpl implements PoliceAccountMapper {
    @Override
    public int getAuthority(String id, SqlSession sqlSession){
        return(sqlSession.selectOne("PoliceAccountMapper.getAuthority",id));
    }
}

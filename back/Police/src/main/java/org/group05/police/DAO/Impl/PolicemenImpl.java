package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.entity.Policemen;
import org.group05.police.DAO.mapper.PolicemenMapper;
import org.group05.police.DTO.LoginResponse;
import org.springframework.web.servlet.tags.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolicemenImpl implements PolicemenMapper{
    @Override
    public boolean login(String id, String pwd, SqlSession sqlSession) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", id);
        params.put("password", pwd);
        List<Integer> police=sqlSession.selectList("PolicemenMapper.checkPassword", params);
        System.out.println(police);
        return !police.isEmpty();
    }
}

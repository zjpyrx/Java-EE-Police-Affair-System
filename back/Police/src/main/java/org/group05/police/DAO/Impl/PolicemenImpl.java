package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.PolicemenMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolicemenImpl implements PolicemenMapper{
    @Override
    public boolean login(String id, String pwd, SqlSession sqlSession) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("pwd", pwd);
        List<Integer> police=sqlSession.selectList("PolicemenMapper.checkPassword", params);
        System.out.println(police);
        return !police.isEmpty();
    }

    @Override
    public boolean register(Map<String, String> regRequest, SqlSession sqlSession) {
        try {
            sqlSession.insert("PolicemenMapper.registerPolice", regRequest);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validate(Map<String, String> validationRequest, SqlSession sqlSession) {
        // 根据 validationRequest 中的 queryType 决定使用哪个 SQL 映射。
        String queryType = validationRequest.get("queryType");
        String queryContent = validationRequest.get("queryContent");
        String mapperId = "";
        if ("police_number".equals(queryType)) {
            mapperId = "PolicemenMapper.validatePoliceNumber";
        } else if ("ID_number".equals(queryType)) {
            mapperId = "PolicemenMapper.validateIDNumber";
        }

        try {
            int count = sqlSession.selectOne(mapperId, queryContent);
            return count == 0; // 如果没有记录，返回 true 表示校验通过
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.CasesMapper;
import org.group05.police.DTO.CaseInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CasesImpl implements CasesMapper {
    @Override
    public List<CaseInfoResponse> getCaseInfo(Map<String, String> CaseInfoRequest, SqlSession sqlSession) {
        System.out.println(sqlSession.selectList("CaseInfoMapper.getCaseInfo", CaseInfoRequest).toString());
        return sqlSession.selectList("CaseInfoMapper.getCaseInfo", CaseInfoRequest);
    }
    
}

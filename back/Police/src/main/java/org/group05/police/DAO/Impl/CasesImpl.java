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
    public List<CaseInfoResponse> getCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession) {
        // System.out.println(sqlSession.selectList("CaseInfoMapper.getCaseInfo", CaseInfoRequest).toString());
        return sqlSession.selectList("CaseInfoMapper.getCaseInfo", caseInfoRequest);
    }
    @Override
    public CaseInfoResponse getSingleCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession){
        return sqlSession.selectOne("CaseInfoMapper.getCaseInfo", caseInfoRequest);
    }

    @Override
    public boolean insertCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession) {
        try {
            sqlSession.insert("CaseInfoMapper.insertCaseInfo", caseInfoRequest);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCase(Map<String, String> caseInfoRequest, SqlSession sqlSession) {
        try {
            sqlSession.delete("CaseInfoMapper.deleteCase", caseInfoRequest);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean closeCase(Map<String, String> caseInfoRequest, SqlSession sqlSession){
        try {
            sqlSession.update("CaseInfoMapper.closeCase", caseInfoRequest);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateCase(Map<String, String> caseInfoRequest, SqlSession sqlSession){
        try {
            sqlSession.update("CaseInfoMapper.updateCase", caseInfoRequest);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }
    }
    
}

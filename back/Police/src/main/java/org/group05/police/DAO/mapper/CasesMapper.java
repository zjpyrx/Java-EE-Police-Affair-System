package org.group05.police.DAO.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.CaseInfoResponse;

public interface CasesMapper {
    public List<CaseInfoResponse> getCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession);
    public CaseInfoResponse getSingleCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession);
    public boolean insertCaseInfo(Map<String, String> caseInfoRequest, SqlSession sqlSession);
    public boolean deleteCase(Map<String, String> caseInfoRequest, SqlSession sqlSession);
    public boolean closeCase(Map<String, String> caseInfoRequest, SqlSession sqlSession);
    public boolean updateCase(Map<String, String> caseInfoRequest, SqlSession sqlSession);
}

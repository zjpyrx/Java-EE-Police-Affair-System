package org.group05.police.DAO.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.CaseInfoResponse;

public interface CasesMapper {
    public List<CaseInfoResponse> getCaseInfo(Map<String, String> CaseInfoRequest, SqlSession sqlSession);
}

package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.CasesImpl;
import org.group05.police.DTO.CaseInfoResponse;
import org.group05.police.tools.SessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CasesController {
    @RequestMapping("/api/caseInfo")
    public List<CaseInfoResponse> getCaseInfo(
        @RequestParam(required = false) String caseID,
        @RequestParam(required = false) String caseType,
        @RequestParam(required = false) String caseStatus,
        @RequestParam(required = false) String caseAddress,
        @RequestParam(required = false) String caseRanking) {
        try (SqlSession sqlSession = SessionFactory.getSqlSession()) {
            if (sqlSession == null){
                throw new RuntimeException("数据库连接失败");
            }
            CasesImpl casesImpl = new CasesImpl();
            Map<String, String> caseinfoRequest = new HashMap<>();
            // 将 CaseInfoRequest 中的数据提取到字典
            caseinfoRequest.put("caseID", caseID);
            caseinfoRequest.put("caseType", caseType);
            caseinfoRequest.put("caseStatus", caseStatus);
            caseinfoRequest.put("caseAddress", caseAddress);
            caseinfoRequest.put("caseRanking", caseRanking);

            // System.out.println(caseinfoRequest.toString());
            return casesImpl.getCaseInfo(caseinfoRequest,sqlSession);
        }
    }
}

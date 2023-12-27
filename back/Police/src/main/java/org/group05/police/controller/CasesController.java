package org.group05.police.controller;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.CasesImpl;
import org.group05.police.DTO.CaseInfoResponse;
import org.group05.police.tools.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CasesController {

    private static final Map<String, String> typeToIDMap = new HashMap<>();

    static {
        typeToIDMap.put("强奸", "A");
        typeToIDMap.put("抢劫", "R");
        typeToIDMap.put("故意伤害", "H");
        typeToIDMap.put("盗窃", "T");
        typeToIDMap.put("诈骗", "C");
        typeToIDMap.put("谋杀", "M");
    }

    private static String typeToID(String type) {
        return typeToIDMap.getOrDefault(type, "");
    }

    @RequestMapping("/api/caseInfo")
    public ResponseEntity<List<CaseInfoResponse>> getCaseInfo(
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
            List<CaseInfoResponse> res = casesImpl.getCaseInfo(caseinfoRequest,sqlSession);
            // System.out.println(caseinfoRequest.toString());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    @PostMapping("/api/addCase")
    public ResponseEntity<Boolean> addCase(
        @RequestParam(required = false) String caseID,
        @RequestParam(required = false) String caseType,
        @RequestParam(required = false) String caseAddress,
        @RequestParam(required = false) String caseRanking) {
        try (SqlSession sqlSession = SessionFactory.getSqlSession()) {
            if (sqlSession == null){
                throw new RuntimeException("数据库连接失败");
            }
            CasesImpl casesImpl = new CasesImpl();
            Map<String, String> caseinfoRequest = new HashMap<>();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            String formattedDate = now.format(formatter);
            caseinfoRequest.put("caseID", typeToID(caseType) + caseID);
            caseinfoRequest.put("registerTime", formattedDate);
            caseinfoRequest.put("caseType", caseType);
            caseinfoRequest.put("caseStatus", "立案");
            caseinfoRequest.put("caseAddress", caseAddress);
            caseinfoRequest.put("caseRanking", caseRanking);

            System.out.println(caseinfoRequest.toString());
            try {
                casesImpl.insertCaseInfo(caseinfoRequest,sqlSession);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK).body(false);
            }
        
            // System.out.println(caseinfoRequest.toString());
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }

    @DeleteMapping("/api/delCase")
    public ResponseEntity<?> deleteCase(
        @RequestParam(required = false) String caseID,
        @RequestParam(required = false) String caseType) {

        try (SqlSession sqlSession = SessionFactory.getSqlSession()) {
            if (sqlSession == null){
                throw new RuntimeException("数据库连接失败");
            }
            CasesImpl casesImpl = new CasesImpl();
            Map<String, String> caseinfoRequest = new HashMap<>();
            // 将 CaseInfoRequest 中的数据提取到字典
            caseinfoRequest.put("caseID", typeToID(caseType) + caseID);
            caseinfoRequest.put("caseType", caseType);
            boolean isDeleted = casesImpl.deleteCase(caseinfoRequest,sqlSession);
            if (isDeleted) {
                return ResponseEntity.ok("案件删除成功");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到相应案件");
            }
        }

    }
    
    @PutMapping("/api/closeCase")
    public ResponseEntity<CaseInfoResponse> closeCase(
        @RequestParam(required = false) String caseID,
        @RequestParam(required = false) String caseType) {
        try (SqlSession sqlSession = SessionFactory.getSqlSession()) {
            if (sqlSession == null){
                throw new RuntimeException("数据库连接失败");
            }
            CasesImpl casesImpl = new CasesImpl();
            Map<String, String> caseinfoRequest = new HashMap<>();
            // 将 CaseInfoRequest 中的数据提取到字典
            caseinfoRequest.put("caseID", typeToID(caseType) + caseID);
            caseinfoRequest.put("caseType", caseType);
            CaseInfoResponse res = casesImpl.getSingleCaseInfo(caseinfoRequest,sqlSession);
            try {
                casesImpl.closeCase(caseinfoRequest,sqlSession);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            // System.out.println(caseinfoRequest.toString());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    @PutMapping("/api/updCase")
    public ResponseEntity<String> updateCaseInfo(
            @RequestParam("oriCaseID") String oriCaseID,
            @RequestParam("oriCaseType") String oriCaseType,
            @RequestParam("updCaseID") String updCaseID,
            @RequestParam("updCaseType") String updCaseType,
            @RequestParam("updCaseAddress") String updCaseAddress,
            @RequestParam("updCaseRanking") String updCaseRanking) {

        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库连接失败");
        }

        CasesImpl casesImpl = new CasesImpl();
        Map<String, String> caseInfoRequest = new HashMap<>();
        
        caseInfoRequest.put("oriCaseID", typeToID(oriCaseType) + oriCaseID);
        caseInfoRequest.put("updCaseID", typeToID(updCaseType) + updCaseID);
        caseInfoRequest.put("updCaseType", updCaseType);
        caseInfoRequest.put("updCaseAddress", updCaseAddress);
        caseInfoRequest.put("updCaseRanking", updCaseRanking);

        // 执行更新操作
        boolean success = casesImpl.updateCase(caseInfoRequest, sqlSession);
        if (success) {
            return ResponseEntity.ok("更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败");
        }
    }
}

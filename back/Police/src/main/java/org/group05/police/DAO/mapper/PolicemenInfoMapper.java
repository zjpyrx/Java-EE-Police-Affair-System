package org.group05.police.DAO.mapper;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.PolicemenInfoRequest;
import org.group05.police.DTO.PolicemenInfoResponse;
import org.group05.police.DTO.PolicemenInfoUpdateRequest;

import java.util.List;

public interface PolicemenInfoMapper {
    public PolicemenInfoResponse getPolicemanInfo(String policemenID,SqlSession sqlSession);
    public List<PolicemenInfoResponse> getPolicemenInfo(PolicemenInfoRequest policemenInfoRequest,SqlSession sqlSession);
    public String deletePolicemen(String policemenID,SqlSession sqlSession);
    public String updatePolicemen(PolicemenInfoUpdateRequest policemenInfoUpdateRequest, SqlSession sqlSession);
}

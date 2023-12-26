package org.group05.police.DAO.mapper;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.PolicemenInfoRequest;
import org.group05.police.DTO.PolicemenInfoResponse;

import java.util.List;

public interface PolicemenInfoMapper {
    public List<PolicemenInfoResponse> getPolicemenInfo(PolicemenInfoRequest policemenInfoRequest,SqlSession sqlSession);
}

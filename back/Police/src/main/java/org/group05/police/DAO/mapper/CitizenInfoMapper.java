package org.group05.police.DAO.mapper;
import org.apache.ibatis.session.SqlSession;
import org.group05.police.DTO.CitizenInfoResponse;
import org.group05.police.DTO.CitizenInfoRequest;

import java.util.List;

public interface CitizenInfoMapper {
    public List<CitizenInfoResponse> getCitizens(String citizenID,String citizenName,String citizenSex,String citizenAddress, SqlSession sqlSession);
    public String addCitizen(CitizenInfoRequest citizenInfo,SqlSession sqlSession);
    public String deleteCitizen(String citizenID,SqlSession sqlSession);
    public String updateCitizen(CitizenInfoRequest citizenInfo,SqlSession sqlSession);
}

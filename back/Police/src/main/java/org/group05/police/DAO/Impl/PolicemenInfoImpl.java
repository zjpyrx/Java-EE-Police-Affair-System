package org.group05.police.DAO.Impl;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.mapper.PolicemenInfoMapper;
import org.group05.police.DTO.PolicemenInfoRequest;
import org.group05.police.DTO.PolicemenInfoResponse;
import org.group05.police.DTO.PolicemenInfoUpdateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolicemenInfoImpl implements PolicemenInfoMapper {
    public List<PolicemenInfoResponse> getPolicemenInfo(PolicemenInfoRequest policemenInfoRequest, SqlSession sqlSession){
        Map<String, String> params = new HashMap<>();
        params.put("policemenID", policemenInfoRequest.getPolicemenID());
        params.put("policemenName", policemenInfoRequest.getPolicemenName());
        params.put("policemenIDNum", policemenInfoRequest.getPolicemenIDNum());
        params.put("policemenYear", policemenInfoRequest.getPolicemenYear());
        params.put("policemenSex", policemenInfoRequest.getPolicemenSex());
        params.put("policemenNation", policemenInfoRequest.getPolicemenNation());
        params.put("policemenStatus", policemenInfoRequest.getPolicemenStatus());
        params.put("policemenPosition", policemenInfoRequest.getPolicemenPosition());
        List<PolicemenInfoResponse> res=sqlSession.selectList("PolicemenInfoMapper.getPolicemenInfo", params);
        System.out.println(res);
        return res;
    }
    public String deletePolicemen(String policemenID,SqlSession sqlSession){
        int rowsAffected = sqlSession.delete("PolicemenInfoMapper.deletePolicemen", policemenID);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            sqlSession.commit();
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("删除错误！");
            return "删除错误！";
        }
    }
    public String updatePolicemen(PolicemenInfoUpdateRequest policemenInfoUpdateRequest, SqlSession sqlSession){
        Map<String, Object> params = new HashMap<>();
        params.put("policemenID", policemenInfoUpdateRequest.getPolicemenID());
        params.put("policemenName", policemenInfoUpdateRequest.getPolicemenName());
        params.put("policemenIDNum", policemenInfoUpdateRequest.getPolicemenIDNum());
        params.put("policemenYear", policemenInfoUpdateRequest.getPolicemenYear());
        params.put("policemenMonth", policemenInfoUpdateRequest.getPolicemenMonth());
        params.put("policemenDay", policemenInfoUpdateRequest.getPolicemenDay());
        params.put("policemenSex", policemenInfoUpdateRequest.getPolicemenSex());
        params.put("policemenNation", policemenInfoUpdateRequest.getPolicemenNation());
        params.put("myPolicemenID", policemenInfoUpdateRequest.getMyPolicemenID());
        params.put("policemenPhone", policemenInfoUpdateRequest.getPolicemenPhone());
        params.put("policemenEmail", policemenInfoUpdateRequest.getPolicemenEmail());
        int rowsAffected = sqlSession.update("updatePolicemen", params);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            sqlSession.commit();
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("更新错误！");
            return "更新错误！";
        }
    }
}

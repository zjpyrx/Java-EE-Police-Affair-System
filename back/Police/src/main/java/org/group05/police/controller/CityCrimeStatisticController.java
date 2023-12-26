package org.group05.police.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.group05.police.DAO.Impl.CityCrimeInfoImpl;
import org.group05.police.DTO.CityCrimeInfo;
import org.group05.police.tools.SessionFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CityCrimeStatisticController {
    @GetMapping("api/CityCrimeInfo")
    public ResponseEntity<List<CityCrimeInfo>> GetCityCrimeInfo(@RequestParam String CityName){
        SqlSession sqlSession = SessionFactory.getSqlSession();
        if (sqlSession == null){
            return null;
        }
        System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]");
        System.out.println(CityName);

        List<CityCrimeInfo> cityCrimeInfoList = new ArrayList<>();
        CityCrimeInfoImpl cityCrimeInfoImpl=new CityCrimeInfoImpl();

        List<String> districtNames = cityCrimeInfoImpl.GetDistrictName(CityName,sqlSession);
        System.out.println(districtNames);
        List<Integer> districtPopulations = cityCrimeInfoImpl.GetDistrictPopulations(districtNames,sqlSession);
        System.out.println(districtPopulations);
        List<Integer> districtCrimes = cityCrimeInfoImpl.GetDistrictCrimeNums(districtNames,sqlSession);
        System.out.println(districtCrimes);
        List<List<Object>> crimeTypes = cityCrimeInfoImpl.GetDistrictCrimeType(districtNames,sqlSession);
        System.out.println(crimeTypes);
        List<Map<String, Object>> crimeTimes = cityCrimeInfoImpl.GetDistrictCrimeTime(districtNames,sqlSession);
        System.out.println(crimeTimes);

        for (int i = 0; i < districtNames.size(); i++) {
            CityCrimeInfo info = new CityCrimeInfo();
            info.setDistrictName(districtNames.get(i));
            info.setPopulation(districtPopulations.get(i));
            info.setCrimeNum(districtCrimes.get(i));

            //List<Integer> crimeTypeStat = new ArrayList<>();
            // Add crime type statistics to crimeTypeStat...
            info.setCrimeTypeStatistic(crimeTypes.get(i));
            info.setDistrictCrimeTimeStatistic(crimeTimes.get(i));
            cityCrimeInfoList.add(info);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cityCrimeInfoList);
    }
}

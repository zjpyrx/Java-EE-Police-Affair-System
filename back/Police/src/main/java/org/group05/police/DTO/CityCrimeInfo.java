package org.group05.police.DTO;

import java.util.List;
import java.util.Map;

public class CityCrimeInfo {
    private String districtName;
    private int population;
    private int crimeNum;
    private List<Object> crimeTypeStatistic;
    private Map<String, Object> districtCrimeTimeStatistic;

    // Getter and Setter for districtName
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName+"区";
    }

    // Getter and Setter for population
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    // Getter and Setter for crimeNum
    public int getCrimeNum() {
        return crimeNum;
    }

    public void setCrimeNum(int crimeNum) {
        this.crimeNum = crimeNum;
    }

    // Getter and Setter for crimeTypeStatistic
    public List<Object> getCrimeTypeStatistic() {
        return crimeTypeStatistic;
    }

    public void setCrimeTypeStatistic(List<Object> crimeTypeStatistic) {
        this.crimeTypeStatistic = crimeTypeStatistic;
    }

    // Getter and Setter for districtCrimeTimeStatistic
    public Map<String, Object> getDistrictCrimeTimeStatistic() {
        return districtCrimeTimeStatistic;
    }

    public void setDistrictCrimeTimeStatistic(Map<String, Object> districtCrimeTimeStatistic) {
        this.districtCrimeTimeStatistic = districtCrimeTimeStatistic;
    }
}

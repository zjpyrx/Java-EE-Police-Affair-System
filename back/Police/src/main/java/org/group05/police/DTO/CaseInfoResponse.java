package org.group05.police.DTO;

public class CaseInfoResponse {

    public CaseInfoResponse(String caseID, String caseType, String status, String registerTime, String address, String ranking) {
        this.caseID = caseID;
        this.caseType = caseType;
        this.status = status;
        this.registerTime = registerTime;
        this.address = address;
        this.ranking = ranking;
    }

    public String getCaseID() {
        return this.caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getCaseType() {
        return this.caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRanking() {
        return this.ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
    private String caseID;
    private String caseType;
    private String status;
    private String registerTime;
    private String address;
    private String ranking;

}

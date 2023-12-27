package org.group05.police.DTO;

public class DetailCaseInfoResponse {

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCaseT() {
        return this.caseT;
    }

    public void setCaseT(String caseT) {
        this.caseT = caseT;
    }
    public String name;
    public String id;
    public String gender;
    public String caseT;
}

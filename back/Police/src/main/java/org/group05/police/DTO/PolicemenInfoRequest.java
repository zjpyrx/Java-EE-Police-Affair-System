package org.group05.police.DTO;

public class PolicemenInfoRequest {
    private String policemenID;
    private String policemenName;
    private String policemenIDNum;
    private String policemenYear;
    private String policemenSex;
    private String policemenNation;
    private String policemenStatus;
    private String policemenPosition;

    public String getPolicemenID() {
        return policemenID;
    }

    public String getPolicemenName() {
        return policemenName;
    }

    public String getPolicemenIDNum() {
        return policemenIDNum;
    }

    public String getPolicemenYear() {
        return policemenYear;
    }

    public String getPolicemenSex() {
        return policemenSex;
    }

    public String getPolicemenNation() {
        return policemenNation;
    }

    public String getPolicemenStatus() {
        return policemenStatus;
    }

    public String getPolicemenPosition() {
        return policemenPosition;
    }
    public void setPolicemenID(String policemenID) {
        this.policemenID = policemenID;
    }

    public void setPolicemenName(String policemenName) {
        this.policemenName = policemenName;
    }

    public void setPolicemenIDNum(String policemenIDNum) {
        this.policemenIDNum = policemenIDNum;
    }

    public void setPolicemenYear(String policemenYear) {
        this.policemenYear = policemenYear;
    }

    public void setPolicemenSex(String policemenSex) {
        this.policemenSex = policemenSex;
    }

    public void setPolicemenNation(String policemenNation) {
        this.policemenNation = policemenNation;
    }

    public void setPolicemenStatus(String policemenStatus) {
        this.policemenStatus = policemenStatus;
    }

    public void setPolicemenPosition(String policemenPosition) {
        this.policemenPosition = policemenPosition;
    }
}

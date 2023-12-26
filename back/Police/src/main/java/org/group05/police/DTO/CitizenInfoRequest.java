package org.group05.police.DTO;

public class CitizenInfoRequest {
    private String citizenID;
    private String citizenName;
    private String citizenSex;
    private String citizenAddress;
    private String motherID;
    private String fatherID;

    public String getCitizenID() {
        return citizenID;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public String getCitizenAddress() {
        return citizenAddress;
    }

    public String getCitizenSex() {
        return citizenSex;
    }

    public String getMotherID() {
        return motherID;
    }

    public String getFatherID() {
        return fatherID;
    }
}

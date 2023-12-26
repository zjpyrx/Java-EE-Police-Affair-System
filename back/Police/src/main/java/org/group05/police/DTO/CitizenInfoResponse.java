package org.group05.police.DTO;

public class CitizenInfoResponse {
    private String id;
    private String name;
    private String sex;
    private String address;
    private String motherid;
    private String fatherid;
    private boolean success;
    public  String message;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getMotherid() {
        return motherid;
    }

    public String getFatherid() {
        return fatherid;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }
    public void setMotherid(String motherid) {
        this.motherid = motherid;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


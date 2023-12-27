package org.group05.police.DTO;

public class PolicemenInfoResponse {
    private String pid;
    private String name;
    private String idn;
    private String birthday;
    private String gender;
    private String nation;
    private String phone;
    private String email;
    private String status;
    private String position;
    private String pic;

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getIdn() {
        return idn;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getNation() {
        return nation;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

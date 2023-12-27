package org.group05.police.DTO;

import java.util.ArrayList;
import java.util.List;

public class Info {
    private String name;
    private String gender;
    private String ID;
    private ArrayList<String> crimeType;

    public Info(){
        name = "";
        gender = "";
        ID = "";
        crimeType = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCrimeType(ArrayList<String> crimeType) {
        this.crimeType = crimeType;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getID() {
        return ID;
    }

    public List<String> getCrimeType() {
        return crimeType;
    }
}

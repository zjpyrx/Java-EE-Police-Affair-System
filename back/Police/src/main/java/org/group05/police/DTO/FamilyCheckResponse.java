package org.group05.police.DTO;

import java.util.ArrayList;
import java.util.List;

public class FamilyCheckResponse {
    public ArrayList<Info> people;// 每个人
    public ArrayList<String> relationship;// 每个人和中心人的关系
    public FamilyCheckResponse(){
        people = new ArrayList<>();
        relationship = new ArrayList<>();
    }

}

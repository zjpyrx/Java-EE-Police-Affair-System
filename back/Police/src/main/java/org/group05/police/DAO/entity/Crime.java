package org.group05.police.DAO.entity;

public class Crime {
    private String ID_num; // 对应 citizen.ID_num
    private String citizen_name; // 对应 citizen_name (可以根据 Java 命名规范修改为 camelCase)
    private String gender;
    private String case_type; // 对应 case_type

    // 可能需要添加对应字段的 Getter 和 Setter 方法
    // 省略其他方法...

    // 示例 Getter 和 Setter 方法
    public String getID_num() {
        return ID_num;
    }

    public void setID_num(String ID_num) {
        this.ID_num = ID_num;
    }

    public String getCitizenName() {
        return citizen_name;
    }

    public void setCitizenName(String citizenName) {
        this.citizen_name = citizenName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCaseType() {
        return case_type;
    }

    public void setCaseType(String caseType) {
        this.case_type = caseType;
    }
}

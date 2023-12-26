package org.group05.police.DTO;

public class ValidationRequest {
    private String queryType;
    private String queryContent;
    
    public String getQueryType() {
        return this.queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryContent() {
        return this.queryContent;
    }

    public void setQueryContent(String queryContent) {
        this.queryContent = queryContent;
    }

}
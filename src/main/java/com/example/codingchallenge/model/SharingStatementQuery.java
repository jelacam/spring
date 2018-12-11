package com.example.codingchallenge.model;

import java.util.List;

public class SharingStatementQuery {
    private String attribute;
    private String relation;
    private String value;
    private String sharingOrgId;

    public SharingStatementQuery(String attribute, String relation, String value, String sharingOrgId) {
        this.attribute = attribute;
        this.relation = relation;
        this.value = value;
        this.sharingOrgId = sharingOrgId;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getRelation() {
        return relation;
    }

    public String getValue() {
        return value;
    }

    public String getSharingOrgId() {
        return sharingOrgId;
    }
}

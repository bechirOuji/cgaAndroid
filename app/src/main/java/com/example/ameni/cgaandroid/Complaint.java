package com.example.ameni.cgaandroid;

import java.io.Serializable;

/**
 * Created by bechir23 on 28/11/2017.
 */

public class Complaint implements Serializable {
    private int complaintId;
    private String description;
    private String response;

    public Complaint(int complaintId, String description, String response) {
        this.complaintId = complaintId;
        this.description = description;
        this.response = response;
    }

    public Complaint(String description, String response) {
        this.description = description;
        this.response = response;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}

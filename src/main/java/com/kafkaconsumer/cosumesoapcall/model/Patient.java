package com.kafkaconsumer.cosumesoapcall.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @JsonProperty(value = "PatientID")
    private Integer patientId;
    private Integer id;
    private String patientlist;

    public String getPatientlist() {
        return patientlist;
    }

    public void setPatientlist(String patientlist) {
        this.patientlist = patientlist;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", id=" + id +
                '}';
    }
}

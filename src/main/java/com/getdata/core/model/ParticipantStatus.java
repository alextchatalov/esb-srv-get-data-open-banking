package com.getdata.core.model;

public enum ParticipantStatus {

    ACTIVE,
    INACTIVE;

    public static ParticipantStatus convert(String status) {
        if (status == null) {
            System.out.printf("teste");
        }
        return "Active".equals(status) ? ACTIVE : INACTIVE;
    }
}

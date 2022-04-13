package com.getdata.exception.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionMessage {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}

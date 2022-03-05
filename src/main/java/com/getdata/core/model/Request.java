package com.getdata.core.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Request {

    private Category category;
    private String url;
    private String version;
    private Participant participant;
    @EqualsAndHashCode.Exclude
    private LocalDateTime lastRequest;

}

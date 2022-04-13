package com.getdata.exception;

import lombok.Getter;

@Getter
public class MapperException extends RuntimeException {

    public MapperException(final String errorMessage) {
        super(errorMessage);
    }
}

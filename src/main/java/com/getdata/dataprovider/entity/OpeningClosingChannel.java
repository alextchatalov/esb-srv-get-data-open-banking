package com.getdata.dataprovider.entity;

public enum OpeningClosingChannel {

    DEPENDENCIAS_PROPRIAS("DEPENDENCIAS PROPRIAS"),
    CORRESPONDENTES_BANCARIOS("CORRESPONDENTES BANCARIOS"),
    INTERNET_BANKING("INTERNET BANKING"),
    MOBILE_BANKING("MOBILE BANKING"),
    CENTRAL_TELEFONICA("CENTRAL TELEFONICA"),
    CHAT("CHAT"),
    OUTROS("OUTROS");

    private String description;

    OpeningClosingChannel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OpeningClosingChannel getByDescription(String description) {
        for (OpeningClosingChannel channel : OpeningClosingChannel.values()) {
            if (channel.getDescription().equals(description)) {
                return channel;
            }
        }
        return null;
    }
}

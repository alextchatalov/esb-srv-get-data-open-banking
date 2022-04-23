package com.getdata.dataprovider.entity;

public enum Interval {

    FAIXA_1("1_FAIXA"),
    FAIXA_2("2_FAIXA"),
    FAIXA_3("3_FAIXA"),
    FAIXA_4("4_FAIXA");

    private String original;

    private Interval(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }

    public static Interval getByOriginal(String original) {
        for (Interval interval : Interval.values()) {
            if (interval.getOriginal().equals(original)) {
                return interval;
            }
        }
        return null;
    }
}

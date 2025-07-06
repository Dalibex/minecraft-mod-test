package com.dalibex.trinket;

public enum TrinketType {
    CANDLE("candle"),
    SUSPICIOUS_SUBSTANCE("suspicious_substance");

    private final String id;

    TrinketType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

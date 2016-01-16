package com.fireball1725.firecore.util;

public class ColorString {
    private String message;
    private int color;

    public ColorString(String message, int color) {
        this.message = message;
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public int getColor() {
        return color;
    }
}

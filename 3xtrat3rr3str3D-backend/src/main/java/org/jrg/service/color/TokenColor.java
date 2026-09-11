package org.jrg.service.color;

public class TokenColor {

    public final int start;
    public final int length;
    public final String category;

    public TokenColor(int start, int length, String category) {
        this.start = start;
        this.length = length;
        this.category = category;
    }
}
package com.memory;

/**
 * Created by Johnpi on 04/10/2017.
 */
public class Carte {
    private String value;
    public int Y;
    public int X;
    public String screenValue;

    public Carte(int X, int Y, String value) {
        this.X = X;
        this.Y = Y;
        this.value = value;
        this.screenValue = "X";
    }

    public boolean isSame(String name2) {
        if (name2.equals(value)) {
            return true;
        }
        return false;
    }

    public void returned() {
        screenValue = value;
    }

    public void toto() {}
}

package com.memory;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static memory memory;
    public static void main(String[] args) {
        //memory = new memory();
       // memory.start();
        ControleurJeu controleurJeu = new ControleurJeu();
        controleurJeu.LauchGame();

    }

}
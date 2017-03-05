package com.company;

import java.util.Random;

public class Main {

    private static final char arrChars[] = {'a','b','c','d','e','f','g','h','i','j'};

    public static void main(String[] args) {
        BinaryTree<Integer, Character> tree = new BinaryTree<>();
        Random rnd = new Random();
        for (int i = 0; i < 25; ++i) {
            tree.add(50 - rnd.nextInt(100), arrChars[rnd.nextInt(arrChars.length)]);
        }

        String forwardStr = tree.toString(true, " ");
        String inverseStr = tree.toString(false, " ");
        System.out.println(inverseStr + "\n" + forwardStr);
    }
}

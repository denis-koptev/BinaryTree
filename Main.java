package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer, Double> tree = new BinaryTree<>();
        Random rnd = new Random();
        for (int i = 0; i < 25; ++i) {
            tree.add(50 - rnd.nextInt(100), rnd.nextDouble());
        }

        String forwardStr = tree.toString(true, " ");
        String inverseStr = tree.toString(false, " ");
        System.out.println(inverseStr + "\n" + forwardStr);
    }
}

package com.bears.algorithms;

import java.util.ArrayList;

public class ZeroOneKnapsackBruteForce {

    public static ArrayList<Integer> SolveKnapsack(int[] weights, int[] values, int[] indices, int capacity) {

        ArrayList<Integer> output = new ArrayList<Integer>();

        int permutation = 1;
        int[] permutationWord = new int[indices.length];

        int highestValue = 0;

        int currentCapacity = 0;
        int currentValue = 0;
        ArrayList<Integer> currentItems = new ArrayList<Integer>();

        while (GenerateNextPermutation(permutationWord, permutation)) {

            currentCapacity = 0;
            currentValue = 0;
            currentItems.clear();

            for (int i = 0; i < permutationWord.length; i++) {
                if (permutationWord[i] == 1) {
                    currentCapacity += weights[i];
                    currentValue += values[i];
                    currentItems.add(indices[i]);
                }
            }
            if (currentCapacity <= capacity && currentValue > highestValue) {
                highestValue = currentValue;

                output.clear();
                output.addAll(currentItems);
            }
            permutation++;
        }

        return output;
    }

    private static boolean GenerateNextPermutation(int[] permutationWord, int iteration) {

        //check to see if we have finished
        int doneValue = (int) (Math.pow(2, permutationWord.length) - 1);
        if (iteration > doneValue) {
            return false;
        } else {
            int length = permutationWord.length - 1;
            for (int i = 0; i <= length; i++) {
                boolean flipped = (iteration & (1 << i)) != 0;
                if (flipped) {
                    permutationWord[length - i] = 1;
                } else {
                    permutationWord[length - i] = 0;
                }
            }
        }
        return true;
    }
}

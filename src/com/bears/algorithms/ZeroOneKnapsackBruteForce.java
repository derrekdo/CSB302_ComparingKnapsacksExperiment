package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

import java.util.ArrayList;

public class ZeroOneKnapsackBruteForce implements IKnapsackSolver {
    String name = "01 Brute Force";

    public String getSolverName() {
        return name;
    }

    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        int permutation = 1;
        int[] permutationWord = new int[pairings.length];

        int highestValue = 0;

        int currentCapacity = 0;
        int currentValue = 0;
        ArrayList<Integer> currentItems = new ArrayList<Integer>();
        ArrayList<Integer> bestItems = new ArrayList<Integer>();

        while (GenerateNextPermutation(permutationWord, permutation)) {

            currentCapacity = 0;
            currentValue = 0;
            currentItems.clear();

            for (int i = 0; i < permutationWord.length; i++) {
                if (permutationWord[i] == 1) {
                    currentCapacity += pairings[i].getWeight();
                    currentValue += pairings[i].getProfit();
                    currentItems.add(i + 1);
                }
            }
            if (currentCapacity <= weightLimit && currentValue > highestValue) {

                highestValue = currentValue;
                bestItems.clear();
                bestItems.addAll(currentItems);
            }
            permutation++;
        }

        for (int index : bestItems) {
            output.addPairing(pairings[index - 1]);
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

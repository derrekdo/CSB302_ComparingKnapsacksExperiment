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

    /**
     * Brute force approach to 0-1 knapsack. creates permutations of items and compares weights until
     * all permutations are exhausted and the highest value combination of items is found.
     *
     * @param weightLimit the capacity of the knapsack
     * @param pairings    a weight/value object for each item.
     * @return A Knapsack Result object with the best Pair objects
     */
    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output;

        int iteration = 1;
        int[] permutationWord = new int[pairings.length];

        int highestValue = 0;

        int currentCapacity = 0;
        int currentValue = 0;
        ArrayList<Integer> currentItems = new ArrayList<Integer>();
        ArrayList<Integer> bestItems = new ArrayList<Integer>();

        // check to see if we have finished all permutations and if not continue checking next against the highest
        while (GenerateNextPermutation(permutationWord, iteration)) {

            //reset current values for the next iteration
            currentCapacity = 0;
            currentValue = 0;
            currentItems.clear();

            /*
             Go through the word and check to see if each bit is a 1 or a 0. if it's a 1 then add the corresponding
             item to our 'current' knapsack. The position of the 'bit' in the word array also corresponds to item index.
             I was going to change this part to use an actual integer and bitmask but I kind of like the mechanical
             nature of using the array and the GenerateNext function.
            */
            for (int i = 0; i < permutationWord.length; i++) {
                if (permutationWord[i] == 1) {
                    currentCapacity += pairings[i].getWeight();
                    currentValue += pairings[i].getProfit();
                    currentItems.add(i + 1);
                }
            }//if the current iteration is better than the highest recorded then replace it
            if (currentCapacity <= weightLimit && currentValue > highestValue) {

                highestValue = currentValue;
                bestItems.clear();
                bestItems.addAll(currentItems);
            }
            iteration++; // increment the iterator so we can exit/continue the loop
        }

        //add the best pairings to the output
        int totalWeight = 0;
        int totalValue = 0;
        ArrayList<Integer> weights = new ArrayList<Integer>();
        for (int index : bestItems) {

            totalWeight += pairings[index - 1].getWeight();
            totalValue += pairings[index - 1].getProfit();
            weights.add(pairings[index - 1].getWeight());
        }
        output = new KnapsackResult(totalValue, totalWeight, weights);

        return output;
    }// O(2^n) because permutations = 2 ^ number of items

    /**
     * helper function for getting the next permutation for the brute force algorithm
     * Takes an iteration value as an integer and converts it into a 'bits' in an array.
     *
     * @param permutationWord the array of 'bits' to be processed in the main function
     * @param iteration       the current permutation as an integer
     * @return true if the incoming iteration value is smaller than the 2 ^ permutation array length
     */
    private static boolean GenerateNextPermutation(int[] permutationWord, int iteration) {

        //check to see if we have finished
        int doneValue = (int) (Math.pow(2, permutationWord.length) - 1);
        if (iteration > doneValue) {
            return false;
        } else {
            int length = permutationWord.length - 1;
            for (int i = 0; i <= length; i++) {
                //I think this part can be done with ternary but this approach is clearer in my opinion
                boolean flipped = (iteration & (1 << i)) != 0;
                if (flipped) {
                    permutationWord[length - i] = 1;
                } else {
                    permutationWord[length - i] = 0;
                }
            }
        }
        return true;
    }//O(n)
}

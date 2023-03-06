package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

import java.util.ArrayList;

public class FractionalKnapsackBruteForce implements IKnapsackSolver {
    String name = "Fractional Brute Force";
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

        ArrayList<Integer> finalWeights = new ArrayList<>();
        int iteration = 1;
        int[] permutationWord = new int[pairings.length];

        double highestValue = 0;

        double currentCapacity = 0;
        double currentValue = 0;
        ArrayList<Integer> currentWeights = new ArrayList<Integer>();

        // check to see if we have finished all permutations and if not continue checking next against the highest
        while (GenerateNextPermutation(permutationWord, iteration)) {

            //reset current values for the next iteration
            currentCapacity = 0;
            currentValue = 0;
            currentWeights.clear();

            /*
             Go through the word and check to see if each bit is a 1 or a 0. if it's a 1 then add the corresponding
             item to our 'current' knapsack. The position of the 'bit' in the word array also corresponds to item index.
             I was going to change this part to use an actual integer and bitmask but I kind of like the mechanical
             nature of using the array and the GenerateNext function.
            */
            for (int i = 0; i < permutationWord.length; i++) {
                if (permutationWord[i] == 1) {

                    if (currentCapacity + pairings[i].getWeight() <= weightLimit) {
                        currentCapacity += pairings[i].getWeight();
                        currentValue += pairings[i].getProfit();
                        currentWeights.add(pairings[i].getWeight());
                    }else if (currentCapacity < weightLimit){
                        double weightDifference = weightLimit - currentCapacity;
                        double ratio  = Math.round(((double)pairings[i].getProfit() / (double)pairings[i].getWeight())*100)/100.0;
                        currentValue += weightDifference * ratio;
                        currentCapacity += weightDifference;
                        currentWeights.add(pairings[i].getWeight());
                    }
                }
            }
            if (currentCapacity <= weightLimit && currentValue > highestValue) {

                highestValue = currentValue;
                finalWeights.clear();
                finalWeights.addAll(currentWeights);
            }
            iteration++; // increment the iterator so we can exit/continue the loop
        }

        return new KnapsackResult(highestValue, currentCapacity, finalWeights);
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
    }
}

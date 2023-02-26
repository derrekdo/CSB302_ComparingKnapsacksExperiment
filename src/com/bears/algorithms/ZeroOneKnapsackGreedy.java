package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;


public class ZeroOneKnapsackGreedy implements IKnapsackSolver {
    String name = "01 Greedy";

    public String getSolverName() {
        return name;
    }

    /**
     * Greedy approach to 01 knapsack. Sorts items by value so that we know were the most valuable are then takes them
     * until the knapsack is full.
     *
     * @param weightLimit the capacity of the knapsack
     * @param pairings    an array of item weight+value pairs
     * @return A knapsack result with weight+value pairs
     */
    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        int currentCapacity = weightLimit;

        //sort items by value
        SortItems(pairings);

        //go through and find the most valuable that we can fit into teh knapsack
        for (int i = pairings.length - 1; i >= 0; i--) {
            if (pairings[i].getWeight() <= currentCapacity) {
                output.addPairing(pairings[i]);
                currentCapacity -= pairings[i].getWeight();
            }
            if (currentCapacity == 0) {
                break;
            }
        }

        return output;
    }//O(n^2) because we use selection sort

    //sorts the items so that we can find the highest values ones in the solver function
    private void SortItems(Pair[] pairings) {

        //outer loop through the entire array
        for (int i = 0; i < pairings.length; i++) {

            //set up a variable for tracking the index of the smallest index we find in the inner loop
            int smallestIndex = i;

            // innner loop through the rest of the array.
            for (int j = i + 1; j < pairings.length; j++) {
                if (pairings[j].getProfit() < pairings[smallestIndex].getProfit()) { // if the inner loop finds a smaller value update the smallest tracker
                    smallestIndex = j;
                }
            }

            //after the inner loop swap the value at the outer loop's index with the smallest we found with the inner loop
            Pair tempPair = pairings[i];
            pairings[i] = pairings[smallestIndex];
            pairings[smallestIndex] = tempPair;
        }
    }//O(n^2) selection sort
}

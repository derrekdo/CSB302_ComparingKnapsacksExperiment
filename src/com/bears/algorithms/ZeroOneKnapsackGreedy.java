package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

public class ZeroOneKnapsackGreedy implements IKnapsackSolver {
    String name = "01 Greedy";

    public String getSolverName() {
        return name;
    }

    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        int currentCapacity = weightLimit;

        SortItems(pairings);

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
    }

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
    }
}

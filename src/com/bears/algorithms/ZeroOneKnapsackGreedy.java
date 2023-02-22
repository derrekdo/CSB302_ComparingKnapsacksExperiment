package com.bears.algorithms;

import java.util.ArrayList;

public class ZeroOneKnapsackGreedy {

    public static ArrayList<Integer> SolveKnapsack(int[] weights, int[] values, int[] indices, int capacity) {

        ArrayList<Integer> output = new ArrayList<Integer>();

        int currentCapacity = capacity;

        SortItems(weights, values, indices);
        
        for (int i = values.length - 1; i >= 0; i--) {
            if (weights[i] <= currentCapacity) {
                output.add(indices[i]);
                currentCapacity -= weights[i];
            }
            if (currentCapacity == 0) {
                break;
            }
        }

        return output;
    }

    //sorts the values in descending order. also reorganizes the weights to match the ordering of the values
    private static void SortItems(int[] weights, int[] values, int[] indices) {

        //outer loop through the entire array
        for (int i = 0; i < values.length; i++) {

            //set up a variable for tracking the index of the smallest index we find in the inner loop
            int smallestIndex = i;

            // innner loop through the rest of the array.
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[smallestIndex]) { // if the inner loop finds a smaller value update the smallest tracker
                    smallestIndex = j;
                }
            }

            //after the inner loop swap the value at the outer loop's index with the smallest we found with the inner loop
            int tempIndex = indices[i];
            int tempValue = values[i];
            int tempWeight = weights[i];

            indices[i] = indices[smallestIndex];
            values[i] = values[smallestIndex];
            weights[i] = weights[smallestIndex];

            indices[smallestIndex] = tempIndex;
            values[smallestIndex] = tempValue;
            weights[smallestIndex] = tempWeight;
        }
    }
}

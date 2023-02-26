package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

public class ZeroOneKnapsackDynamicProgramming implements IKnapsackSolver {
    String name = "01 Dynamic Programming";

    public String getSolverName() {
        return name;
    }

    /**
     * Dynamic programming solution for 01 knapsack. makes a table of weight and items then traces through it to find
     * the optimal combination
     *
     * @param weightLimit the capacity of the knapsack
     * @param pairings    weight/value combination object for each item.
     * @return A knapsack result object which contains the best set of pair objects
     */
    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        int[][] dpTable = makeDPTable(pairings, weightLimit);
        output = traverseDPKnapSackTable(dpTable, pairings);

        return output;
    }

    /**
     * Helper function for the solver function. Makes a table following the process outlined in lecture
     *
     * @param pairings the array of pair objects(wieght/value of each item)
     * @param capacity the maximum weight we can put into the knapsack
     * @return a 2D array of weight combos to be processed in another helper function
     */
    private int[][] makeDPTable(Pair[] pairings, int capacity) {

        int items = pairings.length;

        //make a table to account for all possible solutions
        // fill the first row with 0s
        int[][] table = new int[items + 1][capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            table[0][j] = 0;
        }

        /* go through each cell and do the equation discussed in class:
            for each cell in the table[i][j] process a left and right equation;
                left:   table[i - 1, j]
                right:  table[i-1][j - weights[i-1]] + values[i-1]
            pick the larger between the two values and set it to [i][j].
            If the right hand side is OOB pick the values 'above' the i,j coordinate for that element
         */
        for (int i = 1; i <= items; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - pairings[i - 1].getWeight() < 0) { //check before hand if we are OOB on that right hand
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(
                            table[i - 1][j],
                            table[i - 1][j - pairings[i - 1].getWeight()] + pairings[i - 1].getProfit());
                }
            }
        }
        return table;
    }// O(nw) n = number of items. w = capacity

    /**
     * Helper function for the main solver equation. Takes the table generated in the other helper function and traces
     * through it to find the optimal item combination.
     *
     * @param table    a 2D array of item weights and total weights
     * @param pairings a array of item weight/value pairings
     * @return A knapsack result object with the best set of items
     */
    private KnapsackResult traverseDPKnapSackTable(int[][] table, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        // indefinite loop because we don't know how many items can be in the best combination
        boolean filling = true;
        //start at the 'lower right' corner of the table
        int columnPosition = table.length - 1;
        int rowPosition = table[0].length - 1;

        /**
         * Check the value above us in the table. If it's different than the value at our current cell add the current
         * row's item to the knapsack and move over one column. Repeat until we reach the top or left side of the table.
         */
        while (filling) {

            if (table[columnPosition][rowPosition] == table[columnPosition - 1][rowPosition]) {
                columnPosition--;
            } else {
                int weight = pairings[columnPosition - 1].getWeight();
                output.addPairing(pairings[columnPosition - 1]);
                columnPosition--;
                rowPosition = rowPosition - weight;

            }
            if (rowPosition == 0 || columnPosition == 0) {
                filling = false;
            }
        }
        return output;
    }
}

package com.bears.algorithms;

import java.util.ArrayList;

public class ZeroOneKnapsackDynamicProgramming {

    public static ArrayList<Integer> SolveKnapsack(int[] weights, int[] values, int capacity) {

        ArrayList<Integer> output = new ArrayList<Integer>();

        int[][] dpTable = makeDPTable(weights, values, capacity);
        output = traverseDPKnapSackTable(dpTable, weights);

        return output;
    }

    private static int[][] makeDPTable(int[] weights, int[] values, int capacity) {

        int items = weights.length;

        //make a table to account for all possible solutions
        // fill the first row with 0s
        int[][] table = new int[items + 1][capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            table[0][j] = 0;
        }

        /* go through each cell and do the equation:
            table[i][j] = Math.max(
                left:   table[i - 1, j] ,
                right:  table[i-1][j - weights[i-1]] + values[i-1]
                )
            if the right hand side is OOB pick the values 'above' the i,j coordinate for that element
         */
        for (int i = 1; i <= items; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - weights[i - 1] < 0) { //check before hand if we are OOB on that right hand
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(
                            table[i - 1][j],
                            table[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return table;
    }

    private static ArrayList<Integer> traverseDPKnapSackTable(int[][] table, int[] weights) {

        ArrayList<Integer> output = new ArrayList<Integer>();

        boolean filling = true;
        int columnPosition = table.length - 1;
        int rowPosition = table[0].length - 1;

        while (filling) {

            if (table[columnPosition][rowPosition] == table[columnPosition - 1][rowPosition]) {
                columnPosition--;
            } else {
                int weight = weights[columnPosition - 1];
                output.add(columnPosition);
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

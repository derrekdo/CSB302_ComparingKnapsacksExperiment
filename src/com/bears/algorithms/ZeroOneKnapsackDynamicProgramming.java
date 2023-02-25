package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

public class ZeroOneKnapsackDynamicProgramming implements IKnapsackSolver {
    String name = "01 Dynamic Programming";

    public String getSolverName() {
        return name;
    }

    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        int[][] dpTable = makeDPTable(pairings, weightLimit);
        output = traverseDPKnapSackTable(dpTable, pairings);

        return output;
    }

    private int[][] makeDPTable(Pair[] pairings, int capacity) {

        int items = pairings.length;

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
    }

    private static KnapsackResult traverseDPKnapSackTable(int[][] table, Pair[] pairings) {

        KnapsackResult output = new KnapsackResult();

        boolean filling = true;
        int columnPosition = table.length - 1;
        int rowPosition = table[0].length - 1;

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

package com.bears.projectrunner;

import com.bears.algorithms.ZeroOneKnapsackBruteForce;
import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

public class Main {
    public static void main(String[] args) {

        debugTestInterface();

        /*
        System.out.println("Hello world!");
        FileReader fileReader = new FileReader("input");
        List<TestCondition> testConditions = fileReader.getTestConditions();
        testConditions.sort((a, b) -> Integer.compare(a.getKnapsackNumber(), b.getKnapsackNumber()));
        TestCondition conditon1 = testConditions.get(0);
        System.out.println(conditon1);
        */
    }

    public static void debugTestInterface() {

        // basic testing using values from assignment 2
        int[] weights = {3, 2, 6, 4, 1};
        int[] values = {16, 17, 19, 22, 11};
        Pair[] pairing = new Pair[weights.length];

        for (int i = 0; i < pairing.length; i++) {
            pairing[i] = new Pair(weights[i], values[i]);

        }

        int capacity = 9;

        IKnapsackSolver solver = new ZeroOneKnapsackBruteForce();
        KnapsackResult result = solver.solveKnapsackProblem(capacity, pairing);
        result.printResult();
    }
}

package com.bears.projectrunner;

import com.bears.algorithms.ZeroOneKnapsackBruteForce;
import com.bears.algorithms.ZeroOneKnapsackDynamicProgramming;
import com.bears.algorithms.ZeroOneKnapsackGreedy;
import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        debugTests();
        /*
        System.out.println("Hello world!");
        FileReader fileReader = new FileReader("input");
        List<TestCondition> testConditions = fileReader.getTestConditions();
        testConditions.sort((a, b) -> Integer.compare(a.getKnapsackNumber(), b.getKnapsackNumber()));
        TestCondition conditon1 = testConditions.get(0);
        System.out.println(conditon1);
        */
    }

    public static void debugTests() {

        ArrayList<IKnapsackSolver> solverList = new ArrayList<IKnapsackSolver>();
        solverList.add(new ZeroOneKnapsackBruteForce());
        solverList.add(new ZeroOneKnapsackDynamicProgramming());
        solverList.add(new ZeroOneKnapsackGreedy());

        // basic testing using values from assignment 2
        int[] weights = {3, 2, 6, 4, 1};
        int[] values = {16, 17, 19, 22, 11};
        Pair[] pairing = new Pair[weights.length];

        for (int i = 0; i < pairing.length; i++) {
            pairing[i] = new Pair(weights[i], values[i]);
        }

        int capacity = 9;

        for (IKnapsackSolver solver : solverList) {
            System.out.println("Solving..." + solver.getSolverName());
            KnapsackResult result = solver.solveKnapsackProblem(capacity, pairing);
            result.printResult();
        }

        // more advanced test using larger item + weight sets from the project csv file
        int[] fileV = {106, 92, 90, 142, 85, 128, 124, 30, 126, 26, 179, 109, 84, 87, 13};
        int[] fileW = {65, 9, 121, 174, 188, 148, 57, 148, 79, 140, 104, 57, 67, 87, 46};
        Pair[] filePairs = new Pair[fileW.length];

        for (int i = 0; i < filePairs.length; i++) {
            filePairs[i] = new Pair(fileW[i], fileV[i]);
        }
        int fileCap = 100;

        for (IKnapsackSolver solver : solverList) {
            System.out.println("Solving..." + solver.getSolverName());
            KnapsackResult result = solver.solveKnapsackProblem(fileCap, filePairs);
            result.printResult();
        }

        //test to make sure greedy is greedy and brute force/DP pick better results
        int[] greedyV = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 20};
        int[] greedyW = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10};
        Pair[] greedyPairs = new Pair[greedyV.length];

        for (int i = 0; i < greedyPairs.length; i++) {
            greedyPairs[i] = new Pair(greedyW[i], greedyV[i]);
        }
        int greedyCap = 10;

        for (IKnapsackSolver solver : solverList) {
            System.out.println("Solving..." + solver.getSolverName());
            KnapsackResult result = solver.solveKnapsackProblem(greedyCap, greedyPairs);
            result.printResult();
        }
    }
}

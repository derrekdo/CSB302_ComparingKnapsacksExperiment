package JUnitTests;

import com.bears.algorithms.ZeroOneKnapsackBruteForce;
import com.bears.algorithms.ZeroOneKnapsackDynamicProgramming;
import com.bears.algorithms.ZeroOneKnapsackGreedy;
import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class KnapsackTests {
    int totWeight = 0;
    int totValue = 0;
    int[] weights = {3, 2, 6, 4, 1};
    int[] values = {16, 17, 19, 22, 11};
    Pair[] pairing = new Pair[weights.length];
    int zeroOneExpectedWeight1 = 9;
    int getZeroOneExpectedProfit1 = 55;

    int[] fileW = {106, 92, 90, 142, 85, 128, 124, 30, 126, 26, 179, 109, 84, 87, 13};
    int[] fileV = {65, 9, 121, 174, 188, 148, 57, 148, 79, 140, 104, 57, 67, 87, 46};
    Pair[] filePairs = new Pair[fileW.length];
    int zeroOneExpectedWeight2 = 69;
    int getZeroOneExpectedProfit2 = 334;

    int[] greedyV = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 20};
    int[] greedyW = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10};
    Pair[] greedyPairs = new Pair[greedyV.length];
    int zeroOneExpectedWeight3 = 10;
    int getZeroOneExpectedProfit3 = 50;

    public void createPairs(int[] weights, int[] values, Pair[] pairings){
        for (int i = 0; i < pairings.length; i++){
            pairings[i] = new Pair(weights[i], values[i]);
        }

    }

    @Test
    void test01BruteForce(){
        ArrayList<IKnapsackSolver> solverList = new ArrayList<>();
        solverList.add(new ZeroOneKnapsackBruteForce());

        createPairs(weights, values, pairing);

        System.out.println("\n----------- 01 Brute Force Test -----------");

        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(9, pairing);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %d      Actual Weight: %d", zeroOneExpectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit1, totValue);
        assertEquals(zeroOneExpectedWeight1, totWeight);
        assertEquals(getZeroOneExpectedProfit1, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(100, filePairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 2:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %d    Actual Profit: %d", getZeroOneExpectedProfit2, totValue);
        assertEquals(zeroOneExpectedWeight2, totWeight);
        assertEquals(getZeroOneExpectedProfit2, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(10, greedyPairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 3:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit3, totValue);
        assertEquals(zeroOneExpectedWeight3, totWeight);
        assertEquals(getZeroOneExpectedProfit3, totValue);
    }

    @Test
    void test01Dynamic(){
        ArrayList<IKnapsackSolver> solverList = new ArrayList<>();
        solverList.add(new ZeroOneKnapsackDynamicProgramming());

        createPairs(weights, values, pairing);

        System.out.println("\n----------- 01 Dynamic Programming Test -----------");

        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(9, pairing);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %d      Actual Weight: %d", zeroOneExpectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit1, totValue);
        assertEquals(zeroOneExpectedWeight1, totWeight);
        assertEquals(getZeroOneExpectedProfit1, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(100, filePairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 2:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %d    Actual Profit: %d", getZeroOneExpectedProfit2, totValue);
        assertEquals(zeroOneExpectedWeight2, totWeight);
        assertEquals(getZeroOneExpectedProfit2, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(10, greedyPairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 3:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit3, totValue);
        assertEquals(zeroOneExpectedWeight3, totWeight);
        assertEquals(getZeroOneExpectedProfit3, totValue);
    }

    @Test
    void test01Greedy(){
        ArrayList<IKnapsackSolver> solverList = new ArrayList<>();
        solverList.add(new ZeroOneKnapsackGreedy());

        createPairs(weights, values, pairing);

        System.out.println("\n----------- 01 Greedy Test -----------");

        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(9, pairing);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %d      Actual Weight: %d", zeroOneExpectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit1, totValue);
        assertEquals(zeroOneExpectedWeight1, totWeight);
        assertEquals(getZeroOneExpectedProfit1, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(100, filePairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 2:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", 98, totWeight);
        System.out.printf("\nExpected Profit: %d    Actual Profit: %d", 234, totValue);
        assertEquals(98, totWeight);
        assertEquals(234, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(10, greedyPairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 3:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", 20, totValue);
        assertEquals(zeroOneExpectedWeight3, totWeight);
        assertEquals(20, totValue);
    }

    @Test
    void testFracGreedy(){
        ArrayList<IKnapsackSolver> solverList = new ArrayList<>();
        solverList.add(new ZeroOneKnapsackBruteForce());

        createPairs(weights, values, pairing);

        System.out.println("----------- Fractional Greedy Test -----------");

        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(9, pairing);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }

        System.out.println("Test 1:");
        //if KnapsackResult values can hold doubles the expect is: 10.66
        System.out.printf("Expected Weight: %d      Actual Weight: %d", zeroOneExpectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit1, totValue);
        assertEquals(zeroOneExpectedWeight1, totWeight);
        assertEquals(getZeroOneExpectedProfit1, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(100, filePairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 2:");
        //if KnapsackResult values can hold doubles the expect is: 68.51
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %d    Actual Profit: %d", getZeroOneExpectedProfit2, totValue);
        assertEquals(zeroOneExpectedWeight2, totWeight);
        assertEquals(getZeroOneExpectedProfit2, totValue);

        totValue = 0;
        totWeight = 0;
        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            KnapsackResult result = solver.solveKnapsackProblem(10, greedyPairs);
            totValue += result.getProfit();
            totWeight += result.getKnapsackWeight();
        }
        System.out.println("\nTest 3:");
        System.out.printf("Expected Weight: %d     Actual Weight: %d", zeroOneExpectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %d     Actual Profit: %d", getZeroOneExpectedProfit3, totValue);
        assertEquals(zeroOneExpectedWeight3, totWeight);
        assertEquals(getZeroOneExpectedProfit3, totValue);
    }
}

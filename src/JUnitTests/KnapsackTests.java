package JUnitTests;

import com.bears.algorithms.*;
import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class KnapsackTests {
    ArrayList<IKnapsackSolver> solverList = new ArrayList<>();
    KnapsackResult result = null;

    double totWeight = 0;
    double totProfit = 0;

    int[] weights = {3, 2, 6, 4, 1};
    int[] values = {16, 17, 19, 22, 11};
    Pair[] pairing = new Pair[weights.length];
    double expectedWeight1 = 9;
    double zeroOneExpectedProfit1 = 55;
    double fracExpectedProfit1 = 60.66;

    int[] fileW = {106, 92, 90, 142, 85, 128, 124, 30, 126, 26, 179, 109, 84, 87, 13};
    int[] fileV = {65, 9, 121, 174, 188, 148, 57, 148, 79, 140, 104, 57, 67, 87, 46};
    Pair[] filePairs = new Pair[fileW.length];
    double zeroOneExpectedWeight2 = 69;
    double zeroOneExpectedProfit2 = 334;
    double fracExpectedWeight2 = 100;
    double fracExpectedProfit2 = 402.51;

    int[] greedyV = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 20};
    int[] greedyW = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10};
    Pair[] greedyPairs = new Pair[greedyV.length];
    double expectedWeight3 = 10;
    double expectedProfit3 = 50;

    private void createPairs(int[] weights, int[] values, Pair[] pairings){
        for (int i = 0; i < pairings.length; i++){
            pairings[i] = new Pair(weights[i], values[i]);
        }
    }

    @Test
    void test01BruteForce(){
        System.out.println("\n----------- START | 01 Brute Force Test -----------");

        solverList.add(new ZeroOneKnapsackBruteForce());
        createPairs(weights, values, pairing);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(9, pairing);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %.2f      Actual Weight: %.2f", expectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", zeroOneExpectedProfit1, totProfit);
        assertEquals(expectedWeight1, totWeight);
        assertEquals(zeroOneExpectedProfit1, totProfit);

        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(100, filePairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 2:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", zeroOneExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %.2f    Actual Profit: %.2f", zeroOneExpectedProfit2, totProfit);
        assertEquals(zeroOneExpectedWeight2, totWeight);
        assertEquals(zeroOneExpectedProfit2, totProfit);

        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(10, greedyPairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 3:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", expectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", expectedProfit3, totProfit);
        assertEquals(expectedWeight3, totWeight);
        assertEquals(expectedProfit3, totProfit);

        System.out.println("\n----------- END | 01 Brute Force Test -----------");
    }

    @Test
    void test01Dynamic(){
        System.out.println("\n----------- START | 01 Dynamic Test -----------");

        solverList.add(new ZeroOneKnapsackDynamicProgramming());
        createPairs(weights, values, pairing);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(9, pairing);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %.2f      Actual Weight: %.2f", expectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", zeroOneExpectedProfit1, totProfit);
        assertEquals(expectedWeight1, totWeight);
        assertEquals(zeroOneExpectedProfit1, totProfit);

        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(100, filePairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 2:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", zeroOneExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %.2f    Actual Profit: %.2f", zeroOneExpectedProfit2, totProfit);
        assertEquals(zeroOneExpectedWeight2, totWeight);
        assertEquals(zeroOneExpectedProfit2, totProfit);

        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(10, greedyPairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 3:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", expectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", expectedProfit3, totProfit);
        assertEquals(expectedWeight3, totWeight);
        assertEquals(expectedProfit3, totProfit);

        System.out.println("\n----------- END | 01 Dynamic Test -----------");
    }

    @Test
    void test01Greedy(){
        System.out.println("\n----------- START | 01 Greedy Test -----------");

        solverList.add(new ZeroOneKnapsackGreedy());
        createPairs(weights, values, pairing);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(9, pairing);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %.2f      Actual Weight: %.2f", expectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", zeroOneExpectedProfit1, totProfit);
        assertEquals(expectedWeight1, totWeight);
        assertEquals(zeroOneExpectedProfit1, totProfit);

        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(100, filePairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 2:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", 98.0, totWeight);
        System.out.printf("\nExpected Profit: %.2f    Actual Profit: %.2f", 234.0, totProfit);
        assertEquals(98.0, totWeight);
        assertEquals(234.0, totProfit);

        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(10, greedyPairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 3:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", 10.0, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", 20.0, totProfit);
        assertEquals(10.0, totWeight);
        assertEquals(20.0, totProfit);

        System.out.println("\n----------- END | 01 Greedy Test -----------");
    }

    @Test
    void testFracGreedy(){
        System.out.println("\n----------- START | Fractional Greedy Test -----------");

        solverList.add(new FractionalKnapsackGreedy());
        createPairs(weights, values, pairing);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(9, pairing);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %.2f      Actual Weight: %.2f", expectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", fracExpectedProfit1, totProfit);
        assertEquals(expectedWeight1, totWeight);
        assertEquals(fracExpectedProfit1, totProfit);

        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(100, filePairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 2:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", fracExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %.2f    Actual Profit: %.2f", fracExpectedProfit2, totProfit);
        assertEquals(fracExpectedWeight2, totWeight);
        assertEquals(fracExpectedProfit2, totProfit);

        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(10, greedyPairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 3:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", expectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", expectedProfit3, totProfit);
        assertEquals(expectedWeight3, totWeight);
        assertEquals(expectedProfit3, totProfit);

        System.out.println("\n----------- END | Fractional Greedy Test -----------");
    }

    @Test
    void testFracBruteForce(){
        System.out.println("\n----------- START | Fractional Brute Force Test -----------");

        solverList.add(new FractionalKnapsackBruteForce());
        createPairs(weights, values, pairing);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(9, pairing);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("Test 1:");
        System.out.printf("Expected Weight: %.2f      Actual Weight: %.2f", expectedWeight1, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", fracExpectedProfit1, totProfit);
        assertEquals(expectedWeight1, totWeight);
        assertEquals(fracExpectedProfit1, totProfit);

        createPairs(fileW, fileV, filePairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(100, filePairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 2:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", fracExpectedWeight2, totWeight);
        System.out.printf("\nExpected Profit: %.2f    Actual Profit: %.2f", fracExpectedProfit2, totProfit);
        assertEquals(fracExpectedWeight2, totWeight);
        assertEquals(fracExpectedProfit2, totProfit);

        createPairs(greedyW, greedyV, greedyPairs);
        for(IKnapsackSolver solver : solverList){
            result = solver.solveKnapsackProblem(10, greedyPairs);
            totWeight = result.getWeight();
            totProfit = result.getProfit();
        }

        System.out.println("\n\nTest 3:");
        System.out.printf("Expected Weight: %.2f     Actual Weight: %.2f", expectedWeight3, totWeight);
        System.out.printf("\nExpected Profit: %.2f     Actual Profit: %.2f", expectedProfit3, totProfit);
        assertEquals(expectedWeight3, totWeight);
        assertEquals(expectedProfit3, totProfit);

        System.out.println("\n----------- END | Fractional Greedy Test -----------");
    }
}

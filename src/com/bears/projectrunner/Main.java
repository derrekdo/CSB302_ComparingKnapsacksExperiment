package com.bears.projectrunner;

import com.bears.algorithms.*;
import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.FileReader;
import com.bears.util.Pair;
import com.bears.util.StopWatch;
import com.bears.util.TestCondition;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<IKnapsackSolver> solverList = new ArrayList<IKnapsackSolver>();
        solverList.add(new ZeroOneKnapsackBruteForce());
        solverList.add(new ZeroOneKnapsackDynamicProgramming());
        solverList.add(new ZeroOneKnapsackGreedy());
        solverList.add(new FractionalKnapsackBruteForce());
        solverList.add(new FractionalKnapsackGreedy());

        FileReader reader = new FileReader("input");
        List<TestCondition> conditions = reader.getTestConditions();

        for (IKnapsackSolver solver : solverList){
            for (TestCondition condition : conditions){
                StopWatch stopWatch = new StopWatch();
                System.out.println(solver.getSolverName());
                System.out.println(condition);
                stopWatch.start();
                KnapsackResult result = solver.solveKnapsackProblem(condition.getWeightCapacity(), condition.getWeightProfitMapping());
                stopWatch.end();
                result.printResult();
                System.out.println("Time is " + stopWatch.getDuration() + " microseconds");
                System.out.println();

            }
        }

    }


}

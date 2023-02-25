package com.bears.model;

import com.bears.util.Pair;

public interface IKnapsackSolver {

    String getSolverName();

    KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings);
}

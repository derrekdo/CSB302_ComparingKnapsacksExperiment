package com.bears.model;

import java.util.Map;
import com.bears.util.Pair;

public interface IKnapsackSolver {

    KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings);

}

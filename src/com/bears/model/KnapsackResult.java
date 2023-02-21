package com.bears.model;
import com.bears.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class KnapsackResult {
    private final int knapsackWeight;
    private final int profit;
    private List<Pair> knapsack;

    public KnapsackResult(int knapsackWeight, int profit){
        this.knapsackWeight = knapsackWeight;
        this.profit = profit;
        this.knapsack = new LinkedList<>();
    }

    public int getKnapsackWeight() {
        return knapsackWeight;
    }

    public int getProfit() {
        return profit;
    }


}

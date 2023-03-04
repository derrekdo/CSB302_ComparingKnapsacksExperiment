package com.bears.model;

import com.bears.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KnapsackResult {

    double profit;
    double weight;

    List<Integer> knapsack;

    public KnapsackResult(){
        profit = 0.0;
        weight = 0.0;
        knapsack = new ArrayList<>();
    }

    public double getProfit() {
        return profit;
    }

    public double getWeight() {
        return weight;
    }

    public List<Integer> getKnapsack(){
        return knapsack;
    }




}

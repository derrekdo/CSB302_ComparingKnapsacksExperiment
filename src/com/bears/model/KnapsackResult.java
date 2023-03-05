package com.bears.model;

import com.bears.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KnapsackResult {

    double profit;
    double weight;
    List<Integer> knapsack;

    public KnapsackResult(double profit, double weight, List<Integer> knapsack) {
        this.profit = profit;
        this.weight = weight;
        this.knapsack = knapsack;
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

    @Override
    public String toString() {
        return "KnapsackResult{" +
                "profit=" + profit +
                ", weight=" + weight +
                ", knapsack=" + knapsack +
                '}';
    }

    public void printResult(){
        System.out.println(this);
    }
}

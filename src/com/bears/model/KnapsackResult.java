package com.bears.model;

import com.bears.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class KnapsackResult {

    private List<Pair> knapsack;

    public KnapsackResult() {
        this.knapsack = new LinkedList<>();
    }

    public int getKnapsackWeight() {
        return knapsack.stream().map(Pair::getWeight).mapToInt(Integer::intValue).sum();
    }

    public int getProfit() {
        return knapsack.stream().map(Pair::getProfit).mapToInt(Integer::intValue).sum();
    }

    public void addPairing(int weight, int profit) {
        knapsack.add(new Pair(weight, profit));
    }

    public void addPairing(Pair pair) {
        knapsack.add(pair);
    }

    public void printResult() {
        for (Pair pair : knapsack) {
            System.out.println(pair.toString());
        }
    }

}

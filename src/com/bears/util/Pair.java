package com.bears.util;

public class Pair {
    private final int weight;
    private final int profit;

    public Pair(int weight, int profit){
        this.weight = weight;
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }
    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "weight=" + weight +
                ", profit=" + profit +
                '}';
    }
}

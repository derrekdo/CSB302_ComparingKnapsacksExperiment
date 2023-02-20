package com.bears.projectrunner;

import java.util.HashMap;

public class TestCondition {
    private final int weightCapacity;
    private final int knapsackNumber;
    private HashMap<Integer, Integer> weightProfitMapping;


    public TestCondition(int knapsackNumber, int weightCapacity) {
        weightProfitMapping = new HashMap<>();
        this.knapsackNumber = knapsackNumber;
        this.weightCapacity = weightCapacity;
    }



    public void enterWeightProfitMapping(int weight, int profit){
        this.weightProfitMapping.put(weight, profit);
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }


    public HashMap<Integer, Integer> getWeightProfitMapping() {
        return weightProfitMapping;
    }

    @Override
    public String toString() {
        return "TestCondition{" +
                "weightCapacity=" + weightCapacity +
                ", knapsackNumber=" + knapsackNumber +
                ", weightProfitMapping=" + weightProfitMapping +
                '}';
    }
}

package com.bears.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.bears.util.Pair;

public class TestCondition {
    private final int weightCapacity;


    private final int knapsackNumber;
    protected Pair[] weightProfitMapping;


    public TestCondition(int knapsackNumber, int weightCapacity, int size) {
        this.knapsackNumber = knapsackNumber;
        this.weightCapacity = weightCapacity;
        weightProfitMapping = new Pair[size];
    }


    public int getWeightCapacity() {
        return weightCapacity;
    }


    public Pair[] getWeightProfitMapping() {
        return weightProfitMapping;
    }
    public int getKnapsackNumber() {
        return knapsackNumber;
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

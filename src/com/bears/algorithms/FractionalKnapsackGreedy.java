package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;

public class FractionalKnapsackGreedy implements IKnapsackSolver {

    public String getSolverName() {
        return "Fractional Greedy";
    }

    /**
     * Calculates ratio of each item and sorts item based on the ratio
     * Takes items with the highest ratio until the next whole item cannot be taken
     * a fraction of the next highest ratio item will be taken in added to the total
     * @param weightLimit the max capacity of the items combined weight
     * @param pairings array of an items weight and value
     * @return item pairing that can be added
     */
    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {
        //will hold each pair
        KnapsackResult output = new KnapsackResult();
        int currentWeight = 0;
        int size = pairings.length;

        //TreeMap to hold the ratio's of each item in a descending order
        TreeMap<Double, Integer> set = new TreeMap<>();
        //loops through each pair and calculates the ratio and adds it to the treemap
        for(int i = 0; i < size; i++){
            double ratio  = Math.round(((double)pairings[i].getProfit() / (double)pairings[i].getWeight())*100)/100.0;
            set.put(ratio, i);
        }

        //iterator is then used to iterate through each item
        Iterator ratios = set.descendingKeySet().iterator();
        //checks if the capacity has been reached and there are any items left
        while(ratios.hasNext() && currentWeight != weightLimit){
            //holds the current ratio
            double current = (double) ratios.next();
            //finds the index of the item with the ratio
            int index = set.get(current);
            //gets the weight  of the item
            int itemWeight = pairings[index].getWeight();
            //checks if the next item exceeds the capacity
            if (currentWeight + itemWeight < weightLimit) {
                //if it does not exceed, the item can be taken
                currentWeight += itemWeight;
                output.addPairing(pairings[set.get(current)]);
            }else{
                //if it exceeds the capacity, only a portion of the item is taken
                //remaining capacity
                int remainingWeight = weightLimit - currentWeight;
                //the amount of the item that can be taken
                double value = current * remainingWeight;
                currentWeight += remainingWeight;
                output.addPairing(remainingWeight, (int)value);
            }
        }
        return output;
    }


}

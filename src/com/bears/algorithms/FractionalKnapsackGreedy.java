package com.bears.algorithms;

import com.bears.model.IKnapsackSolver;
import com.bears.model.KnapsackResult;
import com.bears.util.Pair;

import java.util.*;

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

        //Treeset to hold the ratio's of each item in a descending order
        TreeSet<Double> itemRatios = new TreeSet<>();
        //holds the ratios in an array list unsorted, to get the index of the first instance the ratio appears
        ArrayList<Double> ratioIndex = new ArrayList<>();
        //loops through each pair and calculates the ratio and adds it to a tree set and array list
        for(int i = 0; i < size; i++){
            double ratio  = Math.round(((double)pairings[i].getProfit() / (double)pairings[i].getWeight())*100)/100.0;
            itemRatios.add(ratio);
            ratioIndex.add(i, ratio);
        }

        //iterator is then used to iterate through each item
        Iterator ratios = itemRatios.descendingIterator();
        //checks if the capacity has been reached and there are any items left
        while(ratios.hasNext() && currentWeight != weightLimit){
            //holds the current ratio
            double current = (double) ratios.next();
            //if there are multiple items with the same ratio, as many will be taken w/o exceeding the capacity
            while(ratioIndex.contains(current) && currentWeight != weightLimit) {
                //finds the index of the item with the ratio
                int index = ratioIndex.indexOf(current);
                //gets the weight  of the item
                int itemWeight = pairings[index].getWeight();
                //checks if the next item exceeds the capacity
                if (currentWeight + itemWeight < weightLimit) {
                    //if it does not exceed, the item can be taken
                    currentWeight += itemWeight;
                    output.addPairing(pairings[index]);
                } else {
                    //if it exceeds the capacity, only a portion of the item is taken
                    //remaining capacity
                    int remainingWeight = weightLimit - currentWeight;
                    //the amount of the item that can be taken
                    double value = current * remainingWeight;
                    currentWeight += remainingWeight;
                    output.addPairing(remainingWeight, (int) value);
                }
                //the ratio at current index is not removed but set to null
                //prevents the same item from being taken if there are multiple items with the same ratio
                ratioIndex.set(index, null);
            }
        }
        return output;
    }
}

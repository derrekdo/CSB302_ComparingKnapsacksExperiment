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
     * @return total profit and total weights
     */
    public KnapsackResult solveKnapsackProblem(int weightLimit, Pair[] pairings) {
        //holds weights of items taken
        List<Integer> weights = new LinkedList<>();
        //treemap to hold ratios with corresponding pair
        TreeMap<Double, LinkedList<Pair>> ratios = new TreeMap<>();
        int currentWeight = 0;
        double totProfit = 0;
        int size = pairings.length;

        //loops through each pair and calculates the ratio and adds it to a tree set and array list
        for(int i = 0; i < size; i++){
            double ratio  = Math.round(((double)pairings[i].getProfit() / (double)pairings[i].getWeight())*100)/100.0;
            if(!ratios.containsKey(ratio)) {
                ratios.put(ratio, new LinkedList<>());
            }
            ratios.get(ratio).add(pairings[i]);
        }

        Iterator ratioIterator = ratios.descendingKeySet().iterator();

        //checks if capacity has been reached and if there are items remaining
        while(ratioIterator.hasNext() && currentWeight != weightLimit){
            //holds the current ratio
            double current = (double) ratioIterator.next();
            //if there are multiple items that share the same ratio, as many will be taken w/o exceeding the capacity
            while(!ratios.get(current).isEmpty() && currentWeight != weightLimit){
                //variables to hold the profit and weight of the current item
                double itemProfit = ratios.get(current).getFirst().getProfit();
                int itemWeight = ratios.get(current).getFirst().getWeight();
                //checks if adding the item will exceed the weight limit
                if(currentWeight + itemWeight < weightLimit){
                    //if it doesn't, add to the profits and weight to total
                    totProfit += itemProfit;
                    currentWeight += itemWeight;
                    weights.add(itemWeight);
                    //removes the item
                    ratios.get(current).removeFirst();
                }else{
                    //otherwise take a portion of the current item based on the remaining weight
                    int remainingWeight = weightLimit - currentWeight;
                    //calculates how much can be added
                    double profit = current * remainingWeight;
                    currentWeight += remainingWeight;
                    totProfit += profit;
                    weights.add(remainingWeight);
                }
            }
        }
        KnapsackResult output = new KnapsackResult(totProfit,currentWeight, weights);
        return output;
    }
}

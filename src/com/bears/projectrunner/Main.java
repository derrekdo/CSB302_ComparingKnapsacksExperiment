package com.bears.projectrunner;
import com.bears.util.FileReader;
import com.bears.util.TestCondition;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        FileReader fileReader = new FileReader("input");
        List<TestCondition> testConditions = fileReader.getTestConditions();
        testConditions.sort((a, b) -> Integer.compare(a.getKnapsackNumber(), b.getKnapsackNumber()));
        TestCondition conditon1 = testConditions.get(0);
        System.out.println(conditon1);



    }
}
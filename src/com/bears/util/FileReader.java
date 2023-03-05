package com.bears.util;
import java.io.File;
import java.util.*;

public class FileReader {

    private String inputDirectory;
    private List<TestCondition> testConditions;

    public FileReader(String inputDirectory){
        this.inputDirectory = inputDirectory;
        testConditions = new LinkedList<>();
        File[] files = new File(inputDirectory).listFiles(file -> file.getName().endsWith("csv"));

        Arrays.stream(files).filter(File::isFile).forEach(this::process);
    }

    public List<TestCondition> getTestConditions(){
        return testConditions;
    }

    private void process(File file){
        if (!file.isFile()){
            throw new IllegalArgumentException("Can only read a file");
        }

        int numberOfLines = 0;
        List<String> readInputs = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine() && numberOfLines < 3){
                String line = scanner.nextLine();
                readInputs.add(line);
                numberOfLines++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int knapsackNumber = Integer.parseInt(readInputs.get(0).split(",")[0]);
        int weightCapacity = Integer.parseInt(readInputs.get(0).split(",")[1]);

        int[] profitArray = Arrays.stream(readInputs.get(1).split(",")).filter(FileReader::isDigit).mapToInt(Integer::parseInt).toArray();
        int[] weightArray = Arrays.stream(readInputs.get(2).split(",")).filter(FileReader::isDigit).mapToInt(Integer::parseInt).toArray();

        TestCondition condition = new TestCondition(knapsackNumber, weightCapacity, profitArray.length);
        for (int i = 0; i < profitArray.length; i++){
            int profit = profitArray[i];
            int weight = weightArray[i];
            condition.weightProfitMapping[i] = new Pair(weight, profit);
        }

        testConditions.add(condition);
    }

    public static boolean isDigit(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer num = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        FileReader reader = new FileReader("input");
        List<TestCondition> l1 = reader.getTestConditions();
        for (TestCondition test: l1){
            Pair[] pairs = test.weightProfitMapping;
            for (Pair pair: pairs){
                System.out.println(pair.getWeight() + "---" + pair.getProfit());
            }
        }


    }



}

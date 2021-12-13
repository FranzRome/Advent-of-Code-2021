package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7 {

    public static void main(String[] args) {
        // Variables declaration
       ArrayList<Integer> input = new ArrayList<>();

        // Input loading
        try {
            File file = new File("data/day7.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] split = line.split(",");

                for(String s : split) {
                    input.add(Integer.parseInt(s));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        //System.out.println(input.toString());

        // Part one
        System.out.println("Part one: " + partOne(input));


        // Part two
        System.out.println("Part two: " + partTwo(input));


    }

    static int partOne(ArrayList<Integer> input){
        ArrayList<Integer> positions = (ArrayList<Integer>) input.clone();
        int median = median(positions);


        return calculateFuelCost1(positions, median);
    }

    static int partTwo(ArrayList<Integer> input){
        ArrayList<Integer> positions = (ArrayList<Integer>) input.clone();
        int mean = mean(positions);
        int maxPosition = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;

        for (int p: positions){
            maxPosition = Math.max(maxPosition, p);
        }

        for (int i=0; i<maxPosition; i++){
            result = Math.min(result, calculateFuelCost2(positions, i));
        }

        return result;
    }

    static int calculateFuelCost1(ArrayList<Integer> positions, int targetPosition){
        int cost=0;

        for(int p: positions){
            cost+= Math.abs(targetPosition-p);
        }

        return cost;
    }

    public static int calculateFuelCost2(ArrayList<Integer> positions, int targetPosition) {
        int result = 0;

        for(int p : positions) {
            int n = Math.abs(targetPosition-p);
            for (int i = n; i > 0; i--) {
                result += i;
            }
        }

        return result;
    }

    public static int median(ArrayList<Integer> list) {
        int middle = list.size()/2;
        Collections.sort(list);
        if (list.size()%2 == 1) {
            return list.get(middle);
        } else {
            return Math.round((list.get(middle-1) + list.get(middle-1)) / 2.0f);
        }
    }

    public static int mean(ArrayList<Integer> list) {
        float sum = 0;

        for(int e : list) {
            sum+=e;
        }
            return Math.round(sum / list.size());
    }
}
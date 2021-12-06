package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {

    public static void main(String[] args) {
        // Variables declaration
       ArrayList<Integer> input = new ArrayList<>();

        // Input loading
        try {
            File file = new File("data/day6.txt");
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

    // Calculate the total lantern fish in 80 days starting from input
    static int partOne(ArrayList<Integer> input){
        ArrayList<Integer> fishes = (ArrayList<Integer>) input.clone();

        for(int i=0; i<80; i++) {
            for (int j=0; j<fishes.size(); j++) {
                if(fishes.get(j) > 0){
                    fishes.set(j, (fishes.get(j)-1));
                } else {
                    fishes.set(j, 6);
                    fishes.add(9);
                }
            }

            // System.out.println("{" + i + "," + fishes.size() + "}");
        }

        return fishes.size();
    }

    // Calculate the total lantern fish in 256 days starting from input
    static long partTwo(ArrayList<Integer> input){
        long[] fishes = new long[9];
        long result = 0;

        for(int i=0; i<input.size(); i++){
            fishes[input.get(i)]++;
        }

        for(int i=0; i<256; i++) {
            long tmp = fishes[0];
            shiftLeft(fishes);
            fishes[6] += tmp;
        }

        for(int i=0; i<fishes.length; i++){
            result += fishes[i];
        }
        return(result);
    }

    static long[] shiftLeft(long[] array){
        for(int i=0; i<array.length-1; i++){
            if(array[i] < 0|| array[array.length-1] < 0) System.out.println("OVERFLOW");
            long tmp = array[i+1];
            array[i+1] = array[i];
            array[i] = tmp;
        }
        return array;
    }
}
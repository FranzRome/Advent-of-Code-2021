/*
 * --- Day 2: Dive! ---
 */

package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        // Variables declaration
        ArrayList<String[]> input = new ArrayList<>();
        int horizontalPosition = 0, depth = 0;

        // Input loading
        try {
            File file = new File("data/day2.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] s = reader.nextLine().split(" ");
                input.add(s);
                //System.out.println(s[0]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        // Part one
        for(String[] s : input){
            switch (s[0]){
                case "forward":
                    horizontalPosition += Integer.parseInt(s[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(s[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(s[1]);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + s[0]);
            }
        }

        System.out.println("Part one: " + horizontalPosition*depth);


        // Part two
        int aim = 0;
        horizontalPosition = 0;
        depth = 0;

        for(String[] s : input){
            switch (s[0]){
                case "forward":
                    int value = Integer.parseInt(s[1]);
                    horizontalPosition += value;
                    depth += value * aim;
                    break;
                case "up":
                    aim -= Integer.parseInt(s[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(s[1]);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + s[0]);
            }
            //System.out.println("Aim: " + aim + "  Horizontal: " + horizontalPosition + "  Depth: " + depth);
        }

        System.out.println("Part two: " + horizontalPosition*depth);


    }
}

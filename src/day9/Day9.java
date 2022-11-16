/*
 * --- Day 9: Smoke Basin ---
 */

package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        // Variables declaration
        ArrayList<int[]> input = new ArrayList<>();

        // Input loading
        try {
            File file = new File("data/day9.txt");
            Scanner reader = new Scanner(file);


            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] a = new int[line.length()];

                for(int i=0; i<line.length(); i++){
                    a[i] = Integer.parseInt("" + line.charAt(i));
                }

                input.add(a);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        // Part one
        System.out.println("Part one: " + partOne(input));


        // Part two
        System.out.println("Part two: " + partTwo(input));


    }

    static int partOne(ArrayList<int[]> input){
        int result = 0;

        for(int i=0; i<input.size(); i++){
            for(int j=0; j<input.get(i).length; j++){
                int currentValue = input.get(i)[j];
                if((i>0 && currentValue >= input.get(i-1)[j]) ||
                        (j>0 && currentValue >= input.get(i)[j-1]) ||
                        (j< input.get(i).length-1 && currentValue >= input.get(i)[j+1]) ||
                        (i< input.size()-1 && currentValue >= input.get(i+1)[j])){
                        continue;
                }
                else
                {
                    //System.out.println(i + " " + j + "  " + input.get(i)[j]);
                    result += input.get(i)[j] + 1;
                }
            }
        }

        return result;
    }

    static int partTwo(ArrayList<int[]> input){
        int result = 0;
        boolean [][] visited = new boolean [input.size()][input.get(0).length];
        ArrayList<Integer> sizes = new ArrayList<Integer>();

        for(int i=0; i<visited.length; i++){
            for(int j=0; j<visited[i].length; j++){
                visited[i][j] = false;
            }
        }

        for(int i=0; i<visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if(!visited[i][j]){
                    sizes.add(pondSize(input, visited, i, j));
                }
            }
        }

        Collections.sort(sizes);
        result = sizes.get(sizes.size() - 1) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 3);

        return result;
    }

    static int pondSize(ArrayList<int[]> map, boolean[][] visited, int i, int j){
        if(i >= 0 && j >= 0 && i < map.size() && j < map.get(i).length && !visited[i][j] && map.get(i)[j] < 9) {
            visited[i][j] = true;
            return 1 + pondSize(map, visited,i-1, j)
                     + pondSize(map, visited,i+1, j)
                     + pondSize(map, visited, i, j-1)
                     + pondSize(map, visited, i, j+1);
        } else {
            return 0;
        }
    }
}

package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {

    public static void main(String[] args) {
        // Variables declaration
       ArrayList<int[]> input = new ArrayList<>();
       int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        // Input loading
        try {
            File file = new File("data/day5.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] split = line.replaceAll("->", ",").replaceAll(" ", "").split(",");
                int[] nums = new int[4];

                for (int i=0; i<split.length; i++){
                    //System.out.println(split[i]);
                    nums[i] = Integer.parseInt(split[i]);
                   //System.out.println(nums[i]);

                    if(i%2 == 0){
                        if(nums[i] > maxX) maxX = nums[i];
                    } else{
                        if(nums[i] > maxY) maxY = nums[i];
                    }
                }

                //System.out.println(maxX);
                //System.out.println(maxY);

                input.add(nums);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        // Part one
        System.out.println("Part one: " + partOne(input, maxX, maxY));


        // Part two
        System.out.println("Part two: " + partTwo(input, maxX, maxY));


    }

    static int partOne(ArrayList<int[]> input, int maxX, int maxY){
        int [][] grid = new int[maxX+1][maxY+1];

        for(int i=0; i<grid.length; i++) {
            Arrays.fill(grid[i], 0);
        }

        for(int[] cords : input){
            if(cords[0] == cords[2] || cords[1] == cords[3]){
                for(int i = Math.min(cords[1], cords[3]); i <= Math.max(cords[1], cords[3]); i++){
                    for(int j = Math.min(cords[0], cords[2]); j <= Math.max(cords[0], cords[2]); j++){
                        grid[i][j]++;
                    }
                }
            }
        }

        //System.out.println(Arrays.deepToString(grid));

        return count(grid);
    }

    static int partTwo(ArrayList<int[]> input, int maxX, int maxY){
        int [][] grid = new int[maxX+1][maxY+1];

        for(int i=0; i<grid.length; i++) {
            Arrays.fill(grid[i], 0);
        }

        for(int[] cords : input){
            if(cords[0] == cords[2] || cords[1] == cords[3]){
                for(int i = Math.min(cords[1], cords[3]); i <= Math.max(cords[1], cords[3]); i++){
                    for(int j = Math.min(cords[0], cords[2]); j <= Math.max(cords[0], cords[2]); j++){
                        grid[i][j]++;
                    }
                }
            }
            else {
                int i = cords[1], j = cords[0];
                int dirX = cords[0] < cords[2] ? 1 : -1;
                int dirY = cords[1] < cords[3] ? 1 : -1;

                //System.out.println("Points: " + Arrays.toString(cords) + "   DirX:" + dirX + "   DirY: " + dirY);

                for (int c = Math.abs(cords[2] - cords[0]); c >= 0; c--) {
                    grid[i + c * dirY][j + c * dirX]++;
                }
            }
        }

        //System.out.println(Arrays.deepToString(grid));

        return count(grid);
    }

    // It counts values equals or major than 2 in a given grid
    static int count(int[][] grid){
        int result = 0;

        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] >= 2) result++;
            }
        }

        return result;
    }
}
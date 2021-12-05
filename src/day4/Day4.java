package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {

    public static void main(String[] args) {
        // Variables declaration
        ArrayList<Integer> tempNumbers = new ArrayList<>();
        ArrayList<Integer[][]> tempTables = new ArrayList<>();
        int[] numbers;
        Integer[][][] tables;
        boolean[][][] marks;
        boolean[] isWinningTable;
        int winCount = 0;

        int score = 0;

        // Input loading
        try {
            File file = new File("data/day4.txt");
            Scanner reader = new Scanner(file);

            for(String s : reader.nextLine().split(",")){
                tempNumbers.add(Integer.parseInt(s));
            }

            Integer[][] table = new Integer[5][5];
            int i = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // System.out.println(line);

                if(!line.trim().isEmpty()){
                    String[] split = line.trim().replaceAll("  ", " ").split(" ");
                    for(int j = 0; j < table[i].length; j++){
                        table[i][j] = Integer.parseInt(split[j]);
                    }

                    i++;
                }
                if(table[4][4] != null) {
                    tempTables.add(table);
                    table = new Integer[5][5];
                    i = 0;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        numbers = tempNumbers.stream().mapToInt(i->i).toArray();
        tables = tempTables.toArray(new Integer[tempTables.size()][tempTables.get(0).length][tempTables.get(0)[0].length]);
        marks = new boolean[tables.length][tables[0].length][tables[0][0].length];

        for(int i=0; i<marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                Arrays.fill(marks[i][j], false);
            }
        }

        // Part one
        outerLoop:
        for(int i=0; i<numbers.length; i++){
            /*System.out.println("\nMarks[2]:");
            for(int a=0; a<marks[2].length; a++) {
                for (int b = 0; b < marks[2][a].length; b++) {
                    System.out.print(marks[2][a][b] + "  ");
                }
                System.out.print("\n");
            }*/
            for(int j=0; j<tables.length; j++){
                for(int k=0; k<tables[j].length; k++){
                    for(int l=0; l<tables[j][k].length; l++){
                        if(numbers[i] == tables[j][k][l]){
                            marks[j][k][l] = true;
                        }
                        if (i > 3){
                            if(checkMarks(marks[j])){
                                score = calculateScore(tables[j], marks[j], numbers[i]);
                                /* System.out.println("Last number is: " + numbers[i]);
                                   System.out.println("Winning table is: " + j); */
                                break outerLoop;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Part one: " + score);


        // Part two
        score = 0;
        isWinningTable = new boolean[tables.length];
        Arrays.fill(isWinningTable, false);

        marks = new boolean[tables.length][tables[0].length][tables[0][0].length];

        for(int i=0; i<marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                Arrays.fill(marks[i][j], false);
            }
        }

        outerLoop:
        for(int i=0; i<numbers.length; i++){
            /*System.out.println("\nMarks[2]:");
            for(int a=0; a<marks[2].length; a++) {
                for (int b = 0; b < marks[2][a].length; b++) {
                    System.out.print(marks[2][a][b] + "  ");
                }
                System.out.print("\n");
            }*/
            innerLoop:
            for(int j=0; j<tables.length; j++){
                if(!isWinningTable[j]) {
                    for (int k = 0; k < tables[j].length; k++) {
                        for (int l = 0; l < tables[j][k].length; l++) {
                            if (numbers[i] == tables[j][k][l]) {
                                marks[j][k][l] = true;
                            }
                            if (i > 3) {
                                if (checkMarks(marks[j])) {
                                    winCount++;
                                    isWinningTable[j] = true;
                                    System.out.println("Table " + j + " win!");
                                    System.out.println("\nMarks["+ j + "]:");
                                    for(int a=0; a<marks[j].length; a++) {
                                        for (int b = 0; b < marks[j][a].length; b++) {
                                            System.out.print(marks[j][a][b] + "  ");
                                        }
                                        System.out.print("\n");
                                    }

                                    if (winCount == tables.length) {
                                        score = calculateScore(tables[j], marks[j], numbers[i]);
                                        System.out.println("Win count: " + winCount);
                                        System.out.println("Last winning table: " + j);
                                        System.out.println("Last number: " + numbers[i]);

                                        break outerLoop;
                                    }
                                    continue innerLoop;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Part two: " + score);


    }

    static boolean checkMarks(boolean[][] marks){
        boolean found = true;

        for(int i=0; i< marks.length; i++){
            found = true;
            for(int j=0; j< marks[i].length; j++){
                if(!marks[i][j]){
                    found = false;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        if(!found) {
            for (int i = 0; i < marks.length; i++) {
                found = true;
                for (int j = 0; j < marks[i].length; j++) {
                    if (!marks[j][i]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }

        return found;
    }

    static int calculateScore(Integer[][] table, boolean[][] marks, int lastNumber){
       int sum = 0;

        for(int i=0; i< table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(!marks[i][j]) {
                    sum += table[i][j];
                }
            }
        }

        return sum * lastNumber;
    }
}

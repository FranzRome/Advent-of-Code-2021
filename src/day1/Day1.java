package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day1 {

    public static void main(String[] args) {
        // Variables declaration
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        int result = 0;

        // Inputs loading
        try {
            File file = new File("data/day1.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                inputs.add(Integer.parseInt(reader.nextLine()));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        // Part one
        for(int i=1; i<inputs.size(); i++){
            if(inputs.get(i) > inputs.get(i-1)){
                result++;
            }
        }
        System.out.println("Part one: " + result);

        // Part two
        result = 0;

        for(int i=1; i<inputs.size() - 2; i++){
            int a = inputs.get(i - 1);
            int b = inputs.get(i);
            int c = inputs.get(i + 1);
            int d = inputs.get(i + 2);
            int bc = b+c;
            if((a+bc) < (bc+d)){
                result++;
            }
        }
        System.out.println("Part two: " + result);


    }
}

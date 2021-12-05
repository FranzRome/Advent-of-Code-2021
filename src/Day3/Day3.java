package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
        // Variables declaration
        ArrayList<String> input = new ArrayList<>();
        int gamma = 0, epsilon = 0;
        String gammaString = "", epsilonString="";

        // Input loading
        try {
            File file = new File("data/day3.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                input.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        // Part one
        int i = 0, zeroCount=0, oneCount=0;
        for(int j=0; j<input.get(i).length(); j++){
            zeroCount = 0;
            oneCount = 0;
            for(i=0; i<input.size(); i++){
                //System.out.println(i + "  " + j);
                char c = input.get(i).charAt(j);
                if(c == '0') zeroCount++;
                else oneCount++;
            }
            gammaString += (zeroCount > oneCount) ? "0" : "1";
            epsilonString += (zeroCount < oneCount) ? "0" : "1";
            i=0;
        }

        gamma = binaryToDecimal(gammaString);
        epsilon = binaryToDecimal(epsilonString);

        System.out.println("Part one: " + gamma*epsilon);


        // Part two
        ArrayList<String> oxygenGen = (ArrayList<String>) input.clone();
        ArrayList<String> co2Scrub = (ArrayList<String>)input.clone();

        i = 0;
        for (int j = 0; j < oxygenGen.get(i).length() && oxygenGen.size() > 1; j++) {
            zeroCount = 0;
            oneCount = 0;
            for (i = 0; i < oxygenGen.size(); i++) {
                //System.out.println(i + "  " + j);
                char c = oxygenGen.get(i).charAt(j);
                if (c == '0') zeroCount++;
                else oneCount++;
            }

            ArrayList<String> tempOxygenRate = new ArrayList<>();
            for (i = 0; i < oxygenGen.size(); i++) {
                if(zeroCount > oneCount && oxygenGen.get(i).charAt(j) == '0'){
                    tempOxygenRate.add(oxygenGen.get(i));
                }
                else if (zeroCount <= oneCount && oxygenGen.get(i).charAt(j) == '1'){
                    tempOxygenRate.add(oxygenGen.get(i));
                }
            }
            oxygenGen = (ArrayList<String>)tempOxygenRate.clone();
            i=0;
        }

        i=0;
        for (int j = 0; j < co2Scrub.get(i).length() && co2Scrub.size() > 1; j++) {
            zeroCount = 0;
            oneCount = 0;
            for (i = 0; i < co2Scrub.size(); i++) {
                //System.out.println(i + "  " + j);
                char c = co2Scrub.get(i).charAt(j);
                if (c == '0') zeroCount++;
                else oneCount++;
            }

            ArrayList<String> tempCo2Scrub = new ArrayList<>();
            for (i = 0; i < co2Scrub.size(); i++) {
                if(zeroCount <= oneCount && co2Scrub.get(i).charAt(j) == '0'){
                    tempCo2Scrub.add(co2Scrub.get(i));
                }
                else if (zeroCount > oneCount && co2Scrub.get(i).charAt(j) == '1'){
                    tempCo2Scrub.add(co2Scrub.get(i));
                }
            }
            co2Scrub = (ArrayList<String>)tempCo2Scrub.clone();
            i=0;
        }

        System.out.println("Part two: " + binaryToDecimal(oxygenGen.get(0))*binaryToDecimal(co2Scrub.get(0)));


    }

    static int binaryToDecimal(String s){
        int result = 0;
        int len = s.length();

        for(int i=0; i<len; i++){
            result += Math.pow(2, i) * Integer.parseInt("" + s.charAt(len - i -1));
        }

        return result;
    }
}

package com.company;

import java.util.*;

public class Main {

    private static int p;
    private static Scanner scan;
    private static List<List<Integer>> correctStringS;


    public static void main(String[] args) {

        scan = new Scanner(System.in);

        System.out.print("Enter the length of p: ");

        //get p from user.
        p  = Integer.parseInt(scan.nextLine());
        //check if its prime;
        if(isPrime(p) && p != 2){
            //if prime then
            //create a list of 1 with p as the length
            System.out.println(createStringS(p));
            ArrayList<Integer> stringS = createStringS(p);

            //divide the array in three parts xyz

            List<List<List<Integer>>> splitStringS = split(stringS);

            System.out.println(splitStringS);

            correctStringS = getCorrectSplitString(splitStringS);

            ArrayList<List<Integer>> finalStringS = new ArrayList<>();
            ArrayList<List<Integer>> finalStringS2 = new ArrayList<>();

            System.out.println();
            System.out.println("String successfully split in to xyz such: |xy| ≤ p and  |y| ≥ 1");
            System.out.println("String S after splitting: " + correctStringS);
            System.out.println();

            //get i from user
            int i = getI();

            if (i > 0){
                //double y in xyz i times.

                //creating new size value for string y^i
                double sq =  Math.pow(correctStringS.get(1).size(), i);

                //if the length of string y is 1:
                //and 1^ of any i is always 1,
                //the sq value is set to i coz instead of assigning 1,
                //1 ^ i = 1, appearing i times.
                if(sq == 1 ){
                    sq = i;
                }


                System.out.println();
                System.out.println("New length of substring Y is: " + sq);

                //Creating new string y
                ArrayList<Integer> stringY = new ArrayList<>();

                //adding elements if String y considering i.
                for (int r = 0; r < sq; r++){
                    stringY.add(1);
                }

                finalStringS.add(correctStringS.get(0));
                finalStringS.add(stringY);
                finalStringS.add(correctStringS.get(2));

                System.out.println();
                System.out.println("Substring y successfully updated such that: y^i ");
                System.out.println();
                System.out.println("New String S:   " + finalStringS);
                System.out.println();

                //check if new s is part of language(check if if length is prime)
                //if prime => then language id regular

                int sizeOfS = finalStringS.get(0).size() +
                        finalStringS.get(1).size() + finalStringS.get(2).size();

                if(isPrime(sizeOfS)){
                    System.out.println();
                    System.out.println("For i = :"+ i + ":| S ∈ L: Hence String can be pumped");
                }
                //if not => then language not regular
                else {
                    System.out.println("For i = :"+ i + ":| S  ∉  L: Hence String S can not be pumped. L is Not Regular");
                }
            }
            else{
                System.out.println("Wrong value for i: please try again");
            }

        }else {
            System.out.println("p such that string S^p must prime");
        }
    }

    public static boolean isPrime(int n) {
        return !new String(new char[n]).matches(".?|(..+?)\\1+");
    }

    public static ArrayList<Integer> createStringS(int p){
        int n = 0;
        ArrayList<Integer> stringS = new ArrayList<>();

        while(n < p){
            stringS.add(1);
            n++;
        }

        return stringS;
    }

    private static List<List<List<Integer>>> split(List<Integer> list) {

        if (3 > list.size())

            throw new IllegalArgumentException("Invalid number of groups: " + 3 +
                    " (list size: " + list.size() + ")");

        List<List<List<Integer>>> result = new ArrayList<>();

        split(list, 0, new List[3], 0, result);

        return result;
    }

    private static void split(List<Integer> list, int listIdx,
                              List<Integer>[] combo, int comboIdx,
                              List<List<List<Integer>>> result) {
        if (combo.length - comboIdx == 1) {
            combo[comboIdx] = list.subList(listIdx, list.size());
            result.add(new ArrayList<>(Arrays.asList(combo)));
        } else {
            for (int i = 0; i <= (list.size() - listIdx) - (combo.length - comboIdx); i++) {
                combo[comboIdx] = list.subList(listIdx, listIdx + 1 + i);
                split(list, listIdx + 1 + i, combo, comboIdx + 1, result);
            }
        }
    }

    public static List getCorrectSplitString(List<List<List<Integer>>> list){
        //assume the first list correct one
        List<List<Integer>> correctList = list.get(0);

        //it
        for(int m = 1; m < list.size(); m++){
                int lengthX, lengthY;

            lengthX = list.get(m).get(0).size();
            lengthY = list.get(m).get(1).size();
            // (1) |xy| ≤ n
            int sumXY = lengthX + lengthY;
            if ( sumXY <= p){
                // (2) |y| ≥ 1
                if ( lengthY > 0){
                    correctList = list.get(m);
                    //return correctList;
                }
            }
        }
        return correctList;
    }

    public static int getI() {
        System.out.print("Enter value of i such that x(y^i)z ∈ L for some i value: ");
        int n = Integer.parseInt(scan.nextLine());
        return n;
    }
}

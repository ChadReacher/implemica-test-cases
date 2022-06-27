package com.chadreacher.thirdtask;


import java.util.ArrayList;

public class ThirdTask {
    public static void main(String[] args) {
        int number = 100;
        findSumOfDigitsOfNumber(number);
    }

    /**
     * find sum of digits in given number
     * @param n
     */
    static void findSumOfDigitsOfNumber(int n) {
        if (n < 0) {
            return; // check for valid integer
        }
        ArrayList<Integer> record = new ArrayList<>(); // initialize arraylist which will consist of factorial's digits
        int digitSum = 0;
        record.add(1);
        for (int v = 2; v <= n; ++v) {
            multiply(record, v); // for each number from 2 to n we update arraylist
        }

        for (int i = record.size() - 1; i >= 0; --i) {
            digitSum += record.get(i); // sum all digits in list
        }
        System.out.println("\nDigit sum : " + digitSum); // display result
    }

    /**
     * Takes arraylist of numbers and next number in factorial sequence and updates list with multiplication given number with existing numbers
     * @param record
     * @param v
     */
    private static void multiply(ArrayList<Integer> record, int v) {
        int carry = 0; // stands for carry
        int product = 0; // stands for a number
        for (int i = 0; i < record.size(); ++i) { // for each already present number in list
            product = record.get(i) * v + carry; // we take it, multiply with an argument(v) and add carry to it
            record.set(i, product % 10); // as a result we set to list its last digit
            carry = product / 10; // set carry for the first digit of product
        }

        while (carry > 0) { // while carry > 0 we add each digit of it to list
            record.add(carry % 10);
            carry = carry / 10;
        }
    }
}

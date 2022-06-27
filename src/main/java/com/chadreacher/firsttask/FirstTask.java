package com.chadreacher.firsttask;

import java.util.Scanner;
import java.util.Stack;

public class FirstTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of open and close parentheses. If you want to stop entering examples - write 'exit'");
        int numberOfParentheses = scanner.nextInt(); // takes number of open-closed parantheses
        int countOfRightStrings = 0;
        while (true) {
            String string = scanner.nextLine(); // get string
            if (string.equals("exit")) break; // type 'exit' to exit
            if (isStringHaveNormalOrderOfParenthesesWithExactNumberOfThem(string, numberOfParentheses)) {
                countOfRightStrings++; // if string is the corrent order of paratheses according to the task, increment counter of these strings
            }
        }
        System.out.println("Number of strings that contain correct parentheses: " + countOfRightStrings); // display the result
    }

    /**
     * Checks whether input string contains in a right order a number of open and closed parantheses
     * @param string
     * @param number
     * @return boolean
     */
    private static boolean isStringHaveNormalOrderOfParenthesesWithExactNumberOfThem(String string, int number) {
        Stack<Character> stack = new Stack<>(); // create stack for all operations
        int count = 0; // create counter for indicating numbers of right pairs of parantheses
        for (char c : string.toCharArray()) { // iterating in array of chars
            if (c == '(') { // if it's open parenthesis push stack
                stack.push(c);
            } else {
                if (c == ')') {
                    if (stack.empty()) return false; // checks whether this is a first occurrence in the string and returns false as it is wrong
                    if (stack.pop() == '(') {
                        count++; // checking if element from stack is open and current is closed we increment our counter
                    }
                }
            }
        }
        return count == number & stack.size() == 0; // should return true if string contains exactly numbers of open-closed parantheses and the stack is empty
    }


}

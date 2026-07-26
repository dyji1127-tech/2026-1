package cse2010.hw4;
/*
 * CSE2010 Homework #4: BinToDec.java
 *
 * Complete the code below.
 */

public class BinToDec {

    public static int binToDec(String number) {
        if (number.length() == 1) {
            // Base case
            return number.charAt(0) - '0';
        } else {
            // Recursive case
            int firstDigit = number.charAt(0) - '0';
            int power = number.length() - 1; // 2의 거듭제곱 횟수

            return (firstDigit * (int)Math.pow(2, power)) + binToDec(number.substring(1));
        }
    }

    public static int binToDec(String number, int result) {
        return binToDecTR(number, result);
    }

    // Tail-recursion
    public static int binToDecTR(String number, int result) {
        int currentDigit = number.charAt(0) - '0';
        if (number.length()== 1) {
            // Base case
            return (result * 2) + currentDigit;
        } else {
            // Recursive case
            return binToDecTR(number.substring(1), (result * 2) + currentDigit);
        }
    }
}

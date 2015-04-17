package TaskTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bukatinvv on 17.04.2015.
 */
public class PalindromeFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();

        // We will often use length of the array, so need to remember
        int arrayLength = s.length();
        //get an array of digit(because String is immutable), and count how much there 9.
        byte firstDigit;
        int countOfNine = 0;
        byte[] digitsArray = new byte[arrayLength];
        int palindromeResult = 0;
        for (int i = 0; i < arrayLength; i++) {
            digitsArray[i] = Byte.valueOf(s.substring(i, i + 1));
            if (digitsArray[i] == (byte) 9) {
                countOfNine++;
            }
        }
        firstDigit = digitsArray[0];
        // if all digits = 9, then we only need add 2 to number;
        // if all digits = 9, except first digit, then we need add (first digit +2) to number;
        // otherwise we need to loop for all numbers
        if (countOfNine == arrayLength) {
            palindromeResult = Integer.valueOf(s) + 2;// if all digits = 9, then we only need add 2 to number
        } else if (countOfNine == (arrayLength - 1) && firstDigit != (byte) 9) {
            palindromeResult = Integer.valueOf(s) + firstDigit + 2;
        } else {
            // loop only for digitsArray.length / 2, because we need to compare two digit from begin and end of number
            for (int j = 0; j < digitsArray.length / 2; j++) {
                if (digitsArray[j] == digitsArray[arrayLength - j - 1]) {/*NOP*/}
                else if (digitsArray[j] > digitsArray[arrayLength - j - 1]) {
                    // if digit from begin > digit from end, we can only replace digit from end by digit from begin
                    digitsArray[arrayLength - j - 1] = digitsArray[j];
                } else {
                    // if digit from begin < digit from end, we need:
                    // 1. replace digit from end by digit from begin
                    // 2. add to number (10^2). Since we have array of digit, we will increase by 1 the digit from end, if digit = 9, then it =0 and increase next/
                    digitsArray[arrayLength - j - 1] = digitsArray[j];
                    for (int k = arrayLength - j - 2; k >= 0; k--) {
                        if (digitsArray[k] == 9) {
                            digitsArray[k] = 0;
                        } else {
                            digitsArray[k] += 1;
                            break;
                        }
                    }
                }
            }
            StringBuilder sPalindromeResult = new StringBuilder();
            for (int k = 0; k < arrayLength; k++) {
                sPalindromeResult.append(digitsArray[k]);
            }
            palindromeResult = Integer.valueOf(sPalindromeResult.toString());
        }
        System.out.println(palindromeResult);
    }
}

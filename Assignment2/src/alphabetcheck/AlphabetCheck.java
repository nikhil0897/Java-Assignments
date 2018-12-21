package alphabetcheck;

import java.util.*;

public class AlphabetCheck {

    static Set<Integer> check = new HashSet<>(26);

    public static void main(String[] args) {

        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string that you want to check: ");
        String S = input.nextLine();
        for (char c : S.toCharArray()) {
            int n = c - 'a';
            if (n >= 0 && n < 26) {
                if (check.add(n)) {
                    count += 1;
                    if (count == 26) {
                        System.out.println("The input string contains all the alphabets.");
                        break;
                    }
                }
            }
        }
        if( count != 26 ) {
            System.out.println("The input string doesn't contain all the alphabets.");
        }
    }
}

// The time complexity of this code is O(n)

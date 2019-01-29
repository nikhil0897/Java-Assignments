import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AlphabetCountCheck {

    static Set<Integer> checkTheStatement = new HashSet<>(26);    //HashSet to maintain count of the characters occurrence
    public static void main(String[] args) {
        int counter = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string that you want to check: ");
        String statement = input.nextLine();
        for (char letter : statement.toCharArray()) {    //converting string statement to a character array
            int indexingCharacter = letter - 'a';
            if (indexingCharacter >= 0 && indexingCharacter < 26) {
                if (checkTheStatement.add(indexingCharacter)) {
                    counter += 1;
                    if (counter == 26) {
                        System.out.println("The input string contains all the alphabets.");
                        break;
                    }
                }
            }
        }
        if(counter != 26) {
            System.out.println("The input string doesn't contain all the alphabets.");
        }
    }
}

/* The time complexity of this code is O(n) and space complexity is O(1).
 *
 * Test cases:
 * 1. Enter the string that you want to check:
 *    This is Nikhil Sharma
 *    Output: The input string doesn't contain all the alphabets.
 *
 * 2. Enter the string that you want to check:
 *    The quick brown fox jumps over the lazy dog
 *    Output: The input string contains all the alphabets.
 * 
 * 3. Enter the string that you want to check:
 *    08091997
 *    Output: The input string doesn't contain all the alphabets.
 */

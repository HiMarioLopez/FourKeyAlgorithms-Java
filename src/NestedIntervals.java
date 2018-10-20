import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Algorithm: NestedIntervals
 *
 * Problem: Given a mathematical equation as a string, return whether or not
 * the brackets are appropriately balanced.
 *
 * e.g. input: {3*[1+5(6+8)]}, solution: true
 * e.g. input {3*[1+5(6+8])}, solution: false (bracket closes before parenthesis)
 * e.g. input {3*[1+5(6+8)], solution: false (curly braces never close)
 *
 *
 * Observations:
 *      1) For every opening bracket, there has to be an equivalent closing bracket
 *      2) Brackets can be nested, but there can't be an "offset" overlap: {(3+1})
 *
 * Approach:
 *      A) Initialize an empty stack
 *      B) Iterate over the equation. When an opening bracket is encountered,
 *      push the character to the stack
 *      C) If a closing character is encountered, the equation is valid if: the
 *      stack is not empty and if the popped item in the stack matches the
 *      closing character
 *      D) If c is never violated and the stack is empty after iterating, return
 *      true; otherwise, return false
 *
 * Runtime O(n)
 *
 * --------------------------------------------------------------------------------------
 *
 * Popular questions:
 *
 *      A) Given a list of start and end times, return a list of merged times
 *          e.g. [[1, 6], [2, 8], [10,20]] -> [[1, 8] [10, 20]] *(item 0 and item 1 overlap)
 *
 *         Hint: The starting interval values are like opening brackets, and the ending interval
 *         values are like closing brackets. Think about how you can sort these endpoints.
 *
 *      B) (Trickier) Verify pre-order serialization of binary tree, Link:
 *      (https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/)
 */

public class NestedIntervals {

    // Helper function used to check if c is a valid opening brace
    private static boolean isOpenBracket(Character c) {
        return c == '{' || c == '[' || c == '(';
    }

    // Helper function used to check if c is a valid closing brace
    private static boolean isClosingBracket(Character c) {
        return c == '}' || c == ']' || c == ')';
    }

    // Helper function used to check if pair of characters are valid pair of braces
    private static boolean isMatchingBrace(Character open, Character close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');
    }

    private static boolean bracketsAreBalanced(String s) {

        // Initialize the stack we'll be using to hold braces
        Deque<Character> stack = new ArrayDeque<>();

        // Temporary variables to hold our current current character
        // and the last known opening brace
        char currChar;
        char lastOpening;

        // for each character in s
        for (int index = 0; index < s.length(); index++) {
            // update currChar
            currChar = s.charAt(index);

            // If opening brace
            if (isOpenBracket(currChar))
                // Push brace type to our stack
                stack.push(currChar);
            // If closing brace
            else if (isClosingBracket(currChar)) {
                // If stack is empty we return true (closing brace MUST have
                // an opening brace
                if (stack.size() == 0)
                    return false;

                // Pop last value in our stack and store it into our temp variable
                lastOpening = stack.pop();

                // Check if lastOpening and currChar (closing brace) are of
                // matching type, if not return false and end algorithm
                if (!isMatchingBrace(lastOpening, currChar))
                    return false;
            }
        }

        // At the end of our loop check if stack is empty. If brace is left alone
        // (no matching pair found by end) we will return false, else return true
        return stack.size() == 0;
    }

    public static void main(String[] args) {

        // Test case 1, should be true
        System.out.println("Solution 1: " + bracketsAreBalanced("(3+9{12+4})(25)"));

        // Test case 2, should be false
        System.out.println("Solution 2: " + bracketsAreBalanced("3+9{12+4})(25)"));
    }
}

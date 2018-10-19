import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Algorithm: Sliding Window
 *
 * Problem: Given a string and a set of characters, find the length of the
 * smallest substring that contains all of the characters.
 *
 * e.g. input: ABCZDACB, set: {A, B, C, D}, solution: DACB -> 4
 * e.g. input: AAAAABAA, set: {A}, solution: A -> 1
 *
 * Observations:
 *      A) We could just test every substring in O(n^2) time. Not good! Don't do this!
 *      B) The global max will start and end with different characters in the set
 *          e.g. input: ABEEEEEECEBBEAEEEEEEBAGDC, set: {A, B, C}, solution: CEBEA -> 5
 *      C) There may be many substrings that have all the characters and start and end
 *      with different characters in the set
 *          e.g. input: ABEEEEEECEBBEAEEEEEEBAGDC, set: {A, B, C},
 *               solutions: ABEEEEEEC -> 9, ABEEEEEECEBBEA -> 14, etc.
 * Approach:
 *      A) Have two pointers, left and right, starting at 0
 *      B) Increment right until all the characters in the set have been encountered
 *      C) When the set has been completed, increment left until one character in
 *      the set no longer lies within left and right.
 *          Note: a minimized string now lies from left - 1 to right. If this is the
 *          smallest string found so far, then store it in some variable.
 *      D) Repeat B and C while right is less than length of the string
 *
 * Runtime: O(n)
 *
 * --------------------------------------------------------------------------------------
 *
 * Recognizing this algorithm:
 *      A) The question involves an ordered sequence of elements
 *      B) The output is supposed to be a global min/max
 *      C) The trivial solution is O(n^2)
 *      D) Order doesn't matter (you're not matching patterns)
 *          e.g. input: ABCZDACB, set: {A, B, C, D}, solution: DACB
 *          e.g. input: ABCZDCAB, set: {A, B, C, D}, solution: DCAB
 *
 * Popular questions:
 *      - Given an array of integers, find the sub-array with the largest total sum
 *      - You're given a string, a mapping from characters -> score (integer), and
 *        an integer n. Find the longest substring with a score less than or equal to n.
 */

public class SlidingWindow {

    private static int smallestSubstring(String s, Set<Character> chars) {

        // our trivial cases
        if (s.length() == 0)
            return 0;
        if (chars.size() == 0)
            return 0;

        // Pointers, one to track end of string, one to track beginning
        int left = 0;
        int right = 0;

        // Score, will track min length of string containing all chars
        // Initialize to 'infinity' (s might not contain chars)
        int best = Integer.MAX_VALUE;

        // Counter to ensure we've encountered every char in chars
        int charsEncountered = 0;

        // Map we use to store each char in s, use for quick access
        Map<Character, Integer> letterMap = new HashMap<>();

        // fill letterMap with all characters in s
        for (int index = 0; index < s.length(); index++) {
            letterMap.put(s.charAt(index), 0);
        }

        // while there we still have chars to analyze
        // check our left and right pointers don't fall out edge
        // so we don't get any NullPointerException
        while (right < s.length() && left < s.length()) {

            // Move right until we reach end of string
            letterMap.put(s.charAt(right), letterMap.get(s.charAt(right)) + 1);
            if (letterMap.get(s.charAt(right)) == 1 && chars.contains(s.charAt(right))) {
                charsEncountered++;
            }
            right++;

            // We've encountered every letter in chars - we will now minimize the array
            if (charsEncountered == chars.size()) {
                // Make the string 'invalid' again, and update best before continuing
                while (charsEncountered == chars.size()) {
                    letterMap.put(s.charAt(left), letterMap.get(s.charAt(left)) - 1);
                    if (letterMap.get(s.charAt(left)) == 0 && chars.contains(s.charAt(left))) {
                        charsEncountered--;
                    }

                    left++;
                }

                // Take the minimum value (compare prev. min and new score)
                best = Integer.min(best, right - left + 1);
            }
        }

        // Return
        return best;
    }

    // Test cases
    public static void main(String[] args) {
        Set<Character> chars = new HashSet<>();

        // Test case 1, answer should be 4
        chars.add('a');
        chars.add('b');
        chars.add('c');
        System.out.println("Solution 1: " + smallestSubstring("adddddbcbba", chars));

        // Test case 2, answer should be 3
        chars.clear();
        chars.add('a');
        chars.add('b');
        chars.add('c');
        System.out.println("Solution 2: " + smallestSubstring("abc", chars));

        // Test case 3, answer should be 5
        chars.clear();
        chars.add('a');
        chars.add('b');
        chars.add('c');
        System.out.println("Solution 3: " + smallestSubstring("abdddddcbeba", chars));

        // Test case 4, answer should be 6
        chars.clear();
        chars.add('a');
        chars.add('b');
        chars.add('c');
        System.out.println("Solution 4: " + smallestSubstring("abweweffawefcaaaaboiwuroqiwuroiueeeb", chars));

        // Test case 5, answer should be 0
        chars.clear();
        System.out.println("Solution 5: " + smallestSubstring("abcdefg", chars));

        // Test case 6, answer should be 0
        chars.clear();
        chars.add('a');
        chars.add('b');
        chars.add('c');
        System.out.println("Solution 6: " + smallestSubstring("", chars));
    }
}

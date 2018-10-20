import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

/**
 * Algorithm: Dynamic Programming
 *
 * What is Dynamic Programming?
 *      a) Involved when there are many 'overlapping' sub-problems to an algorithm.
 *      b) Centers on using past solutions to improve runtime of an algorithm.
 *
 * Problem: Given a number n representing total cents and a set of numbers representing
 * coins how many ways can you make n cents? You have an infinite supply of each coin.
 *      e.g. input: n=15, coins={1, 5}, solution:
 *      {15 pennies, 2 nickles & 5 pennies, 1 nickel & 10 pennies, 3 nickels} -> 4 ways
 *
 * Attempt #1: Recursion:
 *
 * We can recursively reduce n until n <= 0.
 *     Base Cases:
 *          1) n = 0 -> return 1
 *          2) n < 0 -> return 0
 *     Recursive Case:
 *          total = 0
 *          for every coin c less than or equal to the last used coin:
 *              total += recursivelySolve(n-c, coins, c)
 *          return total
 *
 *     Effectively, we are recursing on two cases: for any coin c_1, generate solutions
 *     including c_1 and excluding c_1.
 *
 *     We will be repeating work using this approach. Runtime O(n^2) - NOT GOOD.
 *
 *     How can we improve?
 *
 * Memoization!
 *      - Technique to cache solutions so that they don't need to be recomputed
 *      - Maintain a mapping from n to total
 *          - No recomputing when overlapping states are encountered
 *
 * Runtime analysis:
 *      - Depth of tree: O(n)
 *          - O(n) solutions are computed due to caching
 *      - Each subproblem requires O(len(coins)) iterations
 *
 * Runtime: O(n * len(coins))
 *
 *      - Number of items in the cache: O(n)
 *
 * Space complexity: O(n)
 *
 * --------------------------------------------------------------------------------------
 *
 * Popular questions:
 *
 *      - Optimize the fibonacci recursive function
 *
 *      - How many ways can Jim jump up n stairs if he can jump 1, 2, or 3 steps at a time?
 *
 *      - Given an n*n matrix where all numbers are distinct, find the maximum length
 *        path (starting from any cell) such that all cells along the path are in
 *        increasing order with a difference of 1. You can traverse in the N-S-E-W
 *        direction.
 */


/**
 * WARNING: This code sample uses Google Guava's MultiMap implementation.
 * @TODO Convert to Maven project, add Google Guava libraries for utility functions.
 * @TODO Fix this shit
 */
public class DynamicProgramming {

    private static Map<Integer, Integer> cache = new HashMap<>();

    private static int countCombinations(int n, int[] coins) {
        ArrayList<Integer> ascendingCoins = new ArrayList<>();

        for (Integer coin : coins)
            ascendingCoins.add(coin);

        Collections.sort(ascendingCoins);
        Collections.reverse(ascendingCoins);

        int total = 0;

        for (Integer coin : ascendingCoins)
            //total += coinCombinationsCache(n - coin, .toPrimitive(ascendingCoins), coin);
            return 0;

        return 0;
    }

    private static int coinCombinationsCache(int n, int[] coins, int lastCoin) {

        if (n == 0)
            return 1;
        if (n < 0)
            return 0;

        int total = 0;

        for (int coin: coins) {
            if (coin <= lastCoin) {
                total += coinCombinationsCache(n - coin, coins, coin);
            }
        }

        cache.put(n, total);

        return total;
    }

    private static int coinCombinationsNoCache(int n, int[] coins, int lastCoin) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;

        int total = 0;

        for (int coin : coins) {
            if (coin <= lastCoin)
                total += coinCombinationsNoCache(n - coin, coins, coin);
        }

        return total;
    }

    public static void main(String[] args) {

    }

}

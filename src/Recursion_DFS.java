/**
 * Algorithm: Recursive Depth-First-Search (DFS)
 *
 * Problem: Given a n*m matrix, count the number of 'islands' in the matrix. Land is
 * represented as a 1 that's connected to the N-S-E-W direction.
 *
 *      e.g. input: [[1,1,0], [1,0,0], [0,1,0]], solution: 2
 *
 *          Matrix visualized:      Graph equivalent:
 *
 *             ---------               (1) - (1)
 *             | 1 1 0 |                |
 *             | 1 0 0 |               (1)
 *             | 0 1 0 |                  (1)
 *             ---------
 *
 *      e.g. input: [[1,0,0], [0,1,0], [0,0,1]], solution: 3
 *
 *          Matrix visualized:       Graph equivalent:
 *
 *             ---------                (1)
 *             | 1 0 0 |                   (1)
 *             | 0 1 0 |                      (1)
 *             | 0 0 1 |
 *             ---------
 *
 *      e.g. input: [[1,1,1], [1,0,1], [0,1,1]], solution: 1
 *
 *          Matrix visualized:      Graph equivalent:
 *
 *             ---------               (1)-(1)-(1)
 *             | 1 1 1 |                |       |
 *             | 1 0 1 |               (1)     (1)
 *             | 0 1 1 |                        |
 *             ---------                   (1)-(1)
 *
 * Observations:
 *      A) Visualize the matrix (as seen above). Notice how it seems to look like a
 *      sort of graph.
 *      B) We can access every 'node' by simply iterating over the matrix
 *
 * Approach:
 *      1) Iterate over the array using a nested for loop /w variables x and y. If you
 *      encounter a 1, preform a depth-first search (DFS) traversal and increment the
 *      islandCount variable by 1.
 *      2) DFS search using recursion
 *          a) Base case: stop recursing if:
 *              i. The index of x and y are out of bounds of the array.
 *              ii. The item at arr[x][y] is 0 (not land)
 *          b) Recursive case:
 *              i. Change the element arr[x][y] to be 0, so that it is not
 *              re-recursed on
 *              ii. Call the recursive function on [x-1][y], [x+1][y], [x][y-1], and [x][y+1]
 *
 * Runtime: O(n * m), for any n * m matrix
 *
 * --------------------------------------------------------------------------------------
 *
 * Popular questions:
 *
 *      - Given a 2D array of characters and a string, return whether the word exists in
 *      the grid using the same rules as the example question (can only recurse in the
 *      NSEW direction, can't re-use a cell)
 *          a) Bonus: given a set of words, return whether all the words are in the grid or not
 *             Hint: look up what a prefix tree is
 *
 *      - Given an array of integers, count the number of inversions in it. The number of
 *      inversions is the number of adjacent swaps necessary to sort the array.
 *          e.g. [2, 3, 1] -> 2 (swap index 1 & 2, swap index 0 & 1)
 *             Hint: look up merge sort
 */

public class Recursion_DFS {

    private static int countIslands(int array[][]) {
        int total = 0;

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if (array[x][y] == 1) {
                    total += 1;
                    depthFirstSearch(x, y, array);
                }
            }
        }

        return total;
    }

    private static void depthFirstSearch(int x, int y, int array[][]) {

        // Base cases:

        // x or y are both invalid indices (less than 0)
        // x or y are both larger than max length/width of matrix
        if (x < 0 || x >= array.length || y < 0 || y >= array[x].length)
            return;
        // Index at x and y is not an island (is equal to 0)
        if (array[x][y] == 0)
            return;

        // Recursive case:

        // Set index to 0 to mark as visited (avoid infinite loop)
        array[x][y] = 0;

        // Divide and conquer!
        depthFirstSearch(x+1, y, array);
        depthFirstSearch(x-1, y, array);
        depthFirstSearch(x, y-1, array);
        depthFirstSearch(x, y+1, array);
    }

    public static void main(String[] args) {

        // Test case 1, should return 2
        int test1[][] = { {1, 1, 1}, {1, 0, 0}, {0, 1, 0} };
        System.out.println("Solution 1: " + countIslands(test1));

        // Test case 2, should return 3
        int test2[][] = { {1, 0, 0}, {0, 1, 0}, {0, 0, 1} };
        System.out.println("Solution 2: " + countIslands(test2));

        // Test case 3, should return 1
        int test3[][] = { {1, 1, 1}, {1, 0, 1}, {0, 1, 1} };
        System.out.println("Solution 3: " + countIslands(test3));

    }

}

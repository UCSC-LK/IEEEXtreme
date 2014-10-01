
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
public class LCS {

    /**
     * This calculates the LCS length for each substring
     *
     * @param x first sequence
     * @param y second sequence
     * @return array containing the length of LCS's for each substring
     */
    public int[][] initLCS(String[] x, String[] y) {
        int[][] c = new int[x.length + 1][y.length + 1];
        for (int i = 0; i < x.length; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < y.length; i++) {
            c[0][i] = 0;
        }
        for (int i = 1; i < x.length + 1; i++) {
            for (int j = 1; j < y.length + 1; j++) {
                if (x[i - 1].equals(y[j - 1]) ) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }
        return c;
    }

    /**
     * This do backtracking in order to get the LCS printed
     *
     * @param c array containing the lengths
     * @param x first sequence
     * @param y second sequence
     * @param i current index of first sequence
     * @param j current index of second sequence
     * @return next character of the LCS
     */
    public String backtrack(int[][] c, String[] x, String[] y, int i, int j) {
        if (i == 0 || j == 0) {
            return "";
        } else if (x[i - 1].equals(y[j - 1]) ) {
            return backtrack(c, x, y, i - 1, j - 1) + x[i - 1];
        } else {
            if (c[i][j - 1] > c[i - 1][j]) {
                return backtrack(c, x, y, i, j - 1);
            } else {
                return backtrack(c, x, y, i - 1, j);
            }
        }
    }
}

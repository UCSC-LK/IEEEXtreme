
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
public class FloodFill {

    /**
     * This method implements 8 direction basic flood fill
     *
     * @param n node array
     * @param i row number
     * @param j column number
     * @param targetColor color to check for
     * @param replacementColor new color that should be applied
     */
    public void floodFill(Node[][] n, int i, int j, String targetColor, String replacementColor) {
        if (!n[i][j].color.equals(targetColor)) {
            return;
        }
        n[i][j].color = replacementColor;
        if (j > 0) {
            floodFill(n, i, j - 1, targetColor, replacementColor);
        }
        if (j > 0 && i > 0) {
            floodFill(n, i - 1, j - 1, targetColor, replacementColor);
        }
        if (j > 0 && i < n.length - 1) {
            floodFill(n, i + 1, j - 1, targetColor, replacementColor);
        }
        if (j < n[i].length - 1 && i > 0) {
            floodFill(n, i - 1, j + 1, targetColor, replacementColor);
        }
        if (j < n[i].length - 1 && i < n.length - 1) {
            floodFill(n, i + 1, j + 1, targetColor, replacementColor);
        }
        if (j < n[i].length - 1) {
            floodFill(n, i, j + 1, targetColor, replacementColor);
        }
        if (i > 0) {
            floodFill(n, i - 1, j, targetColor, replacementColor);
        }
        if (i < n.length - 1) {
            floodFill(n, i + 1, j, targetColor, replacementColor);
        }
        return;

    }
}

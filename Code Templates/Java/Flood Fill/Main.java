
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
public class Main {

    public static void main(String[] args) {
        Node[][] array = new Node[2][3];
        array[0][0] = new Node("green");
        array[0][1] = new Node("blue");
        array[0][2] = new Node("blue");
        array[1][0] = new Node("blue");
        array[1][1] = new Node("green");
        array[1][2] = new Node("green");
        FloodFill f = new FloodFill();
        f.floodFill(array, 1, 1, "green", "red");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j].color + "  ");
            }
            System.out.println();
        }

    }
}

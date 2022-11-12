package matrizen;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args)
            throws FileNotFoundException {

        //Test des einlesens einer Matrix und der Funktion transpose
        System.out.println("Die eingelesene Matrix ist: ");
        int[][] matrix = MatrixOperations.readMatrix("src/matrizen/matrix1.txt");
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("Matrix nach Transpose: ");
        System.out.println(Arrays.deepToString(MatrixOperations.transpose(matrix)));
    }
}

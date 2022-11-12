package matrizen;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args)
            throws FileNotFoundException {

        //Test des einlesens einer Matrix und der Funktion transpose
        System.out.println("Die eingelesene Matrix ist: ");
        int[][] matrix1 = MatrixOperations.readMatrix("src/matrizen/matrix1.txt");
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println("Matrix nach Transpose: ");
        System.out.println(Arrays.deepToString(MatrixOperations.transpose(matrix1)));

        //Test einer nicht quadratiscen Matrix
        System.out.println("Matrix2: ");
        int[][] matrix2 = MatrixOperations.readMatrix("src/matrizen/matrix2.txt");
        System.out.println(Arrays.deepToString(matrix2));
        System.out.println("Matrix2 nach Transpose: ");
        System.out.println(Arrays.deepToString(MatrixOperations.transpose(matrix2)));

        //Test von product
        int[][] matrix3 = MatrixOperations.readMatrix("src/matrizen/matrix3.txt");
        System.out.println("Die beiden Matrizen sind: ");
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println(Arrays.deepToString(matrix3));
        System.out.println("Das Produkt ist: ");
        System.out.println(Arrays.deepToString(MatrixOperations.product(matrix1, matrix3)));

        //Test von product null
        System.out.println("Test null von Methode product: ");
        System.out.println(Arrays.deepToString(MatrixOperations.product(matrix2, matrix3)));
    }
}

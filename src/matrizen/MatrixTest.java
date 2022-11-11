package matrizen;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args)
            throws FileNotFoundException {
        System.out.println(Arrays.deepToString(MatrixOperations.readMatrix("src/matrizen/matrix1.txt")));
    }
}

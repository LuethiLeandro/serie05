package matrizen;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixOperations {
    private final static String PATH_NAME="src/matrix.txt";

    public static void readMatrix() throws FileNotFoundException {
            Scanner fileReader = new Scanner(new File(MatrixOperations.PATH_NAME));

            int[][] matrix = new int[2][3];
            int line = 1;

            while (fileReader.hasNext()) {
                String nextLine = fileReader.nextLine();
                for(int i=0; i<nextLine.length();i++){
                    ArrayList<Character> oneLine = new ArrayList<>();
                    if(nextLine.charAt(i)  != ' '){
                        oneLine.add(nextLine.charAt(i));
                    }
                        matrix[line][i] = nextLine.charAt(i);

                }
                line++;
            }
    }

}

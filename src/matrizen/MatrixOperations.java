package matrizen;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Pascal Zürcher, Matrikelnummer: 22-111-314
// Leandro Lüthi. Matrikelnummer: 22-105-035

public class MatrixOperations {

    public static int[][] readMatrix(String path)
            throws FileNotFoundException {
            Scanner fileReader = new Scanner(new File(path));
            //Zählen der Anzahl Zeilen und der längsten Zeile
            int rows=0;
            int longLine=0;
            while(fileReader.hasNext()){
                String line = fileReader.nextLine();
                int einträgeLine = 2;
                for(int i = 0; i < line.length(); i++){
                    if (line.charAt(i)==' '){
                        einträgeLine++  ;
                    }
                }
                if (einträgeLine > longLine){
                    longLine = einträgeLine;
                };
                rows++;
            }

            //Instanzierung eines 2D-Arrays mit der richtigen Grösse.
            int[][] matrix = new int[rows][longLine-1];

            //neuer Scanner, da Zeilen erneut  durchgegangen werden müssen
            Scanner fileReader2 = new Scanner(new File(path));
            //Hilfsvariable, welche angibt, welche Zeile wir gerade einlesen
            int rowcounter = 1;
            //Schleife geht jede Zeile durch
            while (rowcounter<=rows) {
                //liest Zeile ein
                String nextLine = fileReader2.nextLine();
                int gaps = 0;
                //Geht jede Zahl durch, welche von Leerzeichen getrennt sind und speichert
                //diese in das Array
                Scanner lineScanner = new Scanner(nextLine);
                lineScanner.useDelimiter(" ");
                while (lineScanner.hasNext()) {
                      matrix[rowcounter-1][gaps] = lineScanner.nextInt();
                      gaps++;
                }
                rowcounter++;
            }
            return matrix;
    }

    public static int[][] transpose(int[][] matrix){

        int transition=0;
        for(int i=0;i<matrix.length-1;i++){
            if(matrix.length!=matrix[i].length){
                return null;
            }
            for(int j=0; j<matrix[i].length-1; j++){
                  int temp = matrix[i][j+transition];
                  matrix[i][j+transition]=matrix[j+transition][i];
                  matrix[j+transition][i]=temp;
            }
            transition++;
        }
        return matrix;
    }

    public static int[][] product(int[][] matrix1, int[][] matrix2){
        int[][] matrix3 = new int[matrix1.length][matrix1.length];
        for(int i=0; i<matrix1.length;i++){

            //control
            if(matrix1.length!=matrix2[i].length){
                return null;
            }

            for(int j=0; j<=matrix1.length-1; j++){
                for(int k=0; k<=matrix1.length-1; k++){
                    matrix3[i][j] += matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return matrix3;
    }
}

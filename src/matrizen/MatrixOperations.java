package matrizen;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

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
             Scanner fileReader2 = new Scanner(new File(path));
            //Hilfsvariable, welche angibt, welche Zeile wir gerade einlesen
            int rowcounter = 1;
            //Schleife geht jede Zeile durch
            while (rowcounter<=rows) {
                //liest Zeile ein
                String nextLine = fileReader2.nextLine();
                /*Schleife geht jedes Zeichen der Zeile durch,
                wenn das Zeichen kein Leerzeichen ist, wird es in den Array nextNumber gespeichert.
                Falls ein Leerzeichen vorhanden ist, wird in den richtigen Arrayslot die Zahl gespeichert,
                der String nectNumber wird reseted und gaps wird erhöht.*/
                int gaps = 0;
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

}

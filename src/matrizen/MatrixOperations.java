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
            //Zählen der Anzahl Zeilen
            int rows=0;
            while(fileReader.hasNext()){
                rows++;
            }

            //Erzeugen eines 2D-Arrays mit der richtigen Grösse.
            int[][] matrix = new int[rows][fileReader.nextLine().length()];

            //Hilfsvariable, welche angibt, welche Zeile wir gerade einlesen
            int rowcounter = 1;

            //Schleife geht jede Zeile durch
            while (fileReader.hasNext()) {

                //liest Zeile ein
                String nextLine = fileReader.nextLine();

                /*Schleife geht jedes Zeichen der Zeile durch,
                wenn kein Leerzeichen vorhanden ist, wird das Zeichen in den nächsten freien Arrayslot
                der Zeile gespeichert. Gaps werden gezählt, um keine leeren Stellen im Array zu haben*/
                int gaps = 0;
                for(int i=0; i<nextLine.length();i++){
                    if(nextLine.charAt(i)  != ' '){
                        matrix[rowcounter][i-gaps] = Integer.parseInt(String.valueOf(nextLine.charAt(i)));
                    } else{
                        gaps++;
                    }
                }
                rowcounter++;
            }
    }

}

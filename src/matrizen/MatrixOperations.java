package matrizen;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixOperations {

    public static int[][] readMatrix(String path) throws FileNotFoundException {
            Scanner fileReader = new Scanner(new File(path));
            //Zählen der Anzahl Zeilen und der längsten Zeile
            int rows=0;
            int longLine=0;
            while(fileReader.hasNext()){
                if(longLine<fileReader.nextLine().length()){
                    longLine=fileReader.nextLine().length();
                }
                rows++;
            }

            //Erzeugen eines 2D-Arrays mit der richtigen Grösse.
            int[][] matrix = new int[rows][longLine];

            //Hilfsvariable, welche angibt, welche Zeile wir gerade einlesen
            int rowcounter = 1;

            //Schleife geht jede Zeile durch
            while (fileReader.hasNext()) {

                //liest Zeile ein
                String nextLine = fileReader.nextLine();

                /*Schleife geht jedes Zeichen der Zeile durch,
                wenn das Zeichen kein Leerzeichen ist, wird es in den Array nextNumber gespeichert.
                Falls ein Leerzeichen vorhanden ist, wird in den richtigen Arrayslot die Zahl gespeichert,
                der String nectNumber wird reseted und gaps wird erhöht.*/
                int gaps = 0;
                String nextNumber = "";
                for(int i=0; i<nextLine.length();i++){
                    if(nextLine.charAt(i)  != ' '){
                        nextNumber =""+nextLine.charAt(i);
                    } else{
                        matrix[rowcounter][i-gaps] = Integer.parseInt(String.valueOf(nextNumber));
                        nextNumber="";
                        gaps++;
                    }
                }
                rowcounter++;
            }
            return matrix;
    }

}

package bol1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ej3 {

    public static void main(String[] command) {
        // TODO Auto-generated method stub
        String line;
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        try {
            Process cmd = pb.start();
            InputStream is = cmd.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "850"));

            System.out.println("La salida del proceso hijo " + Arrays.toString(command) + ":");
            while ((line = br.readLine()) != null) {
                System.out.println(line);

            }
            is.close();


        } catch (IOException e) {
            System.out.println("Error ocurrió ejecutando el comando. Descripción: "+e.getMessage());
        }
    }

}
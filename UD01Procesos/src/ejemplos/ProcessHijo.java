package ejemplos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessHijo {

    public static void main(String[]args){

        String entrada="";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);

        try
        {
            entrada=br.readLine();
            System.out.println(entrada.toUpperCase());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
package ejerciciorepaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

public class Proceso_Hijo {
    public static void main(String[] args) {
        System.out.println("[HIJO]> Iniciado y Esperando Entrada...");
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in))){
            String linea;
            int valorExit = 0;
            while(!(linea = bfr.readLine().trim()).equalsIgnoreCase("ACABAR")){
                double res = comprobarOperacion(linea);
                if(res == -10){
                    System.err.println("ERROR AL REALIZAR LA OPERACION");
                    System.err.flush();
                    valorExit = 1;
                }else{
                    System.out.println("Haciendo calculo...");
                    sleep(1000);
                    System.out.println(res);
                    System.out.flush();
                }
            }
            System.out.println("Acabamos");
            System.exit(valorExit);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static double comprobarOperacion(String linea) {
        double res = -10;
        if(linea.contains("*")){
            String[] op = linea.replace("*","l").split("l");
            double uno = Double.parseDouble(op[0]);
            double dos = Double.parseDouble(op[1]);
            res = uno * dos;
        } else if (linea.contains("/")) {
            String[] op = linea.replace("/","l").split("l");
            double uno = Double.parseDouble(op[0]);
            double dos = Double.parseDouble(op[1]);
            res = uno / dos;
        } else if (linea.contains("+")) {
            String[] op = linea.replace("+","l").split("l");
            double uno = Double.parseDouble(op[0]);
            double dos = Double.parseDouble(op[1]);
            res = uno + dos;
        } else if (linea.contains("-")) {
            String[] op = linea.replace("-","l").split("l");
            double uno = Double.parseDouble(op[0]);
            double dos = Double.parseDouble(op[1]);
            res = uno - dos;
        }
        return res;
    }
}

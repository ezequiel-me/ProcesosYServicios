package ejerciciorepaso;

import java.io.*;
import java.util.Random;

public class GestorAuditoria {
    private static BufferedWriter bfwriter;

    public GestorAuditoria(){
        ProcessBuilder proceso = getProcessBuilder();
        try {
            // Ejecutamos el proceso
            Process p = proceso.start();

            bfwriter = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static ProcessBuilder getProcessBuilder() {
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder proceso = new ProcessBuilder("java", "-cp", classpath, "ejerciciorepaso.Proceso_Hijo");
        File ficheroSalida = new File("salida.txt");
        File ficheroErrores = new File("errores.txt");
        if(!ficheroSalida.exists() && !ficheroErrores.exists()){
            try {
                ficheroSalida.createNewFile();
                ficheroErrores.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        proceso.redirectOutput(ficheroSalida);
        proceso.redirectError(ficheroErrores);
        return proceso;
    }

    public synchronized void hacerOperacion(String operacion) {
        try {
            bfwriter.write(operacion);
            System.out.println("Haciendo operacion");
            bfwriter.flush();
            this.notifyAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

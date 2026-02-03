package ejerciciorepaso;

import java.io.BufferedWriter;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Hilo implements Runnable{
    private final static int NUM_OPERACIONES=3;
    private String[] operaciones;
    private GestorAuditoria gestorAuditoria;
    private int numH;
    public Hilo(String[] operaciones, GestorAuditoria gestorAuditoria, int numH) {
        this.operaciones = operaciones;
        this.gestorAuditoria = gestorAuditoria;
        this.numH = numH;
    }

    @Override
    public void run() {
        int contador = 1;
        while(contador != NUM_OPERACIONES){
            Random r = new Random();
            int num = r.nextInt(0,14);
            String operacion = operaciones[num];
            System.out.println("[HILO"+numH+"]> Quiero: "+operacion);
            try {
                sleep(3000);
                System.out.println("Solicitando calculo...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gestorAuditoria.hacerOperacion("acabar");
            contador++;
        }
    }
}

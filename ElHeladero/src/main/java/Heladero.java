import java.util.Random;

import static java.lang.Thread.sleep;

public class Heladero implements Runnable{
    private int numHelados;
    VitrinaHelados vitrina;
    private String[] sabores;

    public Heladero(int numHelados, VitrinaHelados vitrina) {
        this.numHelados = numHelados;
        this.vitrina = vitrina;
        this.sabores = new String[]{"Fresa", "Choco"};
    }


    @Override
    public void run() {
        System.out.println("[HELADERO]> Empieza Mi Día. A Trabajar.");
        while(numHelados > 0){
            System.out.println("[HELADERO]> Voy a producir un helado.");
            if(vitrina.getCapacidad() == vitrina.getHelados().size()){
                System.out.println("[HELADERO]> Vitrina Llena, estoy en espera...");
            }

            // Genera del 0 -1 ;
            Random r = new Random();
            int rInt = r.nextInt(0,2);
            String saborGenerado = sabores[rInt];
            Helado helado = new Helado(sabores[rInt]);
            try {
                vitrina.poner(helado);
                sleep(1000);
                numHelados--;
                System.out.println("[HELADERO]> Helado ("+ saborGenerado + ") Producido E Insertado.");
                System.out.println("[HELADERO]> Quedan: "+numHelados+" Helados por hacer");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        vitrina.setCerrada(true);
    }

    public int getNumHelados() {
        return numHelados;
    }

    public void setNumHelados(int numHelados) {
        this.numHelados = numHelados;
    }

    public VitrinaHelados getVitrina() {
        return vitrina;
    }

    public void setVitrina(VitrinaHelados vitrina) {
        this.vitrina = vitrina;
    }

}

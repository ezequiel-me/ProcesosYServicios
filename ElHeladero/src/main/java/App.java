import java.util.Random;

public class App {
    public static void main(String[] args) {
        System.out.println("### HELADERIA ABIERTA ###");


        VitrinaHelados vitrinaHelados = new VitrinaHelados(8);

        Heladero heladero = new Heladero(1, vitrinaHelados);

        String[] nombres = new String[]{"Ana", "Paula", "Carla", "Lucia", "Alvaro", "Saul", "Carlos", "Daniel"};
        String[] sabores = new String[]{"Fresa", "Choco"};
        Random r = new Random();
        Thread hiloHeladero = new Thread(heladero);
        hiloHeladero.start();
        for(int i = 0; i < nombres.length; i++){
            int num1 = r.nextInt(0,8);
            int num2 = r.nextInt(0,2);
            Ninios ninio = new Ninios(nombres[num1], sabores[num2], vitrinaHelados);
            Thread hiloNinio = new Thread(ninio);
            hiloNinio.start();
        }

        try {
            hiloHeladero.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("### HELADERIA CERRADA ###");

    }

}

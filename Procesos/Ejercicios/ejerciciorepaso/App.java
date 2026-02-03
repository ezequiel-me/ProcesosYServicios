package ejerciciorepaso;

public class App {
    public static void main(String[] args) {
        GestorAuditoria gestor = new GestorAuditoria();
        int numeroHilos = 5;
        String[] operaciones = new String[]{"1*2","1*3","1*4","1*5","1*6","1*7","1*8","1*9","1*10","1*11","1*12","1*13","1*14","1*15","1*16", "ACABAR"};
        for(int i = 1; i <= numeroHilos; i++){
            Thread hilo = new Thread(new Hilo(operaciones, gestor, i));
            hilo.start();
        }
    }
}

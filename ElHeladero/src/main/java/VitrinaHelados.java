import java.util.ArrayList;

public class VitrinaHelados {

    private int capacidad;
    private final ArrayList<Helado> helados;
    private boolean cerrada;

    public VitrinaHelados(int capacidad) {
        this.capacidad = capacidad;
        helados = new ArrayList<>();
        cerrada = false;
    }



    public synchronized boolean poner(Helado helado){
        if(helados.size() == 8){
            System.out.println("Heladero en espera de que la vitrina se vacie helados");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        helados.add(helado);
        this.notifyAll();
        return true;
    }

    public synchronized Helado coger(String saborPreferido, String nombre){
        // Mientras la vitrina esté abierta y NO encuentre el helado de mi sabor...
        while (true) {
            for (int i = 0; i < helados.size(); i++) {
                if (helados.get(i).getSabor().equals(saborPreferido)) {
                    Helado h = helados.remove(i);
                    this.notifyAll(); // Aviso al heladero que hay sitio
                    return h;
                }
            }

            // Si llego aquí, es que no había de mi sabor
            if (cerrada && helados.isEmpty()) return null; // Salida limpia

            try {
                System.out.println("[" + nombre + "]> Esperando sabor " + saborPreferido);
                wait(); // Espero a que el heladero ponga algo
                if (cerrada && helados.isEmpty()) return null;
            } catch (InterruptedException e) { return null; }
        }
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Helado> getHelados() {
        return helados;
    }

    public boolean getCerrada() {
        return cerrada;
    }

    public synchronized void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
        this.notifyAll();
    }
}

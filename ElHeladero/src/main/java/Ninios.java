import static java.lang.Thread.sleep;

public class Ninios implements Runnable{

    private String nombre;
    private String saborPreferido;
    private final VitrinaHelados vitrina;

    public Ninios(String nombre, String saborPreferido, VitrinaHelados vitrina){
        this.nombre = nombre;
        this.saborPreferido = saborPreferido;
        this.vitrina = vitrina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSaborPreferido() {
        return saborPreferido;
    }

    public void setSaborPreferido(String saborPreferido) {
        this.saborPreferido = saborPreferido;
    }

    @Override
    public void run() {
        while(!vitrina.getCerrada()){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(vitrina.getHelados().size() == 8){
                Helado helado = vitrina.coger(saborPreferido, nombre);
                if(helado != null){
                    System.out.println("["+nombre+"]> Estoy comiéndome un helado de sabor: "+helado.getSabor());
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        System.out.println("["+nombre+"]> Me voy a mi casa, el heladero ya cerró la vitrina.");
    }
}

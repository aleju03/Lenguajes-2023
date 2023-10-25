import java.util.ArrayList;

public class Agenda {
    // Variable de clase para almacenar la única instancia (Lazy Initialization)
    private static Agenda instance;

    private ArrayList<Contacto> contactos;
    private ArrayList<Evento> eventos;

    // Constructor privado
    private Agenda() {
        this.contactos = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    /**
     * Método para obtener la única instancia de Agenda (Lazy Initialization).
     * 
     * Diferencias entre Lazy y Eager Initialization:
     * 
     * - Lazy Initialization:
     *   1. La instancia se crea solo cuando es necesaria.
     *   2. Puede ser un poco más lento cuando se accede por primera vez.
     *   3. Es útil cuando la instancia consume muchos recursos y no se necesita de inmediato.
     * 
     * - Eager Initialization:
     *   1. La instancia se crea cuando la clase se carga.
     *   2. Es más rápido al acceder por primera vez.
     *   3. Podría llevar a un desperdicio de recursos si la instancia es pesada y no se necesita de inmediato.
     * 
     * En este caso, Lazy Initialization podría ser más apropiado porque la agenda no necesita 
     * estar disponible inmediatamente al iniciar la aplicación, y podría consumir muchos recursos 
     * si tiene muchos contactos y eventos.
     * 
     * @return única instancia de Agenda
     */
    public static Agenda getInstance() {
        if (instance == null) {
            instance = new Agenda();
        }
        return instance;
    }

    // Métodos para añadir contactos y eventos
    public void addContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

    public void addEvento(Evento evento) {
        this.eventos.add(evento);
    }

    // Métodos para eliminar contactos y eventos
    public void removeContacto(Contacto contacto) {
        this.contactos.remove(contacto);
    }

    public void removeEvento(Evento evento) {
        this.eventos.remove(evento);
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Agenda:\n -Contactos:\n");
        for (Contacto contacto : this.contactos) {
            result.append("     ").append(contacto.toString()).append("\n");
        }
        result.append(" -Eventos:\n");
        for (Evento evento : this.eventos) {
            result.append("     ").append(evento.toString()).append("\n");
        }
        return result.toString();
    }
}
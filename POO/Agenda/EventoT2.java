public class EventoT2 extends Evento {
    private String descripcion;

    // Constructor
    public EventoT2(String titulo, String fecha, String descripcion) {
        super(titulo, fecha);
        this.descripcion = descripcion;
    }

    // Método get
    public String getDescripcion() {
        return this.descripcion;
    }

    // Método set
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString
    @Override
    public String toString() {
        return super.toString() + ", Descripción: " + this.descripcion;
    }
}
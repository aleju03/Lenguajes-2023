public class ConcreteFactory implements AbstractFactory {
    public Contacto createContacto(String tipo, String nombre, String telefono, String extra) {
        if ("T1".equals(tipo)) {
            return new ContactoT1(nombre, telefono, extra);
        } else {
            return new ContactoT2(nombre, telefono, extra);
        }
    }

    public Evento createEvento(String tipo, String titulo, String fecha, String extra) {
        if ("T1".equals(tipo)) {
            return new EventoT1(titulo, fecha, extra);
        } else {
            return new EventoT2(titulo, fecha, extra);
        }
    }
}
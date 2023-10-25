public class Main {
    public static void main(String[] args) {
        // Obtener la única instancia de Agenda
        Agenda agenda = Agenda.getInstance();

        // Crear una fábrica para crear contactos y eventos
        AbstractFactory factory = new ConcreteFactory();

        // Crear algunos contactos y eventos utilizando la fábrica
        Contacto contacto1 = factory.createContacto("T1", "Juan", "123456789", "juan@example.com");
        Contacto contacto3 = factory.createContacto("T1", "Pedro", "111222333", "pedro@example.com");
        Contacto contacto5 = factory.createContacto("T1", "Ana", "444555666", "ana@example.com");
        Contacto contacto2 = factory.createContacto("T2", "Maria", "987654321", "Calle Falsa 123");
        Contacto contacto4 = factory.createContacto("T2", "Laura", "777888999", "Avenida Siempre Viva 742");
        Contacto contacto6 = factory.createContacto("T2", "Carlos", "000111222", "Plaza Mayor 1");

        Evento evento1 = factory.createEvento("T1", "Reunión", "2022-12-01", "Oficina");
        Evento evento3 = factory.createEvento("T1", "Conferencia", "2022-12-03", "Hotel Lujoso");
        Evento evento5 = factory.createEvento("T1", "Seminario", "2022-12-05", "Universidad");
        Evento evento2 = factory.createEvento("T2", "Concierto", "2022-12-02", "Música en vivo");
        Evento evento4 = factory.createEvento("T2", "Festival", "2022-12-04", "Cine al aire libre");
        Evento evento6 = factory.createEvento("T2", "Exposición", "2022-12-06", "Galería de arte");

        // Añadir los contactos y eventos a la agenda
        agenda.addContacto(contacto1);
        agenda.addContacto(contacto3);
        agenda.addContacto(contacto5);
        agenda.addContacto(contacto2);
        agenda.addContacto(contacto4);
        agenda.addContacto(contacto6);
        
        agenda.addEvento(evento1);
        agenda.addEvento(evento3);
        agenda.addEvento(evento5);
        agenda.addEvento(evento2);
        agenda.addEvento(evento4);
        agenda.addEvento(evento6);

        // Mostrar la agenda
        System.out.println(agenda);
    }
}
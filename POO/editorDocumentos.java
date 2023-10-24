import java.util.ArrayList;

interface ObjetoRepresentable {
    void mostrar(int nivelIndentacion);
}

class Texto implements ObjetoRepresentable {
    private String contenido;

    public Texto(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void mostrar(int nivelIndentacion) {
        System.out.println("  ".repeat(nivelIndentacion) + "Texto: " + getContenido());
    }
}

class Circulo implements ObjetoRepresentable {
    public void mostrar(int nivelIndentacion) {
        System.out.println("  ".repeat(nivelIndentacion) + "Círculo");
    }
}

class Rectangulo implements ObjetoRepresentable {
    public void mostrar(int nivelIndentacion) {
        System.out.println("  ".repeat(nivelIndentacion) + "Rectángulo");
    }
}

class Grupo implements ObjetoRepresentable {
    private ArrayList<ObjetoRepresentable> objetos = new ArrayList<>();

    public void agregar(ObjetoRepresentable objeto) {
        objetos.add(objeto);
    }

    public void mostrar(int nivelIndentacion) {
        System.out.println("  ".repeat(nivelIndentacion) + "Grupo:");
        for (ObjetoRepresentable objeto : objetos) {
            objeto.mostrar(nivelIndentacion + 1);
        }
    }
}

class Hoja {
    private ArrayList<ObjetoRepresentable> objetos = new ArrayList<>();

    public void agregar(ObjetoRepresentable objeto) {
        objetos.add(objeto);
    }

    public void mostrar(int nivelIndentacion) {
        System.out.println("  ".repeat(nivelIndentacion) + "Hoja:");
        for (ObjetoRepresentable objeto : objetos) {
            objeto.mostrar(nivelIndentacion + 1);
        }
    }
}

class Documento {
    private ArrayList<Hoja> hojas = new ArrayList<>();

    public void agregarHoja(Hoja hoja) {
        hojas.add(hoja);
    }

    public void mostrar() {
        System.out.println("Documento:");
        for (Hoja hoja : hojas) {
            hoja.mostrar(1);
        }
    }
}

public class editorDocumentos {
    public static void main(String[] args) {
        // Crear instancias de objetos
        Texto texto1 = new Texto("Hola mundo, este es un texto de prueba.");
        Circulo circulo1 = new Circulo();
        Rectangulo rectangulo1 = new Rectangulo();
        Texto texto2 = new Texto("Texto del segundo documento.");
        Circulo circulo2 = new Circulo();
        Rectangulo rectangulo2 = new Rectangulo();

        // Crear un grupo y agregar objetos al grupo
        Grupo grupo1 = new Grupo();
        grupo1.agregar(texto1);
        grupo1.agregar(circulo1);
        // Segundo grupo y agregar objetos a él
        Grupo grupo2 = new Grupo();
        grupo2.agregar(texto2);
        grupo2.agregar(rectangulo2);

        // Crear una hoja y agregar objetos y grupos
        Hoja hoja1 = new Hoja();
        hoja1.agregar(grupo1);
        hoja1.agregar(rectangulo1);
        // Crear una segunda hoja y agregar objetos y grupos
        Hoja hoja2 = new Hoja();
        hoja2.agregar(grupo2);
        hoja2.agregar(circulo2);

        // Crear un documento y agregar hojas
        Documento doc = new Documento();
        doc.agregarHoja(hoja1);
        // Segundo documento
        Documento doc2 = new Documento();
        doc2.agregarHoja(hoja2);

        // Mostrar los documentos
        System.out.println("=== Primer Documento ===");
        doc.mostrar();
        System.out.println("\n=== Segundo Documento ===");
        doc2.mostrar();
    }
}
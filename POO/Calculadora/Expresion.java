import java.util.LinkedList;
import java.util.Stack;

public class Expresion {
    private LinkedList<Elemento> elementos;

    public Expresion() {
        elementos = new LinkedList<>();
    }

    public void reconocer(String input) {
        String[] partes = input.split("(?<=[-+*/])|(?=[-+*/])"); //basado en https://stackoverflow.com/a/2206378/142637 "How to split a string, but also keep the delimiters?"
        for (String parte : partes) {
            parte = parte.trim();
            if (parte.isEmpty()) continue;
            
            char primerCaracter = parte.charAt(0);
            if (primerCaracter == '+' || primerCaracter == '-' || primerCaracter == '*' || primerCaracter == '/') {
                elementos.add(new Operador(primerCaracter));
            } else {
                try {
                    elementos.add(new Numero(Double.parseDouble(parte)));
                } catch (NumberFormatException e) { //try catch: si no es numero ni operador [-+*/] se lanza InvalidCharException
                    throw new InvalidCharException("Expresión inválida: se encontró un caracter no válido: " + parte); 
                }
            }
        }
    }

    public double evaluar() {
        Elemento ultimoElemento = elementos.getLast();
        if (ultimoElemento instanceof Operador) {
            throw new EvaluationException("Error de evaluación: la expresión está incompleta."); //si el ultimo elemento de la lista es operador = excepcion
        }

        // Creamos dos pilas: una para números y otra para operadores
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        //For para iterar a través de la lista de elementos (números y operadores)
        for (Elemento elemento : elementos) {
            if (elemento instanceof Numero) {
                // Si el elemento es un número, se agrega a la pila de números
                numeros.push(((Numero) elemento).getNumero());
            } else {
                // Si el elemento es un operador, se procesa
                char operador = ((Operador) elemento).getOperador();

                // Mientras la pila de operadores no esté vacía y el operador actual tenga
                // igual o menor precedencia que el operador en la cima de la pila,
                // se realizan las operaciones
                while (!operadores.isEmpty() &&
                    ((operador == '+' || operador == '-') ||
                    (operador == '*' || operador == '/')) &&
                    ((operadores.peek() == '*' || operadores.peek() == '/'))) {
                    double b = numeros.pop();
                    double a = numeros.pop();
                    char op = operadores.pop();

                    // Se agrega el resultado de la operación a la pila de números
                    if (op == '+') numeros.push(a + b);
                    else if (op == '-') numeros.push(a - b);
                    else if (op == '*') numeros.push(a * b);
                    else if (op == '/') {
                        if (b == 0) { // Si se intenta dividir por cero, se lanza una excepción
                            throw new EvaluationException("Error de evaluación: división por cero.");
                        }
                        numeros.push(a / b);
                    }
                }
                // Se agrega el operador actual a la pila de operadores
                operadores.push(operador);
            }
        }

        //operaciones pendientes en las pilas de números y operadores
        while (!operadores.isEmpty()) {
            double b = numeros.pop();
            double a = numeros.pop();
            char op = operadores.pop();

            //se calcula la operacion correspondiente y se agrega el resultado
            // a la pila de números
            if (op == '+') numeros.push(a + b);
            else if (op == '-') numeros.push(a - b);
            else if (op == '*') numeros.push(a * b);
            else if (op == '/') {
                if (b == 0) { // Si se intenta dividir por cero, se lanza una excepción
                    throw new EvaluationException("Error de evaluación: división por cero.");
                }
                numeros.push(a / b);
            }
        }
        //Devolver el resultado final, que es el último elemento en la pila de números
        return numeros.pop();
    }
}
//Ejercicio 1: Contar número de caracteres, palabras y líneas de un texto.

package main

import (
	"bufio"   // Para leer del teclado
	"fmt"     // Para imprimir
	"os"      // Para poder usar el teclado
	"strings" // Para manipular strings (split, fields)
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	fmt.Println("Ingrese el texto (Ctrl+Z para salir):")

	var text string
	for scanner.Scan() {
		text += scanner.Text() + "\n"
	}

	charCount := len(text)
	wordCount := len(strings.Fields(text))
	lineCount := len(strings.Split(text, "\n")) - 1

	fmt.Printf("Caracteres: %d\nPalabras: %d\nLíneas: %d\n", charCount, wordCount, lineCount)
}

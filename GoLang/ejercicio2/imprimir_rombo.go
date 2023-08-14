/*Ejercicio 2: Imprimir la figura de un rombo con asteriscos de la manera más eficiente.
		  *
       *  *  *
	*  *  *  *  *
       *  *  *
		  *
*/

package main

import (
	"fmt"
)

func drawShape(centerLine int) {
	if centerLine%2 == 0 || centerLine <= 0 {
		fmt.Println("El número debe ser impar positivo.")
		return
	}

	// Parte superior del rombo
	for i := 1; i <= centerLine; i += 2 {
		spaces := (centerLine - i) / 2
		for j := 0; j < spaces; j++ {
			fmt.Print(" ")
		}
		for j := 0; j < i; j++ {
			fmt.Print("*")
		}
		fmt.Println()
	}

	// Parte inferior del rombo
	for i := centerLine - 2; i >= 1; i -= 2 {
		spaces := (centerLine - i) / 2
		for j := 0; j < spaces; j++ {
			fmt.Print(" ")
		}
		for j := 0; j < i; j++ {
			fmt.Print("*")
		}
		fmt.Println()
	}
}

func main() {
	centerLine := 5
	drawShape(centerLine)
}

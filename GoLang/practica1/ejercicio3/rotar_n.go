//Ejercicio 3: rotación en arrays con funcion que recibe puntero a array, cantidad de movimientos y direccion
//0: izquierda, 1: derecha

package main

import (
	"fmt"
)

func rotate(arr *[]string, rotations int, direction int) {
	length := len(*arr)
	rotations = rotations % length //rotaciones deben estar en el rango

	if direction == 0 { //rotación a la izquierda
		for i := 0; i < rotations; i++ {
			first := (*arr)[0]
			//mover cada elemento una posición a la izquierda
			copy((*arr)[0:], (*arr)[1:])
			(*arr)[length-1] = first // Colocar el primer elemento al final
		}
	} else { //rotación a la derecha
		for i := 0; i < rotations; i++ {
			last := (*arr)[length-1]
			//se mueve cada elemento una posición a la derecha
			copy((*arr)[1:], (*arr)[0:length-1])
			(*arr)[0] = last //poner el último al inicio
		}
	}
}

func main() {
	originalSeq := []string{"a", "b", "c", "d", "e", "f", "g", "h"}
	rotations := []int{3, 2}
	directions := []int{0, 1}

	fmt.Println("Secuencia Original =", originalSeq)

	for i, r := range rotations {
		seq := make([]string, len(originalSeq))
		copy(seq, originalSeq) //crear una copia inmutable del arr original
		rotate(&seq, r, directions[i])

		dir := "izq"
		if directions[i] == 1 {
			dir = "der"
		}

		fmt.Printf("Cantidad de rotaciones = %d\nDirección = %s\nSecuencia final rotada = %v\n\n", r, dir, seq)
	}
}

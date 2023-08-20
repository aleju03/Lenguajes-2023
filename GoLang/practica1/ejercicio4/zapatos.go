//Ejercicio 4: Almacenar inventario de tienda de zapatos usando struct y slice

package main

import (
	"fmt"
)

type calzado struct {
	marca  string
	precio int
	talla  int
}

type inventario struct {
	zapato calzado
	stock  int
}

func venderZapatos(inventario *[]inventario, marca string, talla int) {
	for i, item := range *inventario {
		if item.zapato.marca == marca && item.zapato.talla == talla {
			if item.stock > 0 {
				(*inventario)[i].stock--
				fmt.Printf("Zapato vendido: Marca: %s, Talla: %d, Precio: %d, Stock restante: %d\n", item.zapato.marca, item.zapato.talla, item.zapato.precio, item.stock-1)
				return
			} else {
				fmt.Println("No se puede vender porque ya no hay stock.")
				return
			}
		}
	}
	fmt.Println("Zapato no encontrado en el inventario.")
}

func main() {
	inventario := []inventario{
		{calzado{"Nike", 50000, 42}, 5},
		{calzado{"Adidas", 45000, 40}, 3},
		{calzado{"Reebok", 40000, 38}, 4},
		{calzado{"Puma", 48000, 44}, 2},
	}

	venderZapatos(&inventario, "Nike", 42)
	venderZapatos(&inventario, "Adidas", 40)
	venderZapatos(&inventario, "Nike", 42)
	venderZapatos(&inventario, "Reebok", 38)
	venderZapatos(&inventario, "Nike", 42)

	venderZapatos(&inventario, "Nike", 42)
	venderZapatos(&inventario, "Nike", 42)
	venderZapatos(&inventario, "Nike", 42)

}

package main

import (
	"fmt"
	"sort"
)

type producto struct {
	nombre   string
	cantidad int
	precio   int
}
type listaProductos []producto

var lProductos listaProductos

const existenciaMinima int = 10 //la existencia mínima es el número mínimo debajo de el cual se deben tomar eventuales desiciones

func (l *listaProductos) agregarProducto(nombre string, cantidad int, precio int) {
	prod, err := l.buscarProducto(nombre)
	if err == 0 {
		indice := -1
		for i, p := range *l {
			if p.nombre == prod.nombre {
				indice = i
				break
			}
		}
		if indice != -1 {
			// Si el producto ya existe
			(*l)[indice].cantidad += cantidad
			(*l)[indice].precio = precio
		}
	} else {
		// Si el producto no existe en la lista
		*l = append(*l, producto{nombre: nombre, cantidad: cantidad, precio: precio})
	}
}

func (l *listaProductos) buscarProducto(nombre string) (producto, int) {
	for _, prod := range *l {
		if prod.nombre == nombre {
			return prod, 0
		}
	}
	return producto{}, 1
}

func (l *listaProductos) venderProducto(nombre string, cantidad int) {
	prod, err := l.buscarProducto(nombre)
	if err == 0 {
		indice := -1
		for i, p := range *l {
			if p.nombre == prod.nombre {
				indice = i
				break
			}
		}
		if indice != -1 {
			(*l)[indice].cantidad -= cantidad
			// Remove product from list if quantity is 0 or less
			if (*l)[indice].cantidad <= 0 {
				(*l) = append((*l)[:indice], (*l)[indice+1:]...)
				fmt.Println("Producto", nombre, "agotado y eliminado de la lista.")
			}
		}
	} else {
		fmt.Println("Producto no encontrado.")
	}
}

func llenarDatos() {
	lProductos.agregarProducto("arroz", 15, 2500)
	lProductos.agregarProducto("frijoles", 4, 2000)
	lProductos.agregarProducto("leche", 8, 1200)
	lProductos.agregarProducto("café", 12, 4500)
}

func (l *listaProductos) listarProductosMínimos() listaProductos {
	var productosMinimos listaProductos
	for _, prod := range *l {
		if prod.cantidad < existenciaMinima {
			productosMinimos = append(productosMinimos, prod)
		}
	}
	return productosMinimos
}

func (l *listaProductos) agregarVariosProductos(productos ...producto) {
	for _, prod := range productos {
		l.agregarProducto(prod.nombre, prod.cantidad, prod.precio)
	}
}

func (l *listaProductos) modificarPrecioProducto(nombre string, nuevoPrecio int) {
	prod, err := l.buscarProducto(nombre)
	if err == 0 {
		indice := -1
		for i, p := range *l {
			if p.nombre == prod.nombre {
				indice = i
				break
			}
		}
		if indice != -1 {
			(*l)[indice].precio = nuevoPrecio
		}
	} else {
		fmt.Println("Producto no encontrado.")
	}
}

// De aqui para abajo es parte 2

// a. func para aumentar el inventario de los productos que estén por debajo del mínimo
func (l *listaProductos) aumentarInventarioDeMinimos() {
	for i, prod := range *l {
		if prod.cantidad < existenciaMinima {
			// Calcular la cantidad necesaria para alcanzar el stock mínimo
			cantidadNecesaria := existenciaMinima - prod.cantidad
			(*l)[i].cantidad += cantidadNecesaria
		}
	}
}

// b. ↓ Custom type para sortear por los campos "nombre", "cantidad" y "precio"
type By func(p1, p2 *producto) bool

func (by By) Sort(productos listaProductos) {
	sorter := &productSorter{
		productos: productos,
		by:        by,
	}
	sort.Sort(sorter)
}

type productSorter struct {
	productos listaProductos
	by        func(p1, p2 *producto) bool
}

func (s *productSorter) Len() int {
	return len(s.productos)
}

func (s *productSorter) Swap(i, j int) {
	s.productos[i], s.productos[j] = s.productos[j], s.productos[i]
}

func (s *productSorter) Less(i, j int) bool {
	return s.by(&s.productos[i], &s.productos[j])
}

func (l *listaProductos) ordenarPorCampo(campo string) {
	switch campo {
	case "nombre":
		By(func(p1, p2 *producto) bool {
			return p1.nombre < p2.nombre
		}).Sort(*l)
	case "cantidad":
		By(func(p1, p2 *producto) bool {
			return p1.cantidad < p2.cantidad
		}).Sort(*l)
	case "precio":
		By(func(p1, p2 *producto) bool {
			return p1.precio < p2.precio
		}).Sort(*l)
	default:
		fmt.Println("Campo no válido para ordenamiento.")
	}
}

func main() {
	fmt.Println("Inicializando lista de productos...")
	llenarDatos()
	fmt.Println(lProductos)

	fmt.Println("\nAgregando productos existentes y nuevos...")
	lProductos.agregarProducto("arroz", 5, 2600)  // Incrementa cantidad y cambia precio
	lProductos.agregarProducto("azúcar", 9, 1800) // Nuevo producto
	fmt.Println(lProductos)

	fmt.Println("\nAgregando múltiples productos a la vez...")
	prod1 := producto{nombre: "sal", cantidad: 10, precio: 700}
	prod2 := producto{nombre: "aceite", cantidad: 5, precio: 5000}
	lProductos.agregarVariosProductos(prod1, prod2)
	fmt.Println(lProductos)

	fmt.Println("\nVendiendo productos...")
	lProductos.venderProducto("arroz", 3)
	lProductos.venderProducto("leche", 8) // Debería eliminar leche de la lista
	fmt.Println(lProductos)

	fmt.Println("\nModificando precio de un producto...")
	lProductos.modificarPrecioProducto("frijoles", 2100)
	fmt.Println(lProductos)

	fmt.Println("\nListando productos con existencias mínimas...")
	productosMinimos := lProductos.listarProductosMínimos()
	fmt.Println(productosMinimos)

	//Parte 2
	fmt.Println("\n--------------------------Parte 2----------------------------")
	fmt.Println("\nAumentando inventario de productos con mínimas existencias...")
	lProductos.aumentarInventarioDeMinimos()
	fmt.Println(lProductos)

	fmt.Println("\nOrdenando productos por nombre...")
	lProductos.ordenarPorCampo("nombre")
	fmt.Println(lProductos)

	fmt.Println("\nOrdenando productos por cantidad...")
	lProductos.ordenarPorCampo("cantidad")
	fmt.Println(lProductos)

	fmt.Println("\nOrdenando productos por precio...")
	lProductos.ordenarPorCampo("precio")
	fmt.Println(lProductos)
}

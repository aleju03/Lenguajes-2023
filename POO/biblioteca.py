from datetime import datetime

# Clase para representar un libro
class Libro:
    def __init__(self, codigo, titulo, autor):
        self.codigo = codigo
        self.titulo = titulo
        self.autor = autor
        self.disponible = True
        self.ubicacion = "Biblioteca"

    def prestar(self, socio):
        self.disponible = False
        self.ubicacion = f"Prestado a socio {socio.numero} ({socio.nombre})"
    
    def devolver(self):
        self.disponible = True
        self.ubicacion = "Biblioteca"

# Clase para representar un socio
class Socio:
    def __init__(self, numero, nombre, direccion):
        self.numero = numero
        self.nombre = nombre
        self.direccion = direccion
        self.libros_prestados = []

    def tomar_prestado(self, libro):
        self.libros_prestados.append(libro)
        libro.prestar(self)

    def devolver_libro(self, libro):
        self.libros_prestados.remove(libro)
        libro.devolver()

# Clase para representar un préstamo
class Prestamo:
    def __init__(self, libro, socio):
        self.libro = libro
        self.socio = socio
        self.fecha = datetime.now()

# Función para demostrar el sistema
def demostracion_biblioteca():
    # Crear algunos libros y socios
    libro1 = Libro("L1", "1984", "George Orwell")
    libro2 = Libro("L2", "To Kill a Mockingbird", "Harper Lee")
    libro3 = Libro("L3", "The Great Gatsby", "F. Scott Fitzgerald")
    libro4 = Libro("L4", "Moby-Dick", "Herman Melville")
    libro5 = Libro("L5", "Don Quijote", "Miguel de Cervantes")
    libro6 = Libro("L6", "War and Peace", "Leo Tolstoy")

    socio3 = Socio("S3", "Charlie", "789 Oak St")
    socio1 = Socio("S1", "Alice", "123 Main St")
    socio2 = Socio("S2", "Bob", "456 Elm St")
    
    # Realizar algunos préstamos
    socio1.tomar_prestado(libro1)
    socio1.tomar_prestado(libro2)
    socio1.tomar_prestado(libro5)
    socio1.tomar_prestado(libro6)
    socio2.tomar_prestado(libro3)
    socio3.tomar_prestado(libro4)
    
    # Lista de todos los libros y socios para facilitar la gestión
    todos_los_libros = [libro1, libro2, libro3, libro4, libro5, libro6]
    todos_los_socios = [socio1, socio2, socio3]
    
    # Mostrar el estado de los libros
    for libro in todos_los_libros:
        print(f"Estado de '{libro.titulo}': Disponible = {libro.disponible}, Ubicación = {libro.ubicacion}")

    # Filtrar y mostrar socios con más de 3 libros prestados
    socios_con_mas_de_3_libros = list(filter(lambda s: len(s.libros_prestados) > 2, todos_los_socios))
    print(f"--------------------------------------------------------\nSocios con más de 3 libros prestados: {[s.nombre for s in socios_con_mas_de_3_libros]}")

# Ejecutar la función de demostración
demostracion_biblioteca()
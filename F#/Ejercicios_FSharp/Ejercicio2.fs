module Ejercicio2
//Ejercicio 2: Dada una lista de cadenas y una subcadena, devolver una lista con las cadenas que contienen la subcadena.

let sub_cadenas (sub: string) (lista: string list) =
    // Función auxiliar que verifica si una cadena contiene una subcadena
    let contieneSubcadena (cadena: string) = 
        cadena.Contains(sub)
    
    // Usamos List.filter para filtrar las cadenas que contienen la subcadena
    List.filter contieneSubcadena lista

// Prueba
(*let resultado = sub_cadenas "la" ["la casa"; "el perro"; "pintando la cerca"]

// Imprimir el resultado
printfn "%A" resultado
*)
module Ejercicio4
//Ejercicio 4: Modificar el ejercicio de búsqueda en profundidad visto en clase. Agregar peso

// Función para verificar si un elemento está en una lista
let miembro elem lista =
    List.exists (fun x -> x = elem) lista
    
// Grafo modificado para incluir pesos
let grafo = [
    ("i", [("a", 3); ("b", 6)]);
    ("a", [("i", 3); ("c", 2); ("d", 5)]);
    ("b", [("i", 6); ("c", 4); ("d", 3)]);
    ("c", [("a", 2); ("b", 4); ("x", 7)]);
    ("d", [("a", 5); ("b", 3); ("f", 1)]);
    ("f", [("d", 1)]);
    ("x", [("c", 7)])
]

// Función adaptada para generar vecinos con pesos
let rec vecinos_con_pesos nodo grafo =
    match grafo with
    | [] -> []
    | (head, neighbors) :: rest ->
        if head = nodo then neighbors
        else vecinos_con_pesos nodo rest

// Función adaptada para extender una ruta considerando pesos
let extender_con_pesos (ruta, peso_acumulado) grafo = 
    List.filter
        (fun (r, p) -> r <> [])
        (List.map  (fun (nodo, peso) -> 
            if (miembro nodo (List.map fst ruta)) then ([], 0) 
            else ((nodo, peso)::ruta, peso_acumulado + peso)) 
        (vecinos_con_pesos (fst (List.head ruta)) grafo)) 

// Función principal adaptada de búsqueda en profundidad considerando pesos
let rec prof2 ini fin grafo =
    let mutable camino_mas_corto = ([], System.Int32.MaxValue)  // ruta, peso_acumulado
    let rec prof_aux fin rutas grafo =
        if List.isEmpty rutas then
            let ruta, peso_total = camino_mas_corto
            (List.rev (List.map fst ruta), peso_total)
        else
            let ruta_actual, peso_actual = List.head rutas
            if (fst (List.head ruta_actual)) = fin && peso_actual < snd camino_mas_corto then
                camino_mas_corto <- (ruta_actual, peso_actual)
            prof_aux fin ((List.tail rutas) @ (extender_con_pesos (ruta_actual, peso_actual) grafo)) grafo
    prof_aux fin [([(ini, 0)], 0)] grafo
    
(*let ruta, peso_total = prof2 "i" "x" grafo
printfn "Ruta: %A con peso total: %d" ruta peso_total*)
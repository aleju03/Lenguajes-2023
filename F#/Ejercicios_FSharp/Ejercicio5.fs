module Ejercicio5
//Ejercicio 5: Encontrar ruta más corta en un laberinto.

//Función necesaria:
let miembro elem lista =
    List.exists (fun x -> x = elem) lista

// Representación del laberinto como grafo (el mismo laberinto que viene en la práctica)
let grafo_laberinto = [ //[nodo, [vecinos]]
    (1, [7]); (2, [3; 8]); (3, [2; 4; 9]);
    (4, [3; 10]); (5, [6; 11]); (6, [5]);
    (7, [1; 13]); (8, [2; 9]); (9, [3; 8]);
    (10, [4; 16]); (11, [5; 17]); (12, [18]);
    (13, [7; 14]); (14, [8; 13; 15; 20]); (15, [14; 21]);
    (16, [10; 22]); (17, [11; 23]); (18, [12; 24]);
    (19, [25]); (20, [14; 26]); (21, [15; 22])
    (22, [16; 21]); (23, [17; 29]); (24, [18; 30]);
    (25, [19; 31]); (26, [20; 27]); (27, [26; 29]);
    (28, [27; 29; 34]); (29, [23; 28]); (30, [24; 36]);
    (31, [25; 32]); (32, [31; 33]); (33, [32; 34]);
    (34, [28; 33; 35]); (35, [34; 36]); (36, [30; 35])
]

// Función adaptada para generar vecinos en el laberinto
let rec vecinos_laberinto nodo grafo =
    match grafo with
    | [] -> []
    | (head, neighbors) :: rest ->
        if head = nodo then neighbors
        else vecinos_laberinto nodo rest

// Función adaptada para extender una ruta en el laberinto
let extender_laberinto ruta grafo = 
    List.filter
        (fun x -> x <> [])
        (List.map  (fun x -> if (miembro x ruta) then [] else x::ruta) (vecinos_laberinto (List.head ruta) grafo)) 

// Función principal de búsqueda en profundidad para el laberinto
let rec buscar_ruta_laberinto ini fin grafo =
    let rec buscar_aux fin ruta grafo =
        if List.isEmpty ruta then []
        elif (List.head (List.head ruta)) = fin then
            [List.rev (List.head ruta)] @ buscar_aux fin ((List.tail ruta) @ (extender_laberinto (List.head ruta) grafo) ) grafo       
        else
            buscar_aux fin ((List.tail ruta) @ (extender_laberinto (List.head ruta) grafo)  ) grafo
    buscar_aux fin [[ini]] grafo
    
(*// Encontrar la ruta más corta
let rutas = buscar_ruta_laberinto 2 32 grafo_laberinto
let ruta_mas_corta = List.minBy (fun x -> List.length x) rutas

//Probar
printfn "Todas las rutas desde el inicio hasta el final son:"
rutas |> List.iter (fun ruta -> printfn "%A" ruta)

printfn "\nLa ruta más corta es:"
printfn "%A" ruta_mas_corta*)

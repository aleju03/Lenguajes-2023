open Ejercicio1
open Ejercicio2
open Ejercicio3
open Ejercicio4
open Ejercicio5
[<EntryPoint>]
let main _ =
    // Prueba del Ejercicio 1
    printfn "Ejercicio 1: desplazar (SHIFT) una lista de elementos n posiciones a la izquierda o la derecha según se indique por argumento"
    let test1 = desplazar "izq" 3 [1;2;3;4;5] //Debería retornar [4;5;0;0;0]
    let test2 = desplazar "der" 2 [1;2;3;4;5] //Debería retornar [0;0;1;2;3]
    let test3 = desplazar "izq" 6 [1;2;3;4;5] //Debería retornar [0;0;0;0;0]

    printfn "%A" test1
    printfn "%A" test2
    printfn "%A" test3
    
    // Prueba del Ejercicio 2
    printfn "-----------------------------------------------------------------------------------------"
    printfn "Ejercicio 2: Dada una lista de cadenas y una subcadena, devolver una lista con las cadenas que contienen la subcadena."
    let resultado = sub_cadenas "la" ["la casa"; "el perro"; "pintando la cerca"]
    printfn "%A" resultado
    
    // Prueba del Ejercicio 3
    printfn "-----------------------------------------------------------------------------------------"
    printfn "Ejercicio 3: Obtener n-esimo elemento de una lista usando solo map (no recursividad)"
    let resultado1 = n_esimo 2 [1;2;3;4;5]
    let resultado2 = n_esimo 3 [1;2;3;4;5]
    let resultado3 = n_esimo 6 [1;2;3;4;5]

    printfn "%A" resultado1
    printfn "%A" resultado2
    printfn "%A" resultado3
   
    // Prueba del Ejercicio 4
    printfn "-----------------------------------------------------------------------------------------"
    printfn "Ejercicio 4: Modificar el ejercicio de búsqueda en profundidad visto en clase. Agregar peso"
        (*
        ("i", [("a", 3); ("b", 6)]);
        ("a", [("i", 3); ("c", 2); ("d", 5)]);
        ("b", [("i", 6); ("c", 4); ("d", 3)]);
        ("c", [("a", 2); ("b", 4); ("x", 7)]);
        ("d", [("a", 5); ("b", 3); ("f", 1)]);
        ("f", [("d", 1)]);
        ("x", [("c", 7)])
    *)
    let ruta, peso_total = prof2 "i" "x" grafo
    printfn "Ruta: %A con peso total: %d" ruta peso_total
    
    // Prueba del Ejercicio 5
    printfn "-----------------------------------------------------------------------------------------"
    printfn "Ejercicio 5: Encontrar ruta más corta en un laberinto."

    let rutas = buscar_ruta_laberinto 2 32 grafo_laberinto
    let ruta_mas_corta = List.minBy (fun x -> List.length x) rutas

    //Probar
    printfn "Todas las rutas desde el inicio hasta el final son:"
    rutas |> List.iter (fun ruta -> printfn "%A" ruta)

    printfn "\nLa ruta más corta es:"
    printfn "%A" ruta_mas_corta
    
    0 // return an integer exit code
module Ejercicio1

//Ejercicio 1: desplazar (SHIFT) una lista de elementos n posiciones a la izquierda o la derecha según se indique por argumento

let desplazar direccion n lista =
    let longitud = List.length lista
    
    // Si n es mayor o igual a la longitud, retorna lista de ceros
    if n >= longitud then
        [for _ in 0 .. (longitud - 1) -> 0]
    else
        match direccion with
        | "izq" -> 
            // Tomar los elementos desde el índice n hasta el final y añadir n ceros al final
            (List.skip n lista) @ [for _ in 0 .. (n - 1) -> 0]
        | "der" -> 
            // Tomar los primeros (longitud - n) elementos y añadir n ceros al principio
            [for _ in 0 .. (n - 1) -> 0] @ (List.take (longitud - n) lista)
        | _ -> 
            // En caso de que la dirección no sea válida, retorna una lista vacía
            []

(*//Pruebas
let test1 = desplazar "izq" 3 [1;2;3;4;5] //Debería retornar [4;5;0;0;0]
let test2 = desplazar "der" 2 [1;2;3;4;5] //Debería retornar [0;0;1;2;3]
let test3 = desplazar "izq" 6 [1;2;3;4;5] //Debería retornar [0;0;0;0;0]

//Imprimir los resultados
printfn "%A" test1
printfn "%A" test2
printfn "%A" test3*)

module Ejercicio3
//Ejercicio 3: Obtener n-esimo elemento de una lista usando solo map (no recursividad)

let n_esimo n lista =
    // Crear lista de índices
    let indices = [0 .. (List.length lista - 1)]
    
    // Combinar lista original con índices
    let lista_combinada = List.zip lista indices
    
    // Mapear lista combinada
    let mapeo = lista_combinada |> List.map (fun (valor, indice) -> 
        if indice = n then Some(valor) else None)
    
    // Filtrar valores None y tomar el primer elemento, o devolver false si no hay ninguno
    match mapeo |> List.choose id |> List.tryHead with
    | Some valor -> box valor
    | None -> box false

(*// Pruebas
let resultado1 = n_esimo 2 [1;2;3;4;5]
let resultado2 = n_esimo 3 [1;2;3;4;5]
let resultado3 = n_esimo 6 [1;2;3;4;5]

// Imprimir resultados
printfn "%A" resultado1
printfn "%A" resultado2
printfn "%A" resultado3 *)
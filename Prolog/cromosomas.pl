%Base de datos
persona('Pedro', 'Perez', '1100100101').
persona('Ana', 'Lopez', '1010101010').
persona('Carlos', 'Gonzalez', '0011001100').
persona('Maria', 'Rodriguez', '1110001110').
persona('Juan', 'Martinez', '0001110000').
persona('Sofia', 'Fernandez', '0101010101').
persona('Jose', 'Sanchez', '0010101010').
persona('Laura', 'Garcia', '1111110000').
persona('Miguel', 'Hernandez', '0000001111').
persona('Sara', 'Morales', '1100110011').

comparar_cromosomas(C1, C2, Similaridad) :-
    string_chars(C1, L1),
    string_chars(C2, L2),
    comparar_listas(L1, L2, Contador, 0),
    Similaridad is (Contador / 10) * 100.

comparar_listas([], [], Contador, Contador).
comparar_listas([H1|T1], [H2|T2], Contador, Acum) :-
    ( H1 = H2 -> NuevoAcum is Acum + 1 ; NuevoAcum is Acum ),
    comparar_listas(T1, T2, Contador, NuevoAcum).

persona_mas_parecida(Muestra, NombreCompleto, Similaridad) :-
    findall(S-(N-A), (persona(N, A, C), comparar_cromosomas(Muestra, C, S)), Lista),
    sort(Lista, ListaOrdenada),
    reverse(ListaOrdenada, [Similaridad-(Nombre-Apellido)|_]),
    atom_concat(Nombre, ' ', Temp),
    atom_concat(Temp, Apellido, NombreCompleto).

mostrar_persona_mas_parecida(Muestra) :-
    persona_mas_parecida(Muestra, NombreCompleto, Similaridad),
    write('Nombre: '), write(NombreCompleto), nl,
    write('Similaridad: '), write(Similaridad), nl.

%Consulta: mostrar_persona_mas_parecida('1100100101').
%Devolvería la persona con el cromosoma mas parecido a '1100100101' y su % de similitud.
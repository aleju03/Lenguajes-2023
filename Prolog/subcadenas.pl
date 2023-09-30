% Predicado que verifica si una lista es sublista de otra lista
sublista([], _).
sublista([H|T], [H|Resto]) :-
    sublista(T, Resto).
sublista(Sub, [_|Resto]) :-
    sublista(Sub, Resto).

% Predicado que filtra las cadenas que contienen la subcadena dada
sub_cadenas(_, [], []).
sub_cadenas(Sub, [H|T], [H|Filtradas]) :-
    string_chars(H, HChars),
    string_chars(Sub, SubChars),
    sublista(SubChars, HChars),
    sub_cadenas(Sub, T, Filtradas).
sub_cadenas(Sub, [_|T], Filtradas) :-
    sub_cadenas(Sub, T, Filtradas).

%sub_cadenas("la", ["la casa", "el perro", "pintando la cerca"], Filtradas).
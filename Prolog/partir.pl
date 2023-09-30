partir([], _, [], []).
partir([H|T], Umbral, [H|Menores], Mayores) :-
    H < Umbral,
    partir(T, Umbral, Menores, Mayores).
partir([H|T], Umbral, Menores, [H|Mayores]) :-
    H > Umbral,
    partir(T, Umbral, Menores, Mayores).
partir([H|T], Umbral, Menores, Mayores) :-
    H =:= Umbral,
    partir(T, Umbral, Menores, Mayores).

%partir([2,7,4,8,9,1], 6, Menores, Mayores).
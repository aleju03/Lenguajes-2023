sumlist([], 0).
sumlist([H|T], S) :-
    sumlist(T, Rest),
    S is H + Rest.
aplanar([], []).
aplanar([H|T], L2) :- 
    aplanar(H, H2), 
    aplanar(T, T2), 
    append(H2, T2, L2).
aplanar(H, [H]) :- \+ is_list(H).

%aplanar([1,[2,3,[4,5]],6], L2).
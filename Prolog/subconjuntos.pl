miembro(X, [X|_]).
miembro(X, [_|T]) :- miembro(X, T).

subconj(_, []).
subconj(S, [H|T]) :- miembro(H, S), subconj(S, T).

%subconj([1,2,3,4,5], [2,3,5]). % true
%subconj([1,2,3,4,5], [2,3,6]). % false
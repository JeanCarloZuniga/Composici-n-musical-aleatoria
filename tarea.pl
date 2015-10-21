% ------------------------------------------------------------------------------
%--- Tarea programada 1: Prolog
%--- Jean Carlo Zúñiga Madrigal
%--- B27467
%
%--- II - 2015
% -------------------------------------------------------------------------

% -------Los siguientes predicados fueron dados por el profesor---------
nesimo(1,[X|_],X).			% si se busca el primero, devuelve la cabeza;
nesimo(N,[_|R],E) :-			% en otro caso,reduce el contador en 1
   M is N - 1, nesimo(M,R,E).		% y recurre sobre la cola

miembro(X, [V|_]) :- miembro(X,V).	% si X es el elementos de la cabeza,
miembro(X, [_|R]) :-			% es miembro; en otro caso,
   miembro(X, R).			% lo buscamos en la cola

concatena([],L,L).			% concatenar una lista vacÃ­a y L es L;
concatena([X|Xr],Y,[X|Zr]) :-		% si no es vacÃ­a, la cabeza de concatenaciÃ³n
   concatena(Xr,Y,Zr).			% es la de la 1a lista y su cola es la concate-
					% naciÃ³n de la cola de la primera y la segunda
negacion(M,fail) :- M.			% si la meta M es verdadera, falla
negacion(_,true).			% en otro caso (es falsa), sale con éxito

afirmaTodo([]).
afirmaTodo([C|Rc]) :- X = C, afirmalo(X), afirmaTodo(Rc).
afirmalo(X) :- assert(X).

maximo([X],X).				% si solo hay un elemento, es el máximo
maximo([X|Xr],M) :-			% en otro caso,
	maximo(Xr,Y),			% el máximo debe ser el mayor entre
   ((X >= Y, M = X); M = Y), !.



%---------Los siguientes son los predicados de la tarea----------
%
% PREGUNTA 1
%
% posicion/3(+O, +L, -P): Busca todas las apariciones de O en L, las
% devuelve en P
% Ejemplo: posicion(a,[a,b,c,a,b,c,a,b,c], P).
% Resultado: P=0 , P=3, P=6, false.

posicion(X, [X|_], 0).                                   %Caso base: cuando encuentra A en la cabeza de B.
posicion(X, [_|R], T):- posicion(X,R,T1), T is T1 + 1 .  %Llama recursivamente a pocision con la cola de la lista y +1 en el contador.

%
% PREGUNTA 2
% subarbol/3(+O, +A, -S): devuelve la sublista de S en la que se
% encuentre la primera instancia del objeto O en la lista A.
% Ejemplo: subarbol(a,[r,d,f,[f,[y,a]]],T).
% Resultado: T = [y,a]

%forma_lista(+A,+B,-C)/3: recibe una cabeza y una cola, las une.
forma_lista(X,Y,[X|Y]) :- write([X|Y]).

% miembro2(+A, +B, -C)/3: se diferencia de "miembro" en el hecho de que
% en el caso base pregunta por "member" para averiguar si X está en V,
% es decir, V puede ser una lista. Importante lo que hace "forma_lista"
% para lograr el objetivo.
miembro2(X, [V|Q], Y) :- member(X,V), !, forma_lista(V,[],Y).
miembro2(X, [_|R], Y) :- miembro2(X, R, Y).

subarbol(A,[H|T],C) :- miembro2(A,H,C), !;	%con el nuevo "miembro2" se estaría construyendo la sublista.
		    miembro2(A,T,C), !. %con el nuevo "miembro2" se estaría construyendo la sublista.
subarbol(A,[H|T],C) :- subarbol(A, T, C).     %se llama recursivamente con la cola de la lista que representa el árbol.

%
% PREGUNTA 3
% Llama a la función miembro con X y la lista,
% entra en el primer predicado y pregunta si X
% es la cabeza de la lista, si lo es se devuelve todas las llamadas que
% haya hecho a la función hasta llegar a ese resultado y retorna
% verdadero, sino, llama otra vez a la funcion con la cola de la lista.
% Si encuentra el elemento X en la cola, se devolverá hasta la última
% llamada, como mencioné antes, devolverá verdadero e intentará ubicar
% una vez más el elemento X en la parte restante de la cola a partir de
% donde lo encontró antes. Esto debido a que aún a pesar de haber
% encontrado el elemento, la función se volverá a llamar hasta que la
% cola sea [], es decir, vacía.
%

%
% PREGUNTA 4
% El corte que presenta al final el predicado basta para evitar las
% múltiples soluciones.
%

%
% PREGUNTA 5
% Al quitar el corte '!' lo que ocurre es que el backtraking se
% devolverá tantas llamadas recursivas como haya necesitado el programa
% para dar con un resultado, y luego, intentará dar más respuestas de
% acuerdo al predicado que estemos evaluando en 'M', Así pues, si
% llamamos a negacion(miembro(a,[a,b,c,d,e]),X). lo que ocurrirá es que,
% al encontrar 'a' en la lista, "miembro" retornará verdadero y a su vez
% "negación" lo converirá a falso, y luego, como no hay corte, miembro
% seguirá operando con la cola a partir de 'a', es decir [b,c,d,e] y al
% no encontrar a 'a' en esa sublista devo+verá falso, que "negación" lo
% convertirá en verdadero.


%
% PREGUNTA 6
% Llamadas necesarias: 2, las siguientes:
% assert(sub(X,[X|Rx],[X|Rx])).
% assert(sub(X,[_|R],Z) :- sub(X,R,Z)).

%
% PREGUNTA 7
% No eliminó todas las cláusulas, lo modifiqué de la siguiente manera:
% retractaTodo/1(+L) : retracta todas cláusulas contenidas en la lista
% L.
% retri/1(+A) :retracta A.
% Ejemplo: retractaTodo([sub(X,[X|Rx],[X|Rx]),sub(X,[_|R],Z):-sub(X,R,Z)]).
% Resultado: borra los assert del punto 6.

retractaTodo([]).                                            %Caso base: Lista vacía.
retractaTodo([C|Rc]) :- X = C, retri(X), retractaTodo(Rc).   %Retracta la cabeza y se llama recursivamente con la cola de la lista.
retri(X) :- retract(X).                                      %Retracta X.


%
% PREGUNTA 8
% union/3(+A,+B,-C) -> C es la unión de A y B
% Ejemplo: union([a,c,e],[b,d,f],C)
% Resultado: C=[a,b,c,d,e]

union(A,B,C) :- concatena(A,B,F), eliminarRepes(F,C).		     %utiliza "concatena" para unir las dos listas, luego llama a "elmininarRepes".
eliminarRepes([],[]).					     %Caso base: las dos listas vacías.
eliminarRepes([H|T],S):-member(H,T),!,eliminarRepes(T,S).    %pregunta si la cabeza de la lista está en la cola, si lo está se llama recursivamente con la cola y la lista solución.
eliminarRepes([H|T],[H|S]):- eliminarRepes(T,S).             %se llama recursivamente con las dos colas, la de la lista de entrada y la de la lista solución

% interseccion/3(+A,+B,-C) -> C es la intersección de A y B
% Ejemplo: interseccion([a,z,b,x],[c,z,d,x], C)
% Resultado: C = [z,x]

interseccion([],_,[]).						       %Caso base: las listas A y C vacías
interseccion([H|T], B, M) :- not(member(H,B)), !, interseccion(T,B,M). %Pasa si la cabeza de A NO está en B, se llama recursivamente con la cola de A
interseccion([H|T], B, [H|M]) :- interseccion(T,B,M).                  %se llama recursivamente con la cola de A


% diferencia/3(+A,+B,-C) -> C es la diferencia de A y B
% Ejemplo:   diferencia([a,b,c,d,e],[a,e,i,o],P).
% Resultado: P=[b,c,d]

diferencia([],_,[]).                                            %Caso base: las listas A y C vacías.
diferencia([H|T], B, M) :- member(H,B), !, diferencia(T,B,M).   %Pasa si la cabeza de A está en B, se llama recursivamente con la cola de A
diferencia([H|T], B, [H|M]) :- diferencia(T,B,M).               %se llama recursivamente con la cola de A

% potencia/2(+A,-P) -> P es el conjunto potencia de A
% Ejemplo:   potencia([a,b,c],P).
% Resultado: P=[a,b,c] , P=[a,b], P=[a,c], P=[a], P=[b,c], P=[b], P=[c],
% P=[].

potencia([],[]).                                 %Caso base: las dos listas vacías.
potencia([H|T1],[H|T2]) :- potencia(T1,T2).	 %se llama recursivamente con las dos colas de las dos listas
potencia([_|T1],T2) :- potencia(T1,T2).          %se llama recursivamente con las dos colas de las dos listas


%-----------------------Fin tarea programada----------------------------













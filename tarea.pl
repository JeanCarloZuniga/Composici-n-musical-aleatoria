% ------------------------------------------------------------------------------
%--- Proyecto programado: Composici�n musical aleatoria.
%--- Jean Carlo Z��iga Madrigal, B27467.
%--- Brandon Alvarez, BXXXXX
%
%--- II - 2015
% -------------------------------------------------------------------------

%Recibe una nota, un indice y donde quiero la respuesta.
%Encuantra todas las progresiones para la nota dada y las enlista
%Devuelve el contenido del indice especificado por el usuario.
resultado_especifico(Nota, Index, Elem) :-
	findall(Resp, progresion(Nota, Resp), Intermedia),
	nth1(Index, Intermedia ,Elem).

%Recibe una nota y donde quiero la respuesta.
%Devuelve el n�mero de "asserts" para la nota dada.
tamano(Nota, Cantidad) :- aggregate_all(count, progresion(Nota,Y), Cantidad).

% Recibe un entero "Tiempo" m�s peque�o que 8, una "Lista" vac�a: [] y
% un tercer par�metro que devolver� la respuesta.
% El while saca n�meros aleatorios y los anexa a una lista respuesta que
% devolver� luego, la primer l�nea, para Z, es la que dice cuantos
% n�meros aleatorios deber� sacar.
while(Tiempo, Lista, Tercer) :-
	                Z is 8 - Tiempo,
		        random_between(1, Z, R),
			M is Tiempo + R,
			append([R|_], Lista,  Tercer), !,
			(M < 8 -> while(M, Tercer, T), ! ;
			b_setval(time, Tercer)).

% Primero anexa un 1 a la lista respuesta, luego el output del while y
% finalmente un 2. El valor inicial y el final es para efectos de
% armon�a cuando se reproduzca la progresi�n en java.
tiempos(Respuesta) :- append([1], [], Lista),
		      random_between(1, 5, R),
		      Tiempo_w is 3 + R,
		      append(Lista, [R|_], Cons),
		      (Tiempo_w <  8 -> while(Tiempo_w, [], Tercer),
		                      b_getval(time, Temporal),
		                      nb_delete(time),
		                      append(Cons, Temporal, Penultima), !,
				      append(Penultima, [2], Respuesta), !
		                      ;
		                      append(Cons, [2], Respuesta), !).



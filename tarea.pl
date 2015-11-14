% ------------------------------------------------------------------------------
%--- Proyecto programado: Composición musical aleatoria.
%--- Jean Carlo Zúñiga Madrigal, B27467.
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
%Devuelve el número de "asserts" para la nota dada.
tamano(Nota, Cantidad) :- aggregate_all(count, progresion(Nota,Y), Cantidad).

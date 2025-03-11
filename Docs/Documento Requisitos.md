# Documento de Requisitos:
##1-Objetivo principal del proyecto:
- El objetivo de este proyecto es realizar un videojuego a modo de clon de clasicos del genero RPG como los primeros FF, Fire Emblem citando titulos mas puntuales como FF o Fire Emblem Awekening el dezpliegue caera sobre estadares modesto en comparacion con estos titanes del genero ademas de diferir de estos en tematica pues nuestro proyecto "Eclipsis : el legado de la nave abandonada" es una epopeya espacial con tematica oscura.

##2-Requisitos del Sistema:
###Requisitos Funcionales:
- La jugabilidad: debe de ser un sistema por turnos y dezplazamiento en un mapa delimitado por casillas donde los jugadores se encontraran tanto a enemigos como puntos de interes como potenciadores o puntos de descanso
- Diseno de niveles: los diferentes niveles del juego estaran definidos por los diferentes mapas por los cuales los jugadores se podran dezplazar estos niveles deben de aumentar su dificultad a medida que se avanza en el juego ademas de esto cada nivel tendra un jefe final que sera necesario derrotar para pasar a la siguente estancia de juego,ademas de eso se debe de hacer una diferencia entre el "mapa de campana" que sera el mapa de cuadriculas sobre el que nos dezplasaremos en los diferente niveles con "la pantalla de batalla" que solo sucedera cuando hagamos contacto con un enemigo momento en que protagonizaremos un combate por turnos la mejor estilo de los RPGs de antano.
- UI: el juego debe de contar con una interfaz grafica intuitiva y que este en el mismo tono oscuro del juego con un menu de inicio,seleccion de personajes y de configuracion,ademas de un HUD que refleje las diferentes stats de nuestra party.
- Personajes:contaremos con una variedad razonable de personajes tanto de seleccion como enemigos los cuales tendran estadisticas especificas que los diferenciaran del resto(movilidad,vitalidad,fuerza,etc..).
- Arte del Juego:el arte del juego debe de ser de un tono oscuro y opresor con una paleta de colores mas bien apagada acompanada de una banda sonora que recalque esta sensacion
- IA: el juego constara con enemigos que usaran inteligencia artificial para hacer frente a el jugador
- Seleccion de personajes: el jugador podra elegir entre una variedad de personajes hasta 4 con los que conformara su equipo.

##Requisitos no Funcionales:
- el juego debe de estar bien optimizado para poder ejecutarse en practicamente cualquier ordenador ademas de ser intuitivo y divertido para el usuario.

##3-Que arquitectura tendra nuestro sistema?
- Nos basaremos en un diseno orientado a objeto con relaciones de herencia y agregacion ademas de implementar caracterizticas propias de este paradigma tambien usaremos un sistema de entidad-componente donde las entidades describen los elementos dentro de nuestro juego y los componentes sus caracteristicas.


# Objetivo principal

Proponer una solución al actual ejercicio de expertus trivia, que refleje mi manera de afrontar este tipo de retos.

## Ejecución del ejercicio

Como se verá a lo largo de este código, la solución permite configurar diferentes aspectos de la aplicación a través de un fichero de properties, pretendiendo dar más flexibilidad al owner del juego. Por esta razón es necesario especificar el path al directorio que posee al fichero de propiedades que puede estar ubicado en cualquier parte del sistema:
>  -Dtrivia.propertyFile.location=<path_to_resource_directory>
Me he permitido utilizar una clase diferente para lanzar la aplicación, para mi caso será TriviaRunner.
A continuación, explicó los pasos seguidos para desarrollar este ejercicio:
*	Analizar ejercicio Actual
*	Definir el juego de Trivia a implementar
*	Definir BDD para la aplicación
*	Crear nuevas funcionalidades
*	Refactorizar juego de trivia adaptado al juego definido
*	Implementar código

## Analizar el ejercicio actual

Entendiendo que el propósito para este ejercicio se centra en proponer una solución que refleje mi manera de enfocar este problema, he tratado de entender la lógico del juego como se ha definido en este ejercicio.
Entre los diferentes problemas que he encontrado, veo clases muy acopladas con muchas responsabilidades. También echo en falta la facilidad de una simple configuración del juego. Al ser una aplicación orientada a objetos, echo en falta el tablero para jugar, el dado, las preguntas, etc…
Asimismo, el código tiene fallos en el código y aplicar una lógica que a mi entender impide escalar esta aplicación. 

## Definir el juego de trivia a implementar

El juego constará de un tablero compuesto de un camino con cajas, donde cada caja puede tener categorías configurables. Asimismo, algunas cajas pueden ser cajas que penalicen al jugador que cae en ellas. Cada Jugador tendrá la oportunidad de moverse a través del tablero, pero para poder moverse debe de contestar siempre una pregunta del tipo de categoría que posea la caja donde esta el jugador. El jugador que gane es aquel que supere con el movimiento del dado la caja final del tablero de trivia.

## Definir BDD para la aplicación

Los BDD test que deberían ser utilizados como aceptance criteria de la aplicación:
* Given a player movement 
  When movement is on PENALTY_BOX
  Then the player should has a configure penalization, and the player should be notified

* Given a player can move
  When the player reach the end
  Then the player should be notified that is the winner and the game is over 

* Given the player answer a question
  When the answer is right
  Then the player should continue and move again
 
## Crear nuevas funcionalidades 

A continuación defino dos funcionalidades que no encuentro en el código actual:
* [ ExpTriv-1 ] – As a Game owner 
                    I want to configure the board to play Trivia
                   So that I can set the board size, where the penalties boxes should be, and what are the categories assigned to the board boxes
 * [ ExpTriv-2 ] – As a Gamer 
       	     I want to see my movement during the trivia game
                   So I have to see my board position, my alias name and all information to can play
     

## Refactorizar juego de trivia adaptado al juego definido

Teniendo en cuenta el tiempo sugerido para realizar esta propuesta (2 horas aprox) decidí enfocar el juego con el template pattern para permitirme controlar la ejecución del juego. Asimismo, he creado los siguientes objetos para poder implementar el juego definido arriba:
* Dice:  dado para el juego, puede ser configurable en el fichero de propiedades de la aplicación
Player: jugador. Se han utilizado los tres jugadores actuales, en un futuro se podría mejorar para permitir n numero de jugadores.
* Question: pregunta a realizar a los jugadores. Las preguntas pueden ser cargadas a través de un csv, de momento solo he cargado 13 preguntas de las categorías que se encontraban en la aplicación
* Rule: defino las reglas del trivia que nos servirán para controlar cuantas casillas se debe penalizar a un jugador que cae en un PENALTY_BOX
* StatePlayer: reflejerá el estado del jugador actual y nos ayudará a definir cuando un jugador gana
* TriviaGame: clase que implemanta el template para cualquier juego. Orquesta el funcionamiento del juego
* TriviaGameTemplate: template definido para este tipo de juego
* TriviaBoard: representa el tablero donde se jugara al trivia
* TriviaState: para futuras implementaciones por si se quiere mantener el estado actual del juego para pausarlo
* TriviaStateHolder: para futuras implementaciones por si se quiere mantener el estado actual del juego para pausarlo 

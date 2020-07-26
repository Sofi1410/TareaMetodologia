<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).
**This project assumes:**

A Unit(Boss,Wild,Player) HP,Victories and Stars are always >0

A Unit attributes (atk,edv,def) are ints , so it could we >0 or <0.

For the moment its not necessary to implement the whole combat between two Units, 
thats why the methods increaseStarsBy and increaseVictoriesBy are not 
connected to the methods attack,avoid and defend, however the currentHp 
is connected with those methods,so you **_can simulate the combat like 
the Test of Player,WildUnit and BossUnit does_**.

All the panels has at least one NextPanel. 



**To run the program:**

Just select the test and run. In the Unit Tests are some default Units like shifuRobot
,chicken, suguri and others so if you want to ,you can adjust this default Units taking care 
of the copyTest(), cause if you change these Units you have to change the expected copy.

For the controllerTest you have to ve aware of the union between panels, if you run the test 
all of them will pass but if you make changes you have to be very carefull.

Para la interfaz, los jugadores y las imágenes se asocian en el orden como :

NomNom-Koala

Polar-Oso Blanco

Panda-Oso Panda

Pardo-Oso Café

Por temas de tiempo no todo ha sido implementado sugiero , jugar recolectando puntos
y escogiedo quedarse en casa. Los Encouter y Boss Panel no fueron implementados.


**Project explanation**

***Panel***
In this project are many types of Panels that works ver similar because of this all 
of them are subclass of the abstract class AbstractPanel , in this way the class 
define the general behaviour of a Panel and each on activates its especial function 
by the abstract method activatedBy().  

***Unit***
In the project exist three types of Unit, Player, WildUnit and BossUnit. These
units had common functions like increaseStars, or the constructor,roll, and others.
Because of this we created the class AbstractUnit. Also exists an Interface Unit witch
allows to created methods that received a Unit in the abstract class,but we don`t 
know for sure witch one is.     
  
***Controller***
The idea of the controller is to manage all the game so it use all the methods in 
IPanel and IUnit , making fluid steps in the situacions of the panel.
For example: When you create a panel you have to add it in the list of all the 
panels in the game, is not a good practice to do that in the IPanel cause is not 
respecting the singularityy function principle. Thats why when you create a panel 
using the controller, this one adds it to the list.
The last examples aplys for Players, Enemy Units, and all the clases in the game.

***Phases***
Las fases representan las transiciones del juego son extremadamente útiles para lo que hará 
el usuario, la idea al conectarlo con la interfaz gráfica sera asumir que las intrucciones del
usuario pueden estar incorrectas por ello cuando se decide pelear, avanzar, etc el controllador
tiene un metodo que intenta realizar la acción , esto lo hace asegurando que esté en una fase 
correcta para tomar esa decisión, por ejemplo, no puedo pelear si estoy en WaitHome.

***Interfaz***
Utilizando el modelo Vista controlador se opera en la interfáz gráfica tal que esta 
nunca llama al modelo, poor esta razon se creó el escenario en el controlador , como un
método ( controller.escenario()). El inconveniente con la interfaz gráfica era mover a 
los jugadores sin llamar al  modelo, por esa razon se utilizan la clase MovableNode
que permite mover una imagen una cantidad específica de pixeles a el lado que se 
le indique.Por esta razon se crean botones que activan las funciones en las que el 
jugador decide moverlo a un lado y a la vez utiliza la clase previamente mencionada 
para mover la imagen, de esta forma la imagen y el jugador o se relaciona directamente. 
Para ejemplificar si presiono el botón Up en panda, la acción llamada será controller.tryToGoUp
y luego panda.moveUp. Por lo anterior se conectan todos los paneles, tal que el jugador siempre 
a qué lugar quiere ir, porque de otra forma(en este momento) no se podría mover el  jugador en
 la interfaz gráfica. Pero de todas modas se deja el observer para que cuando se pueda hacer la 
 relación el código sea extensible.
 
**Logic**

In the whole project the logic is to respect the Solid Principles:

When exist types of something with common behaviour , and the relation between 
 has sense,an abstract class is a very good way to build the project. 

All the methods were made to accept changes, for example if the stars wining in a battle
where a Player wins to another Player changes , the only change you would have to make
is in one line in the method increaseStarsByPlayer(Player player) in the Player class. 

Each functionality has its own method.

All the methods in Abstract Unit and AbstractPanel apply to their subclasses.

When you need to watch a event like, when you have to see who came first in a race
and you can´t see it you ask someone to advice you, that is exactly what we do
using Observer , when on of the players in the game raise his norma level , the observer notice
and when one of them achieve normaLevel=6 that means that is the winner of the game.

Es fundamental notar que las fases filtraran las decisiones del juego y le
daran estabilidad a este mismo, sin las fases se hace muy complejo analizar las decisiones
del usuario y si tienen coherencia con el juego.Es importante que cada botón sea processado como un
intento en el controlador, que lo derive a la fase y si se encuentra en la fase correcta que efectue
lo que se pide.

El modelo vista controlador, debe ser constantemente analizado para el funcionamiento correcto
de la interfaz.




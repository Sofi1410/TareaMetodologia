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
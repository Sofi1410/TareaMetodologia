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


**To run the program:**

Just select the test and run. In the Unit Tests are some default Units like shifuRobot
,chicken, suguri and others so if you want to ,you can adjust this default Units taking care 
of the copyTest(), cause if you change these Units you have to change the expected copy.

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
  

**Logic**

In the whole project the logic is to respect the Solid Principles:

When exist types of something with common behaviour , and the relation between 
 has sense,an abstract class is a very good way to build the project. 

All the methods were made to accept changes, for example if the stars wining in a battle
where a Player wins to another Player changes , the only change you would have to make
is in one line in the method increaseStarsByPlayer(Player player) in the Player class. 

Each functionality has its own method.

All the methods in Abstract Unit and AbstractPanel apply to their subclasses.
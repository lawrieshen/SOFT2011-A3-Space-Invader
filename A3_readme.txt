# SOFT2011-A3-Space-Invader

Hi there, this application is a Space Invader game for the SOFT2201 Software Design And Construction 1 unit.

To start the application, please run "gradle clean run" in the terminal. In addition, please make sure that you have
implemented the dependency for javafx correctly.

In this assignment, I have implemented the following features, coupled with corresponding design patterns:
1. Difficulty Level [Singleton Design Pattern]
2. Time and Score [Observer Design Pattern]
3. Undo and Cheat [Memento Design Pattern]

These design patterns have been extended in the following classes:
1. Singleton Design Pattern:
    - DifficultyLevelMenuWindow class @ DifficultyLevelMenuWindow.java [Singleton]
    - App class @ App.java [Client]
2. Observer Design Pattern:
    - Subject interface class @ Subject.java [Subject]
    - GameScore and GameTime classes @ GameScore.java and GameTime.java [Concrete Subject]
    - Observer interface class @ Observer.java [Observer]
    - SystemStats class @ SystemStats.java [Concrete Observer]
3. Memento Design Pattern:
    - BunkerMemento, EnemyMemento, EnemyProjectileMemento, GameScoreMemento, GameTimeMemento, PlayerMemento classes @
        BunkerMemento.java, EnemyMemento.java, EnemyProjectileMemento.java, GameScoreMemento.java, GameTimeMemento.java,
        PlayerMemento.java [Memento]
    - BunkerCaretaker, EnemyCaretaker, EnemyProjectileCaretaker, GameScoreCaretaker, GameTimeCaretaker, PlayerCaretaker
        @ BunkerCaretaker.java, EnemyCaretaker.java, EnemyProjectileCaretaker.java, GameScoreCaretaker.java,
        GameTimeCaretaker.java, PlayerCaretaker.java [Caretaker]
    - Caretaker interface @ Caretaker.java [marker interface]
    - Bunker, Enemy, EnemyProjectile, GameScore, GameTime, Player classes @ Bunker.java, Enemy.java, EnemyProjectile.java,
        GameScore.java, GameTime.java, Player.java

After you start the application with th command "gradle clean run", the software will prompt ypu and difficulty level
selection window. At the top of the window there will be a menu bar fo you to make the selection. Click on the "Difficulty" label,
then there will be three drop-down option for you select. Select the difficulty level you wanted, then the software will close
the difficulty selection window and launch the game window with corresponding configuration.

Regarding the Undo feature, the state of the game will be automatically saved everytime when a player shoots. Simply click on
the Undo button on the screen to undo the previous shot. To select different kind of cheat codes, just click on the Cheat button and
select the cheat option you wanted.

The game will be ended if all aliens are eliminated or the player is dead.
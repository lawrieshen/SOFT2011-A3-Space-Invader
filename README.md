# SOFT2011-A3-Space-Invader

Difficulty Level
There are now three difficulty levels in your game including easy, normal and hard, which correspond to configuration files config_easy.json Download config_easy.json, config_medium.json Download config_medium.jsonand config_hard.json Download config_hard.json, respectively.
Note: These json configuration files has alreayd been included under 'src/main/resources/' folder in your A3 Codebase.
Only a single instance of each of the level difficulties should exist within the game.
The player can choose a level either by clicking on a button or by selecting from a menu or by clicking on a keyboard key (i.e., you ONLY need to implement this feature through one of these three ways).
You can set the easy level as the default level displayed to the player OR you can ask the player to choose a level before the start of a game.
Attention Please: you are free to change the values in the sample JSON files whereas you are not allowed to change the structure of the JSON files (i.e., no added, no deleted).
Time and Score
Duration of the game is clocked until all aliens are destroyed (i.e., game wins) or the player dies (i.e., game loses). The game must display on the screen a continually updating time (initially at 0:00).
The score is calculated when the player's projectile hit a alien or a alien's projectile. The game must display on the screen an updating score (initially at 0) when the player's projectile hit something during the level.
The alien/projectile and its corresponding score after each hit can be found in the table:
Enemy Entity	slow projectile	fast projectile	slow alien	fast alien
Score	1	2	3	4

Undo and Cheat (Memento)
The player can reset the game to an earlier state (including score, time, alien's position and alien projectile's position) so that a shot can be undo.
This undo functionality can be triggered by button, menu or keyboard action (i.e., you ONLY need to implement this feature through one of these three ways).
This must be a single state that is not written to disk, and the state reaching by the subsequent undo function overwrites the existing saved state (We only save one state at all time).
The player can do a cheating operation to remove all aliens projectile of the same type or all the alien's who have the same strategy immediately.
This functionality can be triggered by button, menu or keyboard action (i.e., you ONLY need to implement this feature through one of these three ways).
Take keyboard action as an example only: pressing the key 'p' on the keyboard will immediately remove all slow projectile on the screen at the cheating moment and add corresponding scores (i.e., 1 score X the number of slow projectile on the screen at the cheating moment).
Note: You need to implement both cheating options, but the player can execute only one cheating option at a time.
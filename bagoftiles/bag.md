##Bag of Tiles
http://open.kattis.com/problems/bagoftiles  
Solution author: Finn Lidbetter, Micah Stairs  
Solution date: February 1st, 2017  

Solution:  
This problem yields a simple Dynamic Programming solution. A state in the Dynamic Programming lookup table is defined by: the number of tiles that have been considered, the number of tiles being used in a sum, and the value that the numbers sum to. For each of these states we store the number of ways that the state can be reached. The number of wins will be the number of ways that the target value can be summed to using the required number of tiles after all tiles have been considered. To calculate the number of losses, we observe that any game that isn't a win is a loss, and there are *mCn* possible games. So there will be *mCn - nWins* losses.

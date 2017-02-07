##Frogger  
http://open.kattis.com/problems/frogger    
Solution author: Finn Lidbetter  
Solution date: February 7th, 2017  

Solution:  
This problem can be solved using a Breadth-First-Search of the state space. We can model the positions of the cars at a particular time step using a long where a 1 represents a car and a 0 represents an empty space. This will work because each car lane has *m* columns and so there are only *m* different car positions for each lane. So a state will be represented by the lane number of the frog, the column number of the frog, and the time step mod *m*. The BFS is then performed as usual. I learned from this problem that I need to be more careful when using bit shift operations with longs. I should use 1L &lt;&lt;m rather than 1&lt;&lt;m if the resulting value is going to be used with a long.

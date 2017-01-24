##Liga
http://open.kattis.com/problems/liga  
Solution author: Micah Stairs, Finn Lidbetter  
Solution date: 11/01/17  

Solution:  
The solution to this problem takes advantage of the relatively small input size and the fact that the number of wins, draws, and losses will uniquely determine the other 2 statistics. We know that there are no more than 100 games played. So we can easily loop through all possibilities for wins, draws, and losses in order to form a lookup table that would tell us the answer when only number of games played and/or number of points are given. We are told that for the input there will be a unique solution, so we don't have to worry about information that gets written twice if there are 2 scenarios that give the same number of points and/or games played. This handles the cases where none of Wins, Draws, or Losses are given. For the cases where at least one of Wins, Draws, and Losses is given we iterate through all possible values for the missing information on Wins, Draws, and Losses until we find a set of values that is consistent with the other information that we have available. Again, since we are told that the solution is unique we can stop at the first consistent set of values that we find.  
The complexity of this solution is *O(n<sup>3</sup>)*, and we have that *n<=100* so this solution is certainly efficient enough.

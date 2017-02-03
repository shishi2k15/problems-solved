##Shuffling Along
http://open.kattis.com/problems/shuffling  
Solution author: Finn Lidbetter  
Solution date: February 1st, 2017  

Solution:  
In order to determine the number of shuffles required, we can look at the number of shuffles required to return a specific card to its original position. If we determine this for all cards, then we can take the least common multiple of these cycle lengths to get the answer that we are looking for. An easy way to achieve this is to use the union find data structure and have one element for each position that a card can be in. We then put 2 cards in the same set if one card will go to the other's position after one shuffle. After setting up the union find data structure, we can then go through and take the least common multiple of the lengths of each of the union find set sizes.

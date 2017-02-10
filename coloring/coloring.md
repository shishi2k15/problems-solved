##Coloring  
http://open.kattis.com/problems/coloring  
Solution author: Finn Lidbetter  
Solution date: February 9th, 2017

Solution:  
This problem can be solved using recursive backtracking. We try each possible value for the number of colours from 2 to 11 and stop at the first solution we find. For each vertex we try assigning each possible colour to that vertex (provided that it doesn't create a bad colouring) and then recurse by trying to colour the next vertex. If at any point we are unable to colour a vertex, we backtrack and try a different colour for some earlier vertex. If no colouring exists using the amount of colours in consideration, we try using more colours.

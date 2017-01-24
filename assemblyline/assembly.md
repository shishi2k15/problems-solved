##Assembly Line
http://open.kattis.com/problems/assemblyline  
Solution author: Finn Lidbetter  
Solution date: 23/01/17  

Solution:  
This problem is very similar to another problem that we recently saw (http://open.kattis.com/problems/mixingcolours) and so I was able to quickly identify the following solution. Since we are only able to combine letters that are adjacent in the sequence we have to find the best order in which to combine the letters. This can be done using a Dynamic Programming solution where we consider each possible subsequence and for each subsequence we consider each possible split point and then for each of these we calculate the smallest cost to produce each possible letter if the letter can be produced at all. Once we have done this for every split point for the last subsequence - the one that considers the entire sequence, we iterate over each of the letters and choose the letter with the lowest cost. This solution has complexity of 200x200x200x26x26x10 for each case which was just fast enough to fit within the 5 second time limit.

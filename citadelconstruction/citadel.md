##Citadel Construction  
http://open.kattis.com/problems/citadelconstruction  
Solution author: Finn Lidbetter  
Solution date: February 11th, 2017  

Solution:  
The first observation necessary to solve this problem is that the 4 (or 3) points that will maximise the area of the citadel must lie on the convex hull of the given points. So the first step is to take the convex hull. From this step we can then consider all non-adjacent pairs of points on the convex hull. For each of these pairs we can use a ternary search to find the points on either side of the line defined by the pair that are the furthest from this line - those that maximise the areas of the triangles on either side of the line. Ternary search is applicable because we know that we are working with a convex polygon, so the distance from the line to the points on the hull must increase and then decrease. Taking the maximum area found from all pairs of points will give the answer.  

##Chasing the Cheetahs
http://open.kattis.com/problems/cheetahs  
Solution author: Finn Lidbetter  
Solution date: February 10th, 2017  

Solution:  
The crucial observation in this problem is that the distance between the furthest ahead cheetah and the furthest behind cheetah is a unimodal function. That is, it may start by decreasing but after it starts increasing, it will only continue to increase. It is easy to see that once the function starts to increase it will continue to increase. This is because if it is increasing then the furthest ahead cheetah must be moving faster than the furthest behind cheetah and this situation can only become greater as time passes. Furthermore, if it the function is deacreasing then we know that there must eventually be a time when the function starts increasing as the speeds of the cheetahs are given by linear equations. Using this information we can simply perform a ternary search in order to find the shortest distance.

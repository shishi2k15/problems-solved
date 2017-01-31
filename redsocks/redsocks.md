##Boastin' Red Socks
http://open.kattis.com/problems/redsocks  
Solution author: Finn Lidbetter  
Solution date: January 31st, 2017  

Solution:  
The solution to this problem is fairly straightforward. We try every possible value for the total number of socks and then check if the corresponding value for the number of red socks forced by the fraction *p/q* is an integer. If *t* is the total number of socks, then we are looking for *r* the number of red socks that satisfies *[r(r-1)]/[t(t-1)]=p/q*. If we reduce the fraction *p/q* then we know that there must be some constant *c* such that *[r(r-1)]/[t(t-1)]=cp/cq*. Since we are trying each possible value for *t* we solve for *c* first and then we can solve the quardatic simple equation: *r<sup>2</sup>-r-cp=0*. If we get that *r* is an integer then we have found the answer.  
Watch out for the special case where *p=0*.

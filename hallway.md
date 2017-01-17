###Decoding the Hallway
http://open.kattis.com/problems/decodingthehallway  
Solution author: Finn Lidbetter  
Solution date: 11/01/17

Solution:  
The first observation to be made is that the string at depth *n* can be constructed by taking the string at depth *n-1* and then appending an *L* and then appending the string at depth *n-1* reversed and with all *L*'s changed to *R*'s and all *R*'s changed to *L*'s.

Given that the string can be constructed in this manner, and that the substring that we are working with has a maximum length of 100, we can obtain the answer through the following method:

Let *s(n)* be the string at depth *n*, and let *b* be the substring that we are trying to find in *s(n)*. Let the 'reverseFlip of *b*' be the string obtained by reversing *b* and switch *L*'s for *R*'s and *R*'s for *L*'s.

*b* will appear either entirely in the first half of *s(n)*, or entirely in the right half of *s(n)*, or it will lie in the middle 201 characters of *s(n)*, or it will not appear in *s(n)* at all.  
If *b* appears entirely in the left half of *s(n)* then it can be found in *s(n-1)*.  
If *b* appears entirely in the right half of *s(n)* then its reverseFlip will be found in *s(n-1)*.  
We can compute the middle 201 characters for each depth of *s(i)*, *i<=n*.  
So check if there is a match for *b* in these 201 characters for *s(n)*.  
If there is no match then we assume that *b* was either entirely in the left half or entirely in the right half of *s(n)* and we proceed to check if *b* or its reverseFlip can be found in *s(n-1)* through the same process.  
If we do find a match for *b* in the middle 201 characters then we stop because we have found that *b* is a substring of *s(n)*.  
If we do not find a match for *b* or its reverseFlip at any depth less than *n* then *b* cannot possibly be a substring of *s(n)*. 

Note that the middle 201 characters for *s(n)* remain constant for *n>10*, since *|s(n)|>201* for *n>10*. As such, for values of *n* greater than 10, we can treat *n* as being equal to 10.

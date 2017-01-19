##Divisors
http://open.kattis.com/problems/divisors  
Solution author: Finn Lidbetter  
Solution date: 17/01/17  

Solution:  
We want to find the prime decomposition of *nCk* where *0<=k<=n<=431*. So to do this, we compute the prime factorisation of each integer between 2 and 431 inclusive. We then store the prime factorisation as an array where the index corresponds to the prime and the entry at that index is the multiplicity of that prime.  
We can then easily find the prime factorisation of *n!* by adding up the multiplicities of each prime in the prime decompositions of each integer between 2 and *n* inclusive.  
The prime factorisation of *nCk=n!/(k!(n-k)!)* can then be obtained by subtracting the multiplicities of each prime in the prime decompositions of each integer between 2 and *k* inclusive and 2 and *(n-k)* inclusive.  
The answer is then obtained by adding 1 to each multiplicity and then multiplying all multiplicities together.

Note that some work can be removed by only adding the multiplicities from *max(k+1,n-k+1)* up to *n* (inclusive) and then only subtracting the multiplicities from 2 up to *min(k,n-k)* (inclusive).

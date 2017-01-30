##Factors
http://open.kattis.com/problems/factors  
Solution author: Finn Lidbetter, Micah Stairs, Liam Keliher, William Fiset  
Solution date: 29/01/17  

Solution:  
Given the value for *n* we want to find the smallest value for *k* such that *f(k)=n*. We can observe that for any *k*, *n* will be the number of prime factors in *k*  (including repeats) and taking the factorial of this value, then dividing it by the factorials of the number of repeats of each prime factor (1 instance = 1 repeat). Note that this means that the numbers in the denominator before taking there factorials must add up to the number in the numerator before taking its factorial.  
We can then establish some upper bounds on the number of terms that appear in the sum and the number that these terms sum to. First, we know that there must be 15 or fewer terms. This is because if there are more than 15 terms, *n* must the the result of multiplying more than 15 distinct primes, but this will be guaranteed to exceed *2<sup>63</sup>*. So there are fewer than 15 terms. Next, we know that they must sum to a values less than or equal to 63. This is because if we are summing to 63 and we want at least 2 distinct primes (else there is only one arrangement), then the smallest corresponding *n* value is *2<sup>62</sup>x3* and this already exceeds *2<sup>63</sup>*.  
Using these 2 constraints we can iterate over all valid partitions of values from 2 to 63 and compute the corresponding *n* and *k* values. These values can be placed in a map such that each possible value can be queried.

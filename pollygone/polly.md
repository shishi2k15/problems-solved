##Polly Gone  
http://open.kattis.com/problems/pollygone  
Solution author: Finn Lidbetter  
Solution date: February 10th, 2017  

Solution:  
This problem reduces to a 0/1 knapsack problem using the fact that the probabilities have at most 4 digits after the decimal point. This means that we can multiply the probabilities by 10,000 and have all the values in the range [0,10000]. The only thing that you have to be careful of is that even though we are only working with values with 4 decimal digits and multiplying them by 10,000 there is still room for precision errors as the binary representation of these values may not be completely precise. As such, it is necessary to round the value after it is multiplied by 10,000. After this it is a straightforward 0/1 knapsack problem.

##Comparing answers
http://open.kattis.com/problems/comparinganswers  
Solution author(s): Finn Lidbetter (coded after researching Freivalds' algorithm), Micah Stairs (identified it as a matrix multiplication problem)  
Solution date: 16/01/17  

Solution:  
Given the starting configuration of roads it can be seen that the matrix containing the number of paths of length 2 between each pair of cities is given by the square of the original matrix.

The problem then reduces to a problem of verifying a a matrix multiplication. This can be done in *O(kn<sup>2</sup>)* by using a probabilistic algorithm known as Freivalds' algorithm as described here: https://en.wikipedia.org/wiki/Freivalds'_algorithm

The basic idea of the algorithm is that for matrices *A,B,C*, if *AB=C* then for any vector *n* with appropriate dimensions, we should have that *ABr=Cr*. So we choose *r* to be a vector of 0's and 1's with entries chosen randomly and check whether *ABr=Cr*. If not then we know that *AB!=C*. In order to have a high probability of having *AB=C* choose another random *r* vector. For each vector *r*, the probability of a false positive is <1/2, so we try with 20 different *r* vectors to give a very low probability of falsely confirming that an incorrect solution.

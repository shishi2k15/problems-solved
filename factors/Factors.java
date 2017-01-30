/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Factors {
  static HashMap<Long,Long> arrangementsToK = new HashMap<Long,Long>();
  // All primes that are needed for this problem
  static long[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61};
  static int[][] factorials = new int[64][18];
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int nPrimes = primes.length;
    int[] primesToIndex = new int[62];
    for (int i=0; i<nPrimes; i++) {
      primesToIndex[(int)primes[i]] = i;
    }

    // Build prime factorisations of each n! up to 63!
    for (int i=2; i<64; i++) {
      for (int j=0; j<nPrimes; j++) {
        factorials[i][j] = factorials[i-1][j];
      }
      int curr = i;
      int divIndex = 0;
      while (curr>1) {
        int div = (int)primes[divIndex];
        while (curr%div==0) {
          curr /= div;
          factorials[i][primesToIndex[div]]++;
        }
        divIndex++;
      }
    }
    
    // Iterate through all partitions of numbers from 2 up 63
    // that are partitioned into 15 or fewer groups
    int[] partition = new int[15];
    for (int i=2; i<=64; i++) {
      nextPartition(partition, 15, 0, i-1, i, 0, 1L);
    }
   
    String line = br.readLine();
    arrangementsToK.put(1L,2L);
    while (line!=null) {
      long n = Long.valueOf(line);
      sb.append(n);
      sb.append(" ");
      if (arrangementsToK.get(n)==null) {
        throw new NullPointerException();
      }
      sb.append(arrangementsToK.get(n));
      sb.append("\n");
      line = br.readLine();
   }
   
    System.out.print(sb);
  }

  static void nextPartition(int[] partition, int maxSummands, int index, int maxVal, int target, int currentSum, long mult) {
    if (index>=maxSummands)
      return;
    
    for (int i=1; i<=maxVal; i++) {
      partition[index] = i;
      
      // Check that the value resulting from multiplying the primes
      // from this partition will be less than 2^63
      long nextMult = mult;
      for (int j=0; j<i; j++) {
        if (nextMult <= Long.MAX_VALUE/primes[index]) {
          nextMult *= primes[index];
        } else {
          return;
        }
      }
      if (currentSum+i>target)
        break;
      if (currentSum+i==target) {
        // Good partition found
        addPartitionToMap(partition, index+1, nextMult);
      } else {
        // Keep looking for partitions
        nextPartition(partition, maxSummands, index+1, i, target, currentSum+i, nextMult);
      }
    }
  }

  // Adds the pairs of numbers to the map according to the partition
  static void addPartitionToMap(int[] partition, int index, long mult) {
    int[] fact = factorialRemainder(partition, index);
    long n = 1;
    for (int i=0; i<fact.length; i++) {
      for (int j=0; j<fact[i]; j++) {
        if (n> Long.MAX_VALUE/primes[i]) {
          return;
        }
        n *= primes[i];
      }
    }
    // Only update map if there is no value yet, or previous value is larger
    if (!arrangementsToK.containsKey(n) || mult<arrangementsToK.get(n)) {
      arrangementsToK.put(n, mult);
    }
  }

  // Compute the prime factorisation of the combinatorial
  // expression defined by the partition array
  static int[] factorialRemainder(int[] partition, int index) {
    int[] result = new int[18]; 
    int sum = 0;
    for (int i=0; i<index; i++) {
      sum += partition[i];
      for (int j=0; j<factorials[0].length; j++) {
        result[j] -= factorials[partition[i]][j];
      }
    }
    for (int j=0; j<factorials[0].length; j++) {
      result[j] += factorials[sum][j];
    }
    return result;
  }
}

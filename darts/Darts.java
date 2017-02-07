/**
 * @author Finn Lidbetter
 * Note that this program generates 2 arrays that give the answers to all
 * possible inputs. A separate program was used to then read the input and
 * print out the appropriate answer.
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Darts {
  static final int MAX_DEPTH = 200;
  static Double[][][][] dp = new Double[502][502][2][MAX_DEPTH];

  // Represents the positions of the number around the dartboard
  static int[] order = {20,1,18,4,13,6,10,15,2,17,3,19,7,16,8,11,14,9,12,5};
  
  public static void main(String[] args) throws IOException {

    // Prints the array of values for the probablity that A wins
    System.out.print("double[] aProb = {");
    for (int i=1; i<=501; i++) {
      System.err.println(i);
      System.out.printf("%.10f,",f(i,i,true,0));
    }
    System.out.printf("0};\n");

    // Prints the array of values for the probablity that B wins
    System.out.print("double[] bProb = {");
    for (int i=1; i<=501; i++) {
      System.err.println(i);
      System.out.printf("%.9f,",f(i,i,false,0));
    }
    System.out.printf("0};\n");

  }

  // Populate DP table based on player A's score and player B's score
  // and whose turn it is. The depth is used to avoid an infinite loop
  // Computing the entire table is too slow to do so online.
  static double f(int aScore, int bScore, boolean aTurn, int depth) {
    int turnIndex = (aTurn) ? 0 : 1;
    if (aScore<0 || bScore<0) {
      // If player A made their score negative then this cannot possibly
      // be a winning move, so make the probability that player B wins, 1.
      // (Or vice versa)
      return 1;
    }
    if (aScore==0 || bScore==0) {
      // Other player reaching 0 first is a guaranteed loss.
      return 0.0;
    } if (depth==MAX_DEPTH) {
      // Probability has become too small to make a difference
      return 0;
    } if (dp[aScore][bScore][turnIndex][depth]!=null) {
      return dp[aScore][bScore][turnIndex][depth];
    }
    if (aTurn) {
      double sum = 0;
      for (int i=0; i<20; i++) {
        if (aScore-(i+1)>=0) {
          sum += 0.05*f(aScore-(i+1), bScore, !aTurn, depth+1);
        } else {
          sum += 0.05*f(aScore, bScore, !aTurn, depth+1);
        }
      }
      if (sum > 1) 
        sum = 1; 
      return dp[aScore][bScore][turnIndex][depth] = 1.0-sum;
    } else {
      double maxProb = 0;
      double third = 1.0/3.0;
      // Try aiming at each position
      for (int i=0; i<20; i++) {
        double sum = 0;
        int v1 = order[(i+19)%20];
        int v2 = order[i];
        int v3 = order[(i+21)%20];
        if (bScore-v1>=0)
          sum += third*f(aScore,bScore-v1, !aTurn, depth+1);
        else
          sum += third*f(aScore,bScore, !aTurn, depth+1);
        if (bScore-v2>=0)
          sum += third*f(aScore,bScore-v2, !aTurn, depth+1);
        else
          sum += third*f(aScore,bScore, !aTurn, depth+1);
        if (bScore-v3>=0)
          sum += third*f(aScore,bScore-v3, !aTurn, depth+1);
        else
          sum += third*f(aScore,bScore, !aTurn, depth+1);
        if (sum > 1) 
          sum = 1;
        double prob = 1.0-sum;
        if (prob>maxProb)
          maxProb = prob;
      }
      return dp[aScore][bScore][turnIndex][depth] = maxProb;
    }
  }
}

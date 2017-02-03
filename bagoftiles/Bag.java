/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Bag {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int g = Integer.parseInt(br.readLine());

    // Create Pascal's triangle for calculating mCn
    long[][] pascal = new long[32][32];
    for (int i=0; i<=31; i++) {
      pascal[i][i] = 1;
    }
    for (int i=1; i<=31; i++) {
      for (int j=1; j<i; j++) {
        pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
      }
    }


    for (int e=0; e<g; e++) {
      int m = Integer.parseInt(br.readLine());
      String[] s = br.readLine().split(" ");
      int[] vals = new int[m];
      int sum = 0;
      for (int i=0; i<m; i++) {
        vals[i] = Integer.parseInt(s[i]);
        sum += vals[i];
      }
      s = br.readLine().split(" ");
      int n = Integer.parseInt(s[0]);
      int t = Integer.parseInt(s[1]);

      // Use DP to count the number of wins
      // DP table stores how many wins there are for
      // a target value, the tiles included, and the
      // number of tiles used.
      int[][][] dp = new int[m+1][n+1][t+1];
      dp[0][0][0] = 1;
      for (int i=0; i<m; i++) {
        int currVal = vals[i];
        for (int j=0; j<=n; j++) {
          for (int k=0; k<=t; k++) {
            dp[i+1][j][k] += dp[i][j][k];
            if (k<=t-currVal && j>0)
              dp[i+1][j][k+currVal] += dp[i][j-1][k];          
          }
        }
      }
      sb.append("Game ");
      sb.append((e+1));
      sb.append(" -- ");
      sb.append(dp[m][n][t]);
      sb.append(" : ");
      // Using the number of wins, we can calculate the
      // number of losses using combinatorics
      sb.append(pascal[m+1][n+1]-dp[m][n][t]);
      sb.append("\n");
      
    }
    System.out.print(sb);
  }
}

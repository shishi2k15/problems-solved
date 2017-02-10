/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Polly {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    // Multiply probability by 10000 so that we are dealing with integers
    int minP = (int)Math.round(10000.0*Double.parseDouble(s[1]));
    Long[][] dp = new Long[n+1][10001];
    dp[0][0] = 0L;
    for (int i=0; i<n; i++) {
      s = br.readLine().split(" ");
      long energy = Long.parseLong(s[0]);
      int p = (int)Math.round(10000.0*Double.parseDouble(s[1]));
      // Try not including
      for (int j=0; j<10001; j++) {
        dp[i+1][j] = dp[i][j];
      }
      for (int j=0; j<10001; j++) {
        // Try including
        if (dp[i][j]!=null && (dp[i+1][j+p]==null || dp[i][j]+energy<dp[i+1][j+p])) {
          dp[i+1][j+p] = dp[i][j] + energy;
        }
      }
    }
    long minEnergy = Long.MAX_VALUE;
    // Iterate over all probabilities that are at least minP
    for (int i=minP; i<10001; i++) {
      if (dp[n][i]!=null && dp[n][i]<minEnergy)
        minEnergy = dp[n][i];
    }
    System.out.println(minEnergy);
  }
}

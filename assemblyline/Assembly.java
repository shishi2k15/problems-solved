/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Assembly {
  
  static int INFINITY = 300_000_000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();


    int k = Integer.parseInt(br.readLine());
    while (k!=0) {
      char[] letters = new char[k];
      String[] s = br.readLine().split(" ");
      HashMap<Character, Integer> charToInt = new HashMap<>();
      for (int i=0; i<k; i++) {
        letters[i] = s[i].charAt(0);
        charToInt.put(letters[i],i);
      }

      int[][] costs = new int[k][k];
      int[][] productIndex = new int[k][k];
      for (int i=0; i<k; i++) {
        s = br.readLine().split(" ");
        for (int j=0; j<k; j++) {
          costs[i][j] = Integer.parseInt(s[j].substring(0,s[j].length()-2));
          productIndex[i][j] = charToInt.get(s[j].charAt(s[j].length()-1));
        }
      }
      int n = Integer.parseInt(br.readLine());
      for (int t=0; t<n; t++) {
        char[] seq = br.readLine().toCharArray();
        int seqLen = seq.length;
        // row = start - inclusive
        // col = end - inclusive
        // depth = letter
        int[][][] dp = new int[seqLen][seqLen][k];
        for (int i=0; i<seqLen; i++) {
          for (int j=0; j<seqLen; j++) {
            Arrays.fill(dp[i][j], -1);
          }
        }

        for (int i=0; i<seqLen; i++) {
          for (int j=0; j<k; j++) {
            if (j==charToInt.get(seq[i]))
              dp[i][i][j] = 0;
            else 
              dp[i][i][j] = INFINITY;
          }
        }
        // For all start and end points, try each split point for each possible letter
        for (int subLen=2; subLen<=seqLen; subLen++) {
          for (int start=0; start+subLen<=seqLen; start++) {
            for (int split=1; split<subLen; split++) { 
              for (int i=0; i<k; i++) {
                for (int j=0; j<k; j++) {
                  if (dp[start][start+split-1][i]==-1 || dp[start+split][start+subLen-1][j]==-1) {
                    continue;
                  }
                      
                  int currentProductIndex = productIndex[i][j];
                  
                  int cost = dp[start][start+split-1][i] + dp[start+split][start+subLen-1][j] + costs[i][j];
                  if (cost<dp[start][start+subLen-1][currentProductIndex] || dp[start][start+subLen-1][currentProductIndex]==-1) {
                    dp[start][start+subLen-1][currentProductIndex] = cost;
                  }
                }
              }
            }
          }
        }
        int min = INFINITY;
        int minIndex = -1;
        for (int i=0; i<k; i++) {
          if (dp[0][seqLen-1][i]!=-1 && dp[0][seqLen-1][i]<min) {
            minIndex = i;
            min = dp[0][seqLen-1][i];
          }
        }
        sb.append(min);
        sb.append("-");
        sb.append(letters[minIndex]);
        sb.append("\n");
      }
      sb.append("\n");

      k = Integer.parseInt(br.readLine());
    }
    sb.delete(sb.length()-1, sb.length());
    System.out.print(sb);
  }
}

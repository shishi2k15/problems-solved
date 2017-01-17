/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Answers {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int n = Integer.parseInt(br.readLine());
    while (n!=0) {
      int[][] mat = new int[n][n];
      for (int i=0; i<n; i++) {
        String[] s= br.readLine().split(" ");
        for (int j=0; j<n; j++) {
          mat[i][j] = Integer.parseInt(s[j]);
        }
      }
      int[][] ans = new int[n][n];
      for (int i=0; i<n; i++) {
        String[] s = br.readLine().split(" ");
        for (int j=0; j<n; j++) {
          ans[i][j] = Integer.parseInt(s[j]);
        }
      }

      boolean allGood = true;
      for (int k=0; k<20; k++) {
        int[] r = new int[n];
        for (int i=0; i<n; i++) {
          r[i] = (Math.random()>0.5) ? 1 : 0;
        }
        int[] res1 = mult(mat, r);
        int[] res2 = mult(mat, res1);
        int[] res3 = mult(ans, r);
        for (int i=0; i<n; i++) {
          if (res2[i]!=res3[i]) {
            allGood = false;
            break;
          }
        }
        if (!allGood) {
          break;
        }
      }

      if (allGood) {
        sb.append("YES\n");
      }else{
        sb.append("NO\n");
      }
      br.readLine();
      n = Integer.parseInt(br.readLine());
    }
    System.out.print(sb);
  }
  static int[] mult(int[][] mat, int[] vec) {
    int n = vec.length;
    int[] res = new int[n];
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        res[i] += mat[i][j]*vec[j];
      }
    }
    return res;
  }
}

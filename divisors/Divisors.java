/**
 * @author Finn Lidbetter
 * 17/01/17
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Divisors {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> primes = new ArrayList<Integer>();

    for (int i=2; i<432; i++) {
      boolean noDivs = true;
      for (int j=2; j<i; j++) {
        if (i%j==0) {
          noDivs = false;
          break;
        }
      }
      if (noDivs) {
        primes.add(i);
      }
    }

    int[] arrPrimes = new int[primes.size()];
    for (int i=0; i<arrPrimes.length; i++) {
      arrPrimes[i] = primes.get(i);
    }

    int nPrimes = arrPrimes.length;
    int[][] primeDecomps = new int[432][nPrimes];
    for (int i=2; i<432; i++) {
      int val = i;
      for (int j=0; j<nPrimes; j++) {
        int prime = arrPrimes[j];
        while (val%prime==0) {
          val/=prime;
          primeDecomps[i][j]++;
        }
        if (val==1) {
          break;
        }
      }
    }

    String line = br.readLine();
    while (line!=null) {
      String[] s = line.split(" ");
      int n = Integer.parseInt(s[0]);
      int k = Integer.parseInt(s[1]);

      int nk = n-k;
      int max = (k>nk) ? k : nk;
      int min = (k>nk) ? nk : k;
      
      int[] nkDecomp = new int[nPrimes];
      for (int i=max+1; i<=n; i++) {
        for (int j=0; j<nPrimes; j++) {
          nkDecomp[j] += primeDecomps[i][j];
        }
      }
      for (int i=2; i<=min; i++) {
        for (int j=0; j<nPrimes; j++) {
          nkDecomp[j] -= primeDecomps[i][j];
        }
      }
      long nDivs = 1;
      for (int i=0; i<nPrimes; i++) {
        nDivs *= (nkDecomp[i]+1);
      }
      sb.append(nDivs);
      sb.append("\n");
      line = br.readLine();
    }
    System.out.print(sb);
  }
}

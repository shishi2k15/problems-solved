/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class RedSocks {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] s = br.readLine().split(" ");
    long p = Long.parseLong(s[0]);
    long q = Long.parseLong(s[1]);
    while (!s[1].equals("0")) {
      boolean found = false;
      long div = gcd(p,q);
      p /= div;
      q /= div;
      if (p==0) {
        // Special case
        found = true;
        sb.append("0 2\n");
      } else {
        for (int i=2; i<=50000; i++) {
          // [r(r-1)]/[t(t-1)] = p/q
          // Find c such that [t(t-1)]=cq
          // Then r(r-1)=cp
          // Much simpler quadratic with smaller discriminant
          long t = i;
          long c = (t*(t-1))/q;
          if (c*q!=t*(t-1)) {
            continue;
          }
          long r = (((long)Math.round(Math.sqrt(1+4*c*p)))+1)/2;
          if (r*(r-1)!=c*p) {
            continue;
          }
          found = true;
          sb.append(r);
          sb.append(" ");
          sb.append(t-r);
          sb.append("\n");
          break;
        }
      }
      if (!found) {
        sb.append("impossible\n");
      }
      s = br.readLine().split(" ");
      p = Long.parseLong(s[0]);
      q = Long.parseLong(s[1]);
      
    }
    System.out.print(sb);
  }
  static long gcd(long a, long b) {
    if(a == 0 || b == 0) return a+b; 
      return gcd(b,a%b);
  } 
}

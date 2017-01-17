import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Hallway {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    String[] halls = new String[11];
    String[] middles = new String[11];
    halls[1] = "L";
    middles[1] = "L";
    for (int i=2; i<11; i++) {
      halls[i] = halls[i-1] + "L" + reverseFlip(halls[i-1]);
      int hallLen = halls[i].length();
      int midPt = (hallLen-1)/2;
      middles[i] = halls[i].substring((int)Math.max(0,midPt-100),midPt) + "L" + halls[i].substring(midPt+1, (int)Math.min(hallLen, midPt+101));
    }
    int t = Integer.parseInt(br.readLine());
    for (int e=0; e<t; e++) {
      String[] s = br.readLine().split(" ");
      int n = Integer.parseInt(s[0]);
      String sub = s[1];
      String subFlip = reverseFlip(sub);
      if (n>10) {
        n = 10;
      }
      int subLen = sub.length();
      boolean good = false;
      for (int j=1; j<n; j++) {

        if (subLen<=middles[j].length()) {
          for (int i=0; i<middles[j].length()-subLen+1; i++) {
            String hallSub = middles[j].substring(i, i+subLen);
            if (hallSub.equals(sub) || hallSub.equals(subFlip)) {
              good = true;
              break;
            }
          }
        }
      }
      if (subLen<=middles[n].length()) {
        for (int i=0; i<middles[n].length()-subLen+1; i++) {
          String hallSub = middles[n].substring(i, i+subLen);
          if (hallSub.equals(sub)) {
            good = true;
            break;
          }
        }
      }
      if (good) {
        sb.append("Case "+(e+1)+": Yes\n");
      } else {
        sb.append("Case "+(e+1)+": No\n");
      }
    }
    System.out.print(sb);
  }
  static String reverseFlip(String s) {
    StringBuilder sb = new StringBuilder(s);
    sb.reverse();
    StringBuilder sb2 = new StringBuilder();
    for (char c:sb.toString().toCharArray()) {
      if (c=='L')
        sb2.append('R');
      else {
        sb2.append('L');
      }
    }
    return sb2.toString();
  }
}

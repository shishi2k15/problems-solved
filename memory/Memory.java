/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Memory {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String s = br.readLine();
    int len = s.length();
    char[] seq = s.toCharArray();
    long last1 = -1;
    long lastMove = 0;
    long lastNotMove = 1;
    long MOD = 1_000_000_009;
    for (int i=0; i<len; i++) {
      if (seq[len-i-1]!='1')
        continue;

      if (last1==-1) {
        lastNotMove = 1;
        lastMove = i;
        last1 = i;
      } else {
        long nextNotMove = (lastMove+lastNotMove)%MOD;
        long nextMove = (((i-last1-1)*lastNotMove)%MOD + ((i-last1)*lastMove)%MOD)%MOD;
        lastMove = nextMove;
        lastNotMove = nextNotMove;
        last1 = i;
      }
    }
    System.out.println((lastMove+lastNotMove)%MOD);
  }
}

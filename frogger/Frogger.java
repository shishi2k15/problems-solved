/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Frogger {
  public static int m;
  public static int n;
  public static long[][] cars;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int nCases = Integer.parseInt(br.readLine());
    for (int e=0; e<nCases; e++) {
      int maxSteps = Integer.parseInt(br.readLine());
      String[] s = br.readLine().split(" ");
      n = Integer.parseInt(s[0]);
      m = Integer.parseInt(s[1]);
      cars = new long[n][m];
      HashSet<State> visited = new HashSet<State>();
      String line = br.readLine();
      int goal = 0;
      for (int i=0; i<m; i++) {
        if (line.charAt(i)=='G')
          goal = i;
      }
      // Store positions of cars in a long
      for (int i=0; i<n; i++) {
        cars[n-i-1][0] = stringToCarRow(br.readLine(), m);
      }
      line = br.readLine();
      int start = 0;
      for (int i=0; i<m; i++) {
        if (line.charAt(i)=='F')
          start = i;
      }
      // Calculate and store positions of cars at each time step
      for (int i=0; i<n; i++) {
        for (int j=1; j<m; j++) {
          if (i%2==0) {
            cars[i][j] = shiftRight(cars[i][j-1], m);
          } else {
            cars[i][j] = shiftLeft(cars[i][j-1], m);
          }
        }
      }
      LinkedList<State> q = new LinkedList<>();
      State startState = new State(start, -1, 0);
      visited.add(startState);
      q.offer(startState);
      int time = 0;
      State curr = startState;
      boolean found = false;
      // Perform Breadth-First-Search
      while (!q.isEmpty()) {
        curr = q.poll();
        int x = curr.x;
        int y = curr.y;
        int t = curr.t;
        if (y==n && x==goal) {
          found  = true;
          break;
        }
        State left = goLeft(curr);
        State right = goRight(curr);
        State up = goUp(curr);
        State down = goDown(curr);
        State stay = stay(curr);
        if (left!=null && !visited.contains(left)) {
          q.offer(left);
          visited.add(left);
        }
        if (right!=null && !visited.contains(right)) {
          q.offer(right);
          visited.add(right);
        }
        if (up!=null && !visited.contains(up)) {
          q.offer(up);
          visited.add(up);
        }
        if (down!=null && !visited.contains(down)) {
          q.offer(down);
          visited.add(down);
        }
        if (stay!=null && !visited.contains(stay)) {
          q.offer(stay);
          visited.add(stay);
        }
      }
      if (curr.t<=maxSteps && found) {
        sb.append("The minimum number of turns is ");
        sb.append(curr.t);
        sb.append(".\n");
      } else {
        sb.append("The problem has no solution.\n");
      }
    }
    System.out.print(sb);
  }

  static long stringToCarRow(String s, int m) {
    long state = 0;
    for (int i=0; i<m; i++) {
      if (s.charAt(m-i-1)=='X') {
        state |= 1L << (long)i;
      }
    }
    return state;
  }
  static long shiftLeft(long val, int m) {
    long state = val;
    val = val << 1L;
    if ((val & 1L<<(long)m)!=0) {
      val ^= 1L<<(long)m;
      val |= 1L;
    }
    return val;
  }
  static long shiftRight(long val, int m) {
    long state = val;
    if ((val&1L)!=0) {
      val |= 1L<<(long)m;
    }
    val = val >> 1L;
    return val;
  }
  static State goLeft(State curr) {
    if (curr.x==0)
      return null;
    if (curr.y==-1 || curr.y==n) 
      return new State(curr.x-1, curr.y, curr.t+1);
    else if ((cars[curr.y][(curr.t+1)%m] & 1L<<(m-(curr.x-1)-1))!=0)
      return null;
    return new State(curr.x-1, curr.y, curr.t+1);
  }
  static State goRight(State curr) {
    if (curr.x==m-1)
      return null;
    if (curr.y==-1 || curr.y==n)
      return new State(curr.x+1, curr.y, curr.t+1);
    else if ((cars[curr.y][(curr.t+1)%m] & 1L<<(m-(curr.x+1)-1))!=0)
      return null;
    return new State(curr.x+1, curr.y, curr.t+1);
  }
  static State goUp(State curr) {
    if (curr.y==n)
      return null;
    if (curr.y==n-1)
      return new State(curr.x, curr.y+1, curr.t+1);
    else if ((cars[curr.y+1][(curr.t+1)%m] & 1L<<(m-curr.x-1)) !=0)
      return null;
    return new State(curr.x, curr.y+1, curr.t+1);
  }
  static State goDown(State curr) {
    if (curr.y==-1)
      return null;
    if (curr.y==0)
      return new State(curr.x, curr.y-1, curr.t+1);
    else if ((cars[curr.y-1][(curr.t+1)%m] & 1L<<(m-curr.x-1)) !=0)
      return null;
    return new State(curr.x, curr.y-1, curr.t+1);
  }
  static State stay(State curr) {
    if (curr.y==-1 || curr.y==n)
      return new State(curr.x, curr.y, curr.t+1);
    if ((cars[curr.y][(curr.t+1)%m] & 1L<<(m-curr.x-1))!=0) 
      return null;
    return new State(curr.x, curr.y, curr.t+1);
  }

}
class State {
  int x;
  int y;
  int t; 
  public State(int xx, int yy, int tt) {
    x = xx;
    y = yy;
    t = tt;
  }
  
  @Override
  public boolean equals(Object o) {
    State s = (State)o;
    return x==s.x && y==s.y && (t%Frogger.m)==(s.t%Frogger.m);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x,y, t%Frogger.m);
  }
}

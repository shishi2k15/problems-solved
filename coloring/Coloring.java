/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Coloring {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    boolean[][] adj = new boolean[n][n];
    for (int i=0; i<n; i++) {
      String[] s = br.readLine().split(" ");
      for (int j=0; j<s.length; j++) {
        int k = Integer.parseInt(s[j]);
        adj[i][k] = adj[k][i] = true;
      }
    }
    int[] colours = new int[n];
    // Try each possible number of colours until solution is found
    for (int i=1; i<=11; i++) {
      if (colourGraph(adj, colours, 0, i)) {
        System.out.println(i);
        break;
      }
    }
  }
  
  static boolean colourGraph(boolean[][] adj, int[] colours, int currentV, int maxColour) {
    if (currentV==colours.length)
      return true;

    for (int i=1; i<=maxColour; i++) {
      // Try each colour
      if (canColour(adj, colours, currentV, i)) {
        colours[currentV] = i;       
        
        // Recurse
        if (colourGraph(adj, colours, currentV+1, maxColour)) 
          return true;

        // Backtrack
        colours[currentV] = 0;
      }
    }
    return false;
  }

  // Checks if it is possible to use this colour
  static boolean canColour(boolean[][] adj, int[] colours, int currentV, int colour) {
    for (int i=0; i<adj.length; i++) {
      if (adj[currentV][i] && colours[i]==colour)
        return false;
    }
    return true;
  }
}

/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Cheetahs {
  static int[] startTime;
  static int[] speed;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    startTime = new int[n];
    speed = new int[n];
    int minTime = 0;
    for (int i=0; i<n; i++) {
      String[] s = br.readLine().split(" ");
      startTime[i] = Integer.parseInt(s[0]);
      speed[i] = Integer.parseInt(s[1]);
      // minTime is the time when the last cheetah starts
      if (startTime[i]>minTime)
        minTime = startTime[i];
    }
    // Set maxTime to some large value beyond the largest max time.
    int maxTime = 500000000;
    
    double ans = ternarySearch(minTime, maxTime);
    System.out.println(ans);
  }

  // Returns the distance betweent the first cheetah and the last cheetah
  static double function(double time) {
    double minPos = Double.POSITIVE_INFINITY;
    double maxPos = Double.NEGATIVE_INFINITY;
    for (int i=0; i<startTime.length; i++) {
      double runningTime = time-startTime[i];
      double pos = runningTime*speed[i];

      if (pos<minPos)
        minPos = pos;
      if (pos>maxPos)
        maxPos = pos;
    }
    return maxPos - minPos;
  }

  static double ternarySearch(double low, double high) {
    Double best = null;
    while (true) {
      double mid1 = (2*low + high)/3;
      double mid2 = (low + 2*high)/3;
      double res1 = function(mid1);
      double res2 = function(mid2);
      if (res1 > res2) low = mid1;
      else high = mid2;
      if (best != null && Math.abs(best - res1) < 0.000000001) break;
      best = res1;
    }
    return best;
  }
}

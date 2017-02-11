/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Citadel {
  static Point2D p1;
  static Point2D p2;
  static Point2D[] hull;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int t = Integer.parseInt(br.readLine());
    for (int e=0; e<t; e++) {
      int n = Integer.parseInt(br.readLine());
      Point2D[] pts = new Point2D.Double[n];
      for (int i=0; i<n; i++) {
        String[] s = br.readLine().split(" ");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);
        pts[i] = new Point2D.Double(x,y);
      }
      // Compute the convex hull of the points
      hull = convexHull(pts);
      
      n = hull.length;

      // If hull only has 3 points, output area of triangle
      if (n==3) {
        sb.append(formatAns(area(hull[0],hull[1], hull[2])));
        sb.append("\n");
        continue;
      }
      double maxArea = 0;
      // Try all pairs of non-adjacent points on convex hull
      for (int i=0; i<n; i++) {
        for (int j=0; j<i-1; j++) {
          if (i==n-1 && j==0)
            continue;
          p1 = hull[i];
          p2 = hull[j];
          // Find the points in the hull that maximise the area of the triangles
          // formed by p1, p2, and the new point
          double area1 = ternarySearch(j+1, i-1);
          double area2 = ternarySearch(i+1, j+n-1);
          // Keep track of the best area overall
          if ((area1+area2)*-1 > maxArea)
            maxArea = -1*(area1+area2);
        }
      }
      sb.append(formatAns(maxArea));
      sb.append("\n");
    }
    System.out.print(sb);
  }

  static String formatAns(double ans) {
    if (Math.abs(ans-Math.round(ans))<0.25)
      return ""+(int)Math.round(ans);
    return String.format("%.1f",ans);
  }

  // Function for the ternary search
  static double function(int val) {
    val %= hull.length;
    Point2D p3 = hull[val];
    return -1.0*area(p1, p2, p3);
  }

  // Discrete version of ternary search
  static double ternarySearch(int lo, int hi) {
    double EPS = 0.000000001;
    while (lo!=hi) {
      if (hi-lo==1)
        return Math.min(function(lo),function(hi));
      if (hi-lo==2) 
        return Math.min(function(lo), Math.min(function(lo+1), function(hi)));
      int mid1 = (2*lo + hi)/3;
      int mid2 = (lo + 2*hi)/3;
      double res1 = function(mid1);
      double res2 = function(mid2);
      if (Math.abs(res1-res2)<EPS) {
        lo = mid1;
        hi = mid2;
      } else if (res1>res2) lo = mid1;
      else hi = mid2;
    }
    return function(lo);
  }
  
  // Area of a triangle from 3 points
  static double area(Point2D a, Point2D b, Point2D c) {
    return Math.abs(a.getX()*(b.getY()-c.getY())-a.getY()*(b.getX()-c.getX())+(b.getX()*c.getY()-b.getY()*c.getX()))/2.0;
  }

  static Point2D[] convexHull(Point2D[] pts) {
    int n = pts.length, k = 0, count = 0;
    Point2D[] arr = new Point2D[2 * n];
    Arrays.sort(pts, new PointComparator());
    for (int i = 0; i < n; ++i) {
      while (k >= 2 && cross(arr[k-2], arr[k-1], pts[i]) <= 0) k--;
      arr[k++] = pts[i]; }
    for (int i = n - 2, t = k + 1; i >= 0; i--) {
      while (k >= t && cross(arr[k-2], arr[k-1], pts[i]) <= 0) k--;
      arr[k++] = pts[i]; }
    HashSet<Point2D> pt_set = new HashSet<>();
    Point2D[] hull = new Point2D[n];
    for (int i = 0; i < k; i++)
      if (!pt_set.contains(arr[i])) {
        hull[count++] = arr[i];
        pt_set.add(arr[i]); }
    return Arrays.copyOfRange(hull, 0, count); }
  static class PointComparator implements Comparator<Point2D> {
    static final double EPS = 0.000001;
    public int compare(Point2D p1, Point2D p2) {
      if (Math.abs(p1.getX() - p2.getX()) < EPS) {
        if (Math.abs(p1.getY() - p2.getY()) < EPS) return 0;
        else if (p1.getY() > p2.getY()) return 1;
      } else if (p1.getX() > p2.getX()) return 1;
      return -1; } }
  static double cross(Point2D a, Point2D b, Point2D c) { // potential overflow
    return ((b.getX() - a.getX()) * (c.getY() - a.getY()) -
            (b.getY() - a.getY()) * (c.getX() - a.getX())); }
}

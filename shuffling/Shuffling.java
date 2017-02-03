/**
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Shuffling {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    boolean out = s[1].equals("out");
    UnionFind uf = new UnionFind(n);
    
    // Determine which elements cycle by
    // putting 2 elements together in the Union
    // Find data structure if one element moves
    // to the other's position after one shuffle
    if (out) { // Out shuffle
      if ((n&1)==1) { // Odd case
        for (int i=0; i<=n/2; i++) {
          uf.union(i, 2*i);
        }
        for (int j=0; j<n/2; j++) {
          uf.union(j+n/2 + 1, 2*j + 1);
        }
      } else { // Even case
        for (int i=0; i<n/2; i++) {
          uf.union(i,2*i);
        }
        for (int j=0; j<n/2; j++) {
          uf.union(j+n/2, 2*j + 1);
        }
      }
    } else { // In shuffle
      if ((n&1)==1) { // Odd case
        for (int i=0; i<n/2; i++) {
          uf.union(i, 2*i + 1);
        }
        for (int j=0; j<=n/2; j++) {
          uf.union(n/2+j, 2*j);
        }
      } else {
        for (int i=0; i<n/2; i++) {
          uf.union(i, 2*i + 1);
        }
        for (int j=0; j<n/2; j++) {
          uf.union(n/2 + j, 2*j);
        }
      }
    }
    int lcmVal = 1;
    // Get the least common multiple of all cycle lengths
    for (int i=0; i<n; i++) {
      lcmVal = lcm(lcmVal, uf.getSize(i));
    }
    System.out.println(lcmVal);
  }

  static int gcd(int a, int b) {
     if (b==0) return a;
     return gcd(b,a%b);
  }
  static int lcm(int a, int b) {
    int gcf = gcd(a,b);
    return (a/gcf)*b;
  }
}
class UnionFind {
  int[] id, sz;
  public UnionFind(int n) {
    id = new int[n]; sz = new int[n];
    for (int i = 0; i < n; i++) { id[i] = i; sz[i] = 1; } }
  int find(int p) {
    int rt = p;
    while (rt != id[rt]) rt = id[rt];
    while (p != rt) { int next = id[p]; id[p] = rt; p = next; }
    return rt; }
  boolean connected(int p, int q) { return find(p) == find(q); }
  int getSize(int p) { return sz[find(p)]; }
  int countSets() {
    int nSets = 0;
    for (int i = 0; i < id.length; i++) if (id[i] == i) nSets++;
    return nSets; }
  void union(int p, int q) {
    int r1 = find(p), r2 = find(q);
    if (r1 == r2) return;
    if (sz[r1] < sz[r2]) { sz[r2] += sz[r1]; id[r1] = r2; }
    else { sz[r1] += sz[r2]; id[r2] = r1; } } }

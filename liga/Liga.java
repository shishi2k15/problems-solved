/*
 * @author Finn Lidbetter
 */
import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class Liga {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    // Wdl: Wrapper class storing wins, draws, and losses
    // rows: games played
    // cols: points
    Wdl[][] memoBoth = new Wdl[101][301];
    Wdl[] memoGames = new Wdl[101];
    Wdl[] memoPoints = new Wdl[301];
    // Establish mapping from number of points and number of games to W,D,L stats
    for (int i=0; i<=100; i++) {
      for (int j=0; j<=100; j++) {
        for (int k=0; k<=100; k++) {
          int games = i+j+k;
          if (games>100) {
            continue;
          }
          int points = 3*i + j;
          Wdl curr = new Wdl(i,j,k);
          memoBoth[games][points] = curr;
          memoGames[games] = curr;
          memoPoints[points] = curr;
        }
      }
    }
    
    int n = Integer.parseInt(br.readLine());
    for (int t=0; t<n; t++) {
      String[] stats = br.readLine().split(" ");
      boolean winsGiven = !stats[1].equals("?");
      int wins = (winsGiven) ? Integer.parseInt(stats[1]) : -1;
      boolean drawsGiven = !stats[2].equals("?");
      int draws = (drawsGiven) ? Integer.parseInt(stats[2]) : -1;
      boolean lossesGiven = !stats[3].equals("?");
      int losses = (lossesGiven) ? Integer.parseInt(stats[3]) : -1;
      boolean gamesGiven = !stats[0].equals("?");
      int games = (gamesGiven) ? Integer.parseInt(stats[0]) : -1;
      boolean pointsGiven = !stats[4].equals("?");
      int points = (pointsGiven) ? Integer.parseInt(stats[4]) : -1;
      
      // If no w,d,l info is given, look up answer in table
      if (!winsGiven && !drawsGiven && !lossesGiven) {
        if (gamesGiven && pointsGiven) {
          Wdl curr = memoBoth[games][points];
          sb.append(getStatString(curr.w,curr.d,curr.l));
        } else if (pointsGiven) {
          Wdl curr = memoPoints[points];
          sb.append(getStatString(curr.w,curr.d,curr.l));
        } else {
          Wdl curr = memoGames[games];
          sb.append(getStatString(curr.w,curr.d,curr.l));
        }
      } else {
        boolean found = false;
        // If one of W,D,L is given, look for the first answer that is consistent.
        // Loop through the possibilities for the missing W,D,L values and stop
        // when values consistent with the given values is found
        if (winsGiven) { //
          for (int j=0; j<=100; j++) {
            if (!drawsGiven) {
              draws = j;
            }
            for (int k=0; k<=100; k++) {
              if (!lossesGiven) {
                losses = k;
              }
              if (isValid(games, wins, draws, losses, points)) {
                sb.append(getStatString(wins,draws,losses));
                found = true;
                break;
              }
            }
            if (found) {
              break;
            }
          }
        }
        else if (drawsGiven) {
          for (int i=0; i<=100; i++) {
            if (!winsGiven) {
              wins = i;
            }
            for (int k=0; k<=100; k++) {
              if (!lossesGiven) {
                losses = k;
              }
              if (isValid(games, wins, draws, losses, points)) {
                sb.append(getStatString(wins,draws,losses));
                found = true;
                break;
              }
            }
            if (found) {
              break;
            }
          }
        }
        else if (lossesGiven) {
          for (int i=0; i<=100; i++) {
            if (!winsGiven) {
              wins = i;
            }
            for (int j=0; j<=100; j++) {
              if (!drawsGiven) {
                draws = j;
              }
              if (isValid(games, wins, draws, losses, points)) {
                sb.append(getStatString(wins,draws,losses));
                found = true;
                break;
              }
            }
            if (found) {
              break;
            }
          }
        }
      }
      sb.append("\n");
    }
    System.out.print(sb);
  }
  static boolean isValid(int g, int w, int d, int l, int p) {
    if (w+d+l>100)
      return false;
    if (g!=-1) {
      if (w+d+l!=g)
        return false;
    }
    if (p!=-1) {
      if (3*w + d != p) {
        return false;
      }
    }
    return true;
  }

  static String getStatString(int w, int d, int l) {
    return ""+(w+d+l)+" "+w+" "+d+" "+l+" "+(3*w + d);
  }
}
class Wdl {
  int w;
  int d;
  int l;
  public Wdl(int ww, int dd, int ll) {
    w = ww;
    d = dd;
    l = ll;
  }
}

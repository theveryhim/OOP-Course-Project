import java.util.*;
public class Maze {
    static Scanner sc = new Scanner(System.in);
    static int n = sc.nextInt();
    static int m = sc.nextInt();
    static int l = Integer.MAX_VALUE;
    static int [][] a = new int[n][m];
    public static void main(String[] args) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        int [][] b = new int[n][m];
        for (int i=0;i<n;i++)
            b[i] = a[i].clone();
        GPS(b, 0, 0, 0);
        if(l==Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(l);
    }
    static void GPS(int [][]b, int steps, int i, int j) {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        if (b[i][j] == 9) {
            if(steps<l) l=steps;
            return;
        }
        b[i][j]=1;
        for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && b[newX][newY] != 1) {
                GPS(b,steps+1,newX,newY);
            }
        }
    }
 }
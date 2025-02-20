import java.util.*;
public class Sudoko {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = new String[6];
        String c = "";
        String t = "";
        for (int i = 0; i < 6; i++)
            a[i] = sc.nextLine().replaceAll(" ", "");
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                String b = "";
                for (int k = 0; k < 6; k++)
                    b = b + a[k].charAt(i);
                c = a[(j/2)* 2].substring((i/3)*3, (i/3)*3 + 3) +
                        a[(j/2)*2 + 1].substring((i/3)*3, (i/3)*3 + 3);
                String lr, lc, lb;
                lr = "";
                lb = "";
                lc = "";
                if (a[j].charAt(i) == 'x') {
                    t = "123456";
                    lr = row_check(t, a[j], i);
                    t = "123456";
                    lc = column_check(t, b, j);
                    t = "123456";
                    lb = box_check(t, c, (j % 2) + (i % 3));
                    if (lr.length() == 1) a[j] = a[j].substring(0, i) + lr + a[j].substring(i + 1, 6);
                    else if (lc.length() == 1) a[j] = a[j].substring(0, i) + lc + a[j].substring(i + 1, 6);
                    else if (lb.length() == 1) a[j] = a[j].substring(0, i) + lb + a[j].substring(i + 1, 6);
                    else if (lr.length() == 2 && lc.length() == 2 && lb.length() == 2) {
                        String lx = "";
                        if (lr.charAt(0) == lc.charAt(0)) lx = lr.substring(0, 1);
                        else if (lr.charAt(1) == lc.charAt(0)) lx = lr.substring(1, 2);
                        else if (lr.charAt(0) == lc.charAt(1)) lx = lr.substring(0, 1);
                        else if (lr.charAt(1) == lc.charAt(1)) lx = lr.substring(1, 2);
                        a[j] = a[j].substring(0, i) + lx + a[j].substring(i + 1, 6);
                    }
                }
            }
        }
        for(int q = 0;q<6;q++){
        for (int z = 0; z < 6; z++) {
            System.out.print(a[q].charAt(z));
            System.out.print(" ");
        }
        System.out.print("\n");
    }
}
    static String row_check(String x,String p,int k){
       for (int i=0;i<6;i++)
               for (int j=0;j<x.length();j++)
                   if(p.charAt(i)==x.charAt(j))
                       x = x.substring(0,j)+x.substring(j+1,x.length());
       return x;
    }
    static String column_check(String x,String p,int k){
          for(int i=0;i<6;i++)
                  for (int j=0;j<x.length();j++)
                      if(p.charAt(i)==x.charAt(j))
                          x = x.substring(0,j)+x.substring(j+1,x.length());
          return x;
    }
    static String box_check(String x,String p,int k){
        for(int i=0;i<6;i++)
                for (int j=0;j<x.length();j++)
                    if(p.charAt(i)==x.charAt(j))
                        x = x.substring(0,j)+x.substring(j+1,x.length());
        return x;
    }
}


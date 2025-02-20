import java.util.*;
public class scape_to_brujen {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,t,b,c,s=0;
        double r=0,l=0;
        String PS0,PS1,f="";
        n = sc.nextInt();
        PS0 = sc.next();
        b = sc.nextInt();
        t = sc.nextInt();
        if (b>t){
            b = b + t;
            t = b - t;
            b = b - t;
        }
        PS1 = PS0.substring(b,t - 1);
        for (int i = 0; i <=t - b - 2;i++) {
            if(PS1.charAt(0)==83){
                if (i == t - b - 2){
                    s = s + 1;
                    f = Integer.toString(s,2);
                    c = 0;
                    s = 0;
                    for (int j = 0; j < f.length(); j++) {
                        if (f.charAt(j) == 49) {
                            c++;
                        }
                    }
                    l =l + c;
                }
                else {
                    s = s + 1;
                }
            }
            else if(s!=0){
                f = Integer.toString(s,2);
                c = 0;
                s = 0;
                    for (int j = 0; j < f.length(); j++) {
                        if (f.charAt(j) == 49) {
                            c++;
                        }
                    }
                l =l + c;
            }
            PS1 = PS1.substring(1,PS1.length());
        }
        System.out.println((int)l);
    }
}

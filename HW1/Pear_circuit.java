import java.text.DecimalFormat;
import java.util.*;
public class Pear_circuit {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int Num,type,Sc=0;
        double r,R=0,v,P=0;
        v = sc.nextDouble();
        type = sc.nextInt();
        if(type<=2&&type>=1) {
            Num = sc.nextInt();
            if (type == 1) {
                for (int i = 0; i < Num; i++) {
                    r = sc.nextDouble();
                    R = R + r;
                }
                if (R == 0) Sc = 1;
            }
            else {
                for (int i = 0; i < Num; i++) {
                    r = sc.nextDouble();
                    if (r == 0) Sc = 1;
                    if (i == 0) R = r;
                    else R = R * r / (r + R);
                }
            }
           if (Sc == 1){
               System.out.println("Total Res: 0 Kohm");
               System.out.println("Short Circuit");
           }
           else {
               P = v*v/R;
               DecimalFormat df = new DecimalFormat("#.##");
               System.out.printf("Total Res: %s Kohm\n",df.format(R/1000));
               System.out.printf("Total Pow: %s mW\n",df.format(P*1000));
           }
        }
        else System.out.println("Invalid Input");
    }
}

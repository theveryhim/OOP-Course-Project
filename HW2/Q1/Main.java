
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Bike> Bikes = new ArrayList<>();
        List<Car> Cars = new ArrayList<>();
        List<Motorcycle> Motorcycles = new ArrayList<>();
        String input = "";
        while (!input.equals("end")){
            String [] a = new String[5];
            String output = "";
            input = sc.next();
            switch (input) {
                case "addCar":
                case "addMotorcycle":
                case "addBike" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    a[2] = sc.next();
                    if (input.equals("addBike"))
                        a[3] = sc.next();
                    output = Add(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarColor":
                case "SetBikeColor":
                case "SetMotorcycleColor" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Color(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarOrigin":
                case "SetMotorcycleOrigin":
                case "SetBikeOrigin" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Origin(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarStrength":
                case "SetMotorcycleStrength" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Strength(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarSpeed":
                case "SetMotorcycleSpeed" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Speed(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetBikeWeight" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Weight(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetWheelSize" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = WheelSize(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarPrice":
                case "SetMotorcyclePrice":
                case "SetBikePrice" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Price(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarState":
                case "SetMotorcycleState":
                case "SetBikeState" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = State(a, input, Cars, Bikes, Motorcycles);}
                break;
                case "CompareCars":
                case "CompareMotorcycles":
                case "CompareBikes" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Compare(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SetCarDiscount":
                case "SetMotorcycleDiscount":
                case "SetBikeDiscount" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Discount(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "SearchCars":
                case "SearchMotorcycles":
                case "SearchBikes" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output =  Search(a, input, Cars, Bikes, Motorcycles);
                }
                break;
                case "LeastCarPrice":
                case "LeastMotorcyclePrice":
                case "LeastBikePrice" : {
                    output = Least(input, Cars, Bikes, Motorcycles);
                }
                break;
                case "Show" : {
                    a[0] = sc.next();
                    a[1] = sc.next();
                    output = Show(a, input, Cars, Bikes, Motorcycles);
                }
            }
            if (!input.equals("end"))
                System.out.println(output);
        }
    }
    public static String Add(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "addBike":{
                for (Bike i:Bikes){
                    if (i.Code.equals(a[2])) {
                        s = "Bike with this code already exists!";
                        break;
                    }
                }
                if (s.equals("")) {
                    Bikes.add(new Bike(a[0], a[1], a[2], a[3]));
                    s = "Bike was added successfully!";
                }
            }
            break;
            case "addCar":{
                for (Car i:Cars){
                    if (i.Code.equals(a[2])) {
                        s = "Car with this code already exists!";
                        break;
                    }
                }
                if (s.equals("")) {
                    Cars.add(new Car(a[0], a[1], a[2]));
                    s = "Car was added successfully!";
                }
            }
            break;
            case "addMotorcycle":{
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[2])) {
                        s = "Motorcycle with this code already exists!";
                        break;
                    }
                }
                if (s.equals("")) {
                    Motorcycles.add(new Motorcycle(a[0], a[1], a[2]));
                    s = "Motorcycle was added successfully!";
                }
            }
        }
        return s;
    }
    public static String Color(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "SetCarColor" : {
                for (Car i:Cars){
                    if (i.Code.equals(a[0])) {
                        s = "CarColor is " + a[1] + "!";
                        i.Color = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcycleColor" : {
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[0])) {
                        s = "MotorcycleColor is " + a[1] + "!";
                        i.Color = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
            break;
            case "SetBikeColor" : {
                for (Bike i:Bikes){
                    if (i.Code.equals(a[0])) {
                        s = "BikeColor is " + a[1] + "!";
                        i.Color = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
            }
        }
        return s;
    }
    public static String Origin(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "SetCarOrigin" : {
                for (Car i:Cars){
                    if (i.Code.equals(a[0])) {
                        s = "CarOrigin is " + a[1] + "!";
                        i.Origin = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcycleOrigin" : {
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[0])) {
                        s = "MotorcycleOrigin is " + a[1] + "!";
                        i.Origin = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
            break;
            case "SetBikeOrigin" : {
                for (Bike i:Bikes){
                    if (i.Code.equals(a[0])) {
                        s = "BikeOrigin is " + a[1] + "!";
                        i.Origin = a[1];
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
            }
        }
        return s;
    }
    public static String Strength(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input) {
            case "SetCarStrength": {
                for (Car i : Cars) {
                    if (i.Code.equals(a[0])) {
                        double Strength = Double.parseDouble(a[1]);
                        if (500 >= Strength & Strength >= 100) {
                            i.Strength = Double.parseDouble(a[1]);
                            s = "CarStrength is " + i.Strength +  " hp!";
                            break;
                        }
                        else {
                            s = "CarStrength is invalid!";
                            break;
                        }
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcycleStrength": {
                for (Motorcycle i : Motorcycles) {
                    if (i.Code.equals(a[0])) {
                        double Strength = Double.parseDouble(a[1]);
                        if (100 >= Strength & Strength >= 50) {
                            i.Strength = Double.parseDouble(a[1]);
                            s = "MotorcycleStrength is " + i.Strength + " hp!";
                            break;
                        }
                        else {
                            s = "MotorcycleStrength is invalid!";
                            break;
                        }
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
        }
        return s;
    }
    public static String Speed(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input) {
            case "SetCarSpeed": {
                for (Car i : Cars) {
                    if (i.Code.equals(a[0])) {
                        i.Speed = Double.parseDouble(a[1]);
                        s = "CarSpeed is " + i.Speed + "!";
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcycleSpeed": {
                for (Motorcycle i : Motorcycles) {
                    if (i.Code.equals(a[0])) {
                        s = "MotorcycleSpeed is " + a[1] + "!";
                        i.Speed = Double.parseDouble(a[1]);
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
        }
        return s;
    }
    public static String Weight(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        for (Bike i : Bikes) {
            if (i.Code.equals(a[0])) {
                i.Weight = Double.parseDouble(a[1]);
                s = "BikeWeight is " + i.Weight + "!";
                break;
            }
        }
        if (s.equals(""))
            s = "No bikes exist with the given code!";
        return s;
    }
    public static String WheelSize(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        for (Bike i : Bikes) {
            if (i.Code.equals(a[0])) {
                double WheelSize = Double.parseDouble(a[1]);
                if (29>=WheelSize&WheelSize>=12) {
                    i.WheelSize = Double.parseDouble(a[1]);
                    s = "WheelSize is " + i.WheelSize + " inches!";
                    break;
                }
                else
                    s = "WheelSize is invalid!";
            }
        }
        if (s.equals(""))
            s = "No bikes exist with the given code!";
        return s;
    }
    public static String Price(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input) {
            case "SetCarPrice": {
                for (Car i : Cars) {
                    if (i.Code.equals(a[0])) {
                        i.Price = Double.parseDouble(a[1]);
                        s = "CarPrice is " + (int)i.Price + "$!";
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcyclePrice": {
                for (Motorcycle i : Motorcycles) {
                    if (i.Code.equals(a[0])) {
                        i.Price = Double.parseDouble(a[1]);
                        s = "MotorcyclePrice is " + (int)i.Price + "$!";
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
            break;
            case "SetBikePrice": {
                for (Bike i : Bikes) {
                    if (i.Code.equals(a[0])) {
                        i.Price = Double.parseDouble(a[1]);
                        s = "BikePrice is " + (int)i.Price + "$!";
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
            }
        }
        return s;
    }
    public static String State(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "SetCarState" : {
                for (Car i:Cars){
                    if (i.Code.equals(a[0])) {
                        if (a[1].equals("unsold")){
                            s = "CarState is unsold!";
                        }
                        else {
                            s = "CarState is sold!";
                            Cars.remove(i);
                        }
                        break;
                    }
                    if (s.equals(""))
                        s = "No cars exist with the given code!";
                }
            }
            break;
            case "SetMotorcycleState" : {
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[0])) {
                        if (a[1].equals("unsold")){
                            s = "MotorcycleState is unsold!";
                        }
                        else {
                            s = "MotorcycleState is sold!";
                            Motorcycles.remove(i);
                        }
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
            break;
            case "SetBikeState" : {
                for (Bike i:Bikes){
                    if (i.Code.equals(a[0])) {
                        if (a[1].equals("unsold")){
                            s = "BikeState is unsold!";
                        }
                        else {
                            s = "BikeState is sold!";
                            Bikes.remove(i);
                        }
                        break;
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
            }
        }
        return s;
    }
    public static String Compare(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "CompareCars" : {
                double [] Price = {-1,-1};
                double [] Strength = {-1,-1};
                double [] Speed = {-1,-1};
                String [] Code = {"",""};
                for (Car i:Cars){
                    if (i.Code.equals(a[0])) {
                        Price[0] = i.Price;
                        Strength[0] = i.Strength;
                        Speed[0] = i.Speed;
                        Code [0] = a[0];
                    }
                    else if (i.Code.equals(a[1])) {
                        Price[1] = i.Price;
                        Strength[1] = i.Strength;
                        Speed[1] = i.Speed;
                        Code [1] = a[1];
                    }
                }
                if (Price[0]==-1|Price[1]==-1)
                    s = "Input is invalid!";
                else{
                    if(Speed[0]>Speed[1])
                        s = s + "CarSpeed: " + Code[0] + "\n";
                    else if(Speed[0]<=Speed[1])
                        s = s + "CarSpeed: " + Code[1] + "\n";
                    if(Strength[0]>Strength[1])
                        s = s + "CarStrength: " + Code[0] + "\n";
                    else if(Strength[0]<=Strength[1])
                        s = s + "CarStrength: " + Code[1] + "\n";
                    if(Price[0]>Price[1])
                        s = s + "CarPrice: " + Code[1] + "\n";
                    else if(Price[0]<=Price[1])
                        s = s + "CarPrice: " + Code[0] + "\n";
                    s = s.substring(0,s.length()-1);
                }
            }
            break;
            case "CompareMotorcycles" : {
                double [] Price = {-1,-1};
                double [] Strength = {-1,-1};
                double [] Speed = {-1,-1};
                String [] Code = {"",""};
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[0])) {
                        Price[0] = i.Price;
                        Strength[0] = i.Strength;
                        Speed[0] = i.Speed;
                        Code [0] = a[0];
                    }
                    else if (i.Code.equals(a[1])) {
                        Price[1] = i.Price;
                        Strength[1] = i.Strength;
                        Speed[1] = i.Speed;
                        Code [1] = a[1];
                    }
                }
                if (Price[0]==-1|Price[1]==-1)
                    s = "Input is invalid!";
                else{
                    if(Speed[0]>Speed[1])
                        s = s + "MotorcycleSpeed: " + Code[0] + "\n";
                    else if(Speed[0]<=Speed[1])
                        s = s + "MotorcycleSpeed: " + Code[1] + "\n";
                    if(Strength[0]>Strength[1])
                        s = s + "MotorcycleStrength: " + Code[0] + "\n";
                    else if(Strength[0]<=Strength[1])
                        s = s + "MotorcycleStrength: " + Code[1] + "\n";
                    if(Price[0]>Price[1])
                        s = s + "MotorcyclePrice: " + Code[1] + "\n";
                    else if(Price[0]<=Price[1])
                        s = s + "MotorcyclePrice: " + Code[0] + "\n";
                    s = s.substring(0,s.length()-1);
                }
            }
            break;
            case "CompareBikes" : {
                double [] Price = {-1,-1};
                double [] Weight = {-1,-1};
                String [] Code = {"",""};
                String [] Type = {"",""};
                for (Bike i:Bikes){
                    if (i.Code.equals(a[0])) {
                        Price[0] = i.Price;
                        Weight[0] = i.Weight;
                        Code [0] = a[0];
                        Type [0] = i.Type;
                    }
                    else if (i.Code.equals(a[1])) {
                        Price[1] = i.Price;
                        Weight[1] = i.Weight;
                        Code [1] = a[1];
                        Type [1] = i.Type;
                    }
                }
                if (Price[0]==-1|Price[1]==-1)
                    s = "Input is invalid!";
                else if(!Type[1].equals(Type[0]))
                    s = "Types should be the same!";
                else{
                    if(Weight[0]>Weight[1])
                        s = s + "BikeWeight: " + Code[0] + "\n";
                    else if(Weight[0]<=Weight[1])
                        s = s + "BikeWeight: " + Code[1] + "\n";
                    if(Price[0]>Price[1])
                        s = s + "BikePrice: " + Code[1] + "\n";
                    else if(Price[0]<=Price[1])
                        s = s + "BikePrice: " + Code[0] + "\n";
                    s = s.substring(0,s.length()-1);
                }
            }
        }
        return s;
    }
    public static String Discount(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "SetCarDiscount" : {
                for (Car i:Cars){
                    if (i.Code.equals(a[0])) {
                        double Discount = Double.parseDouble(a[1]);
                        if (5 <= Discount & Discount <= 15){
                            i.Price = (100 - Discount)*i.Price/100.0;
                            s = "New carPrice is " + (int)i.Price + "$!";
                        }
                        else
                            s = "Input for discount is invalid!";
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
            }
            break;
            case "SetMotorcycleDiscount" : {
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[0])) {
                        double Discount = Double.parseDouble(a[1]);
                        if (5 <= Discount & Discount <= 15){
                            i.Price = (100 - Discount)*i.Price/100.0;
                            s = "New motorcyclePrice is " + (int)i.Price + "$!";
                            break;
                        } else
                            s = "Input for discount is invalid!";
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
            }
            break;
            case "SetBikeDiscount" : {
                for (Bike i:Bikes){
                    if (i.Code.equals(a[0])) {
                        double Discount = Double.parseDouble(a[1]);
                        if (5 <= Discount & Discount <= 15){
                            i.Price = (100 - Discount)*i.Price/100.0;
                            s = "New bikePrice is " + (int)i.Price + "$!";
                            break;
                        } else
                            s = "Input for discount is invalid!";
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
            }
        }
        return s;
    }
    public static String Search(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "SearchCars" : {
                if (a[0].equals("Origin")){
                    for (Car i:Cars)
                        if (i.Origin.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No cars found with the given origin!";
                    else
                        s = s.substring(0,s.length()-1);
                }
                if (a[0].equals("Color")){
                    for (Car i:Cars)
                        if (i.Color.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No cars found with the given color!";
                    else
                        s = s.substring(0,s.length()-1);
                }
            }
            break;
            case "SearchMotorcycles" : {
                if (a[0].equals("Origin")){
                    for (Motorcycle i:Motorcycles)
                        if (i.Origin.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No motorcycles found with the given origin!";
                    else
                        s = s.substring(0,s.length()-1);
                }
                if (a[0].equals("Color")){
                    for (Motorcycle i:Motorcycles)
                        if (i.Color.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No motorcycles found with the given color!";
                    else
                        s = s.substring(0,s.length()-1);
                }
            }
            break;
            case "SearchBikes" : {
                if (a[0].equals("Origin")){
                    for (Bike i:Bikes)
                        if (i.Origin.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No bikes found with the given origin!";
                    else
                        s = s.substring(0,s.length()-1);
                }
                if (a[0].equals("Type")){
                    for (Bike i:Bikes)
                        if (i.Type.equals(a[1])){
                            s = s + i.Name + " " + i.Code + "\n";
                        }
                    if (s.equals(""))
                        s = "No bikes found with the given type!";
                    else
                        s = s.substring(0,s.length()-1);
                }
            }
        }
        return s;
    }
    public static String Least(String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (input){
            case "LeastCarPrice" : {
                double Min = Double.MAX_VALUE;
                String Name = "";
                String Code = "";
                for (Car i:Cars)
                    if (i.Price<Min) {
                        Code = i.Code;
                        Name = i.Name;
                        Min = i.Price;
                    }
                s = Name  +" "+ Code +" "+ (int) Min;
            }
            break;
            case "LeastMotorcyclePrice" : {
                double Min = Double.MAX_VALUE;
                String Name = "";
                String Code = "";
                for (Motorcycle i:Motorcycles)
                    if (i.Price<Min) {
                        Code = i.Code;
                        Name = i.Name;
                        Min = i.Price;
                    }
                s = Name  +" "+ Code +" "+ (int) Min;
            }
            break;
            case "LeastBikePrice" : {
                double Min = Double.MAX_VALUE;
                String Name = "";
                String Code = "";
                for (Bike i:Bikes)
                    if (i.Price<Min) {
                        Code = i.Code;
                        Name = i.Name;
                        Min = i.Price;
                    }
                s = Name  +" "+ Code +" "+ (int) Min;
            }
        }
        return s;
    }
    public static String Show(String []a,String input,List<Car> Cars,List<Bike> Bikes,List<Motorcycle> Motorcycles){
        String s = "";
        switch (a[0]){
            case "CarProperties" : {
                for (Car i:Cars){
                    if (i.Code.equals(a[1])) {
                        s = s + "CarBrand: " + i.Brand + "\n";
                        s = s + "CarName: " + i.Name + "\n";
                        s = s + "CarColor: " + i.Color + "\n";
                        s = s + "CarOrigin: " + i.Origin + "\n";
                    }
                }
                if (s.equals(""))
                    s = "No cars exist with the given code!";
                else
                    s = s.substring(0,s.length()-1);
            }
            break;
            case "MotorcycleProperties" : {
                for (Motorcycle i:Motorcycles){
                    if (i.Code.equals(a[1])) {
                        s = s + "MotorcycleBrand: " + i.Brand + "\n";
                        s = s + "MotorcycleName: " + i.Name + "\n";
                        s = s + "MotorcycleColor: " + i.Color + "\n";
                        s = s + "MotorcycleOrigin: "+i.Origin + "\n";
                    }
                }
                if (s.equals(""))
                    s = "No motorcycles exist with the given code!";
                else
                    s =s.substring(0,s.length()-1);
            }
            break;
            case "BikeProperties" : {
                for (Bike i:Bikes){
                    if (i.Code.equals(a[1])) {
                        s = s + "BikeBrand: " + i.Brand + "\n";
                        s = s + "BikeName: " + i.Name + "\n";
                        s = s + "BikeColor: " + i.Color + "\n";
                        s = s + "BikeOrigin: " + i.Origin + "\n";
                    }
                }
                if (s.equals(""))
                    s = "No bikes exist with the given code!";
                else
                    s = s.substring(0,s.length()-1);
            }
        }
        return s;
    }
}
public class Bike {
    String Name;
    String Brand;
    String Code;
    String Color;
    String Origin;
    String Type;
    double Price;
    double Weight;
    double WheelSize;
    Bike(String Name,String  Brand,String Code,String Type){
        this.Name = Name;
        this.Brand = Brand;
        this.Code = Code;
        this.Type = Type;
        this.Color = "";
        this.Origin = "";
        this.Price = -1;
        this.Weight = -1;
        this.WheelSize = -1;
    }
}

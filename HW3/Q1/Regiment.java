public abstract class Regiment {
    public int Number;
    public Regiment(int number){
        this.Number = number;
    }
    public abstract int returnNumber();
    public abstract void setNumber(int number);
}


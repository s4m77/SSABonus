package src;
public class Patient extends Product {
    private String type;
    private double x;
    private double y;

    public Patient(double [] location, String type){
        super();
        this.type = type;
        this.x = location[0];
        this.y = location[1];
    }

    public double[] getLocation()
    {return new double[]{this.x,this.y};}

    public String getType(){return this.type;}

    public String toString(){return this.type;}
}

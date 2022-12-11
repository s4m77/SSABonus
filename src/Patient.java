package src;
public class Patient extends Product {
    private String type;
    private double x;
    private double y;
    private double time;

    public Patient(double x, double y, String type, double time){
        super();
        this.type = type;
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public double[] getLocation()
    {return new double[]{this.x,this.y};}

    public double getTime(){return this.time;}

    public String getType(){return this.type;}
}

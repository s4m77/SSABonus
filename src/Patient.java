package src;
import java.util.ArrayList;
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

    public void stamp(double time,String event,String station)
	{
		times.add(time);
		events.add(event);
		stations.add(station);
	}
	
	public ArrayList<Double> getTimes()
	{
		return times;
	}

	public ArrayList<String> getEvents()
	{
		return events;
	}

	public ArrayList<String> getStations()
	{
		return stations;
	}
	
	public double[] getTimesAsArray()
	{
		times.trimToSize();
		double[] tmp = new double[times.size()];
		for (int i=0; i < times.size(); i++)
		{
			tmp[i] = (times.get(i)).doubleValue();
		}
		return tmp;
	}

	public String[] getEventsAsArray()
	{
		String[] tmp = new String[events.size()];
		tmp = events.toArray(tmp);
		return tmp;
	}

	public String[] getStationsAsArray()
	{
		String[] tmp = new String[stations.size()];
		tmp = stations.toArray(tmp);
		return tmp;
	}

    public double[] getLocation()
    {return new double[]{this.x,this.y};}

    public double getTime(){return this.time;}

    public String getType(){return this.type;}
}

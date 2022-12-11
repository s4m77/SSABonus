package src;

public class Ambulance extends Machine {

    private int[] location = new int[2];
    private int dock;
    private int start_of_work;
    private int end_of_work;

    public Ambulance(Queue q, ProductAcceptor s, CEventList e, String n, int[] location, int dock, int start_of_work) {
        super(q, s, e, n);
        this.location = location;
        this.dock = dock;
        this.start_of_work = start_of_work;
        int option = (int) (Math.random() * 2);
        if (start_of_work != 23 && (option == 0 || start_of_work == 21 || start_of_work == 20 || start_of_work == 19
                || start_of_work == 18)) {
            this.end_of_work = start_of_work + 4;
        } else {
            this.end_of_work = start_of_work + 8;
        }
    }

    public int[] getLocation() {
        return location;
    }
    
    @Override
	public boolean giveProduct(Product p)
	{
		// Only accept something if the machine is idle
		if(status=='i')
		{
			// accept the product
			product=p;
			// mark starting time
			product.stamp(eventlist.getTime(),"Production started",name);
			// start production
			startProduction();
			// Flag that the product has arrived
			return true;
		}
		// Flag that the product has been rejected
		else return false;
	}

    private void startProduction()
	{		Patient patient = (Patient)product;
			double x_patient = patient.getLocation()[0];
			double y_patient = patient.getLocation()[1];
			double x_ambulance = location[0];
            double y_ambulance = location[1];
            double dist_pat_amb = getManhattanDistance(x_patient, y_patient, x_ambulance, y_ambulance);
            double dist_pat_hos = getManhattanDistance(x_patient, y_patient, 0, 0);
			double duration = dist_pat_amb+drawRamdomErlang3()+dist_pat_hos;
			// Create a new event in the eventlist
			double tme = eventlist.getTime();
			eventlist.add(this,patient.getType(),tme+duration); //target,type,time
			// set status to busy
			status='b';
	}

    public double getManhattanDistance(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public static double drawRamdomErlang3(){
		double prod = 1;
		for (int i = 0; i < 3; i++) {
			prod *= Math.random();
		}
		// Erlang 3 with parameter lambda = 1
		return (-1)*Math.log(prod);
	}

}

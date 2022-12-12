package src;

/**
 *	A source of products
 *	This class implements CProcess so that it can execute events.
 *	By continuously creating new events, the source keeps busy.
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Source implements CProcess
{
	/** Eventlist that will be requested to construct events */
	private CEventList list;
	/** Queue that buffers products for the machine */
	private ProductAcceptor queue;
	/** Name of the source */
	private String name;

	/**
	*	Constructor, creates objects
	*        Interarrival times are exponentially distributed with mean 33
	*	@param q	The receiver of the products
	*	@param l	The eventlist that is requested to construct events
	*	@param n	Name of object
	*/
	public Source(ProductAcceptor q,CEventList l,String n)
	{
		list = l;
		queue = q;
		name = n;
		// put first event in list for initialization
		list.add(this,"a1",list.getTime()+drawRandomExponential(1/lambda(list.getTime()/60))); 
		list.add(this,"b",list.getTime()+drawRandomExponential(1/lambda(list.getTime()/60)));
		list.add(this,"a2",list.getTime()+drawRandomExponential(1/lambda(list.getTime()/60)));
	}

	/**
	*	Constructor, creates objects
	*        Interarrival times are exponentially distributed with specified mean
	*	@param q	The receiver of the products
	*	@param l	The eventlist that is requested to construct events
	*	@param n	Name of object
	*	@param m	Mean arrival time
	*/
        @Override
	public void execute(String type, double tme)
	{
		// show arrival
		System.out.println("Arrival at time = " + tme+" minutes");
		// give arrived product to queue
		double [] location = Region.getLocation();
		Patient p = new Patient(location, type);
		p.stamp(tme,"Creation",name);
		queue.giveProduct(p);
		// generate duration
		double duration = drawRandomExponential(1/lambda(list.getTime()/60))*60;
		// Create a new event in the eventlist
		list.add(this,type,tme+duration); //target,type,time
	}

	// use it to generate interarrival times, with mean 1/lambda (coded below)
	public static double drawRandomExponential(double mean)
	{
		// draw a [0,1] uniform distributed number
		double u = Math.random();
		// Convert it into a exponentially distributed random variate with mean 33
		double res = -mean*Math.log(u);
		return res;
	}
	// time here in HOURS
	public static double lambda(double t){
		return 3-2*Math.sin((5*Math.PI+5*t)/(6*Math.PI));
	}
}
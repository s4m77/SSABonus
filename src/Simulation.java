/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package src;

public class Simulation {

	public CEventList list;
	public Queue queue;
	public Source source;
	public Sink sink;
	public Machine mach;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Create an eventlist
		CEventList l = new CEventList();
		// A queue for the machine
		Queue queue = new Queue();
		// A source
		Source s= new Source(queue, l, "Source Center");
		Sink si = new Sink("Hospital");

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				Machine m = new Ambulance(queue, si, l, i,7*60);
			}
		}

		// start the eventlist
		l.start((24*10+7)*60); // 2000 is maximum time
	}

}

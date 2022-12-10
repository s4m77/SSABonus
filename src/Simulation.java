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
		Queue center = new Queue();
		// A source
		Queue north = new Queue();
		// A source
		Queue south = new Queue();
		// A source
		Queue north_west = new Queue();
		// A source
		Queue north_east = new Queue();
		// A source
		Queue south_west = new Queue();
		// A source
		Queue south_east = new Queue();
		// A source
		Source s_center = new Source(center, l, "Source Center");
		Source s_north = new Source(north, l, "Source 1");
		Source s_south = new Source(south, l, "Source 1");
		Source s_north_west = new Source(north_west, l, "Source 1");
		Source s_north_east = new Source(north_west, l, "Source 1");
		Source s_south_west = new Source(south_west, l, "Source 1");
		Source s_south_east = new Source(south_east, l, "Source 1");

		Sink si = new Sink("Hospital");

		Machine m_center1 = new Machine(center, si, l, "Machine Center");
		Machine m_center2 = new Machine(center, si, l, "Machine Center");
		Machine m_center3 = new Machine(center, si, l, "Machine Center");
		Machine m_center4 = new Machine(center, si, l, "Machine Center");
		Machine m_center5 = new Machine(center, si, l, "Machine Center");

		Machine m_north1 = new Machine(north, si, l, "Machine North");
		Machine m_north2 = new Machine(north, si, l, "Machine North");
		Machine m_north3 = new Machine(north, si, l, "Machine North");
		Machine m_north4 = new Machine(north, si, l, "Machine North");
		Machine m_north5 = new Machine(north, si, l, "Machine North");

		Machine m_south1 = new Machine(south, si, l, "Machine South");
		Machine m_south2 = new Machine(south, si, l, "Machine South");
		Machine m_south3 = new Machine(south, si, l, "Machine South");
		Machine m_south4 = new Machine(south, si, l, "Machine South");
		Machine m_south5 = new Machine(south, si, l, "Machine South");

		Machine m_north_west1 = new Machine(north_west, si, l, "Machine North West");
		Machine m_north_west2 = new Machine(north_west, si, l, "Machine North West");
		Machine m_north_west3 = new Machine(north_west, si, l, "Machine North West");
		Machine m_north_west4 = new Machine(north_west, si, l, "Machine North West");
		Machine m_north_west5 = new Machine(north_west, si, l, "Machine North West");

		Machine m_north_east1 = new Machine(north_east, si, l, "Machine North East");
		Machine m_north_east2 = new Machine(north_east, si, l, "Machine North East");
		Machine m_north_east3 = new Machine(north_east, si, l, "Machine North East");
		Machine m_north_east4 = new Machine(north_east, si, l, "Machine North East");
		Machine m_north_east5 = new Machine(north_east, si, l, "Machine North East");

		Machine m_south_west1 = new Machine(south_west, si, l, "Machine South West");
		Machine m_south_west2 = new Machine(south_west, si, l, "Machine South West");
		Machine m_south_west3 = new Machine(south_west, si, l, "Machine South West");
		Machine m_south_west4 = new Machine(south_west, si, l, "Machine South West");
		Machine m_south_west5 = new Machine(south_west, si, l, "Machine South West");

		Machine m_south_east1 = new Machine(south_east, si, l, "Machine South East");
		Machine m_south_east2 = new Machine(south_east, si, l, "Machine South East");
		Machine m_south_east3 = new Machine(south_east, si, l, "Machine South East");
		Machine m_south_east4 = new Machine(south_east, si, l, "Machine South East");
		Machine m_south_east5 = new Machine(south_east, si, l, "Machine South East");

		// start the eventlist
		l.start(2000); // 2000 is maximum time
	}

}

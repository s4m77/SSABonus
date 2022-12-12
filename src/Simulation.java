/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package src;
import java.io.FileWriter;
import java.io.IOException;
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

		// to  generate data for matlab
		int size = si.getNumbers().length;
        double[][] arr = new double[size/3][5];

        int idx = 0;
        for (int i = 0; i < si.getNumbers().length; i+=3) {
            arr[idx][0] = si.getNumbers()[i];
            arr[idx][1] = si.getTypes()[i];
            arr[idx][2] = si.getTimes()[i]; // creation time
            arr[idx][3] = si.getTimes()[i+1]; // start production
            arr[idx][4] = si.getTimes()[i+2]; // end prooduction
            idx++;
        }

		for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        try (FileWriter writer = new FileWriter("JavaOut.csv")) {
            for (int j = 0; j < arr.length; j++) {
                String row = String.valueOf(arr[j][0])+", "+String.valueOf(arr[j][1])+", "
                +String.valueOf(arr[j][2])+", "+String.valueOf(arr[j][3])+", "+String.valueOf(arr[j][4]);
                writer.append(row);
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



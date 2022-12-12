/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package src;
import java.util.ArrayList;
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
        double totalA1Less15Ratio = 0;
        double totalWaitingTimeA1 = 0;
        double totalWaitingTimeA2 = 0;
        ArrayList<Double> badmins = new ArrayList<>();
        double totalWaitingTimeB = 0;
        int runs = 101;
		double [][] results = new double[runs][4];

        for (int j = 0; j < runs; j++) {
                CEventList l = new CEventList();
            // A queue for the machine
            Queue queue = new Queue();
            // A source
            Source s= new Source(queue, l, "Source Center");
            Sink si = new Sink("Hospital");

            for (int i = 0; i < 7; i++) {
                for (int k = 0; k < 5; k++) {
                    Machine m = new Ambulance(queue, si, l, i,420);
                }
            }

            // 1 days simulation
            l.start(24*60+420);

            // to compute different statistics
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

            double tempTotalA1 = 0;
            double tempTotalA1Less15 = 0;
            double tempTotalWA1 = 0;
            double tempTotalWA2 = 0;
            double tempTotalWB = 0;

            for (int k = 0; k < arr.length; k++) {

                if (arr[k][1] == 1){
                    tempTotalWA1 += arr[k][3] - arr[k][2];
                    if ((arr[k][3] - arr[k][2]) <= 15){
                        tempTotalA1Less15++;
                    }else{
                        badmins.add((arr[k][3] - arr[k][2]));
                    }
                    tempTotalA1++;
                }else if(arr[k][1] == 2){
                    tempTotalWA2 += arr[k][3] - arr[k][2];
                }else if(arr[k][1] == 3){
                    tempTotalWB += arr[k][3] - arr[k][2];
                }
            }
			results[j][0] = tempTotalA1Less15/tempTotalA1;
			results[j][1] = tempTotalWA1/arr.length;
			results[j][2] = tempTotalWA2/arr.length;
			results[j][3] = tempTotalWB/arr.length;

            totalA1Less15Ratio += tempTotalA1Less15/tempTotalA1;
            totalWaitingTimeA1 += tempTotalWA1/arr.length;
            totalWaitingTimeA2 += tempTotalWA2/arr.length;
            totalWaitingTimeB += tempTotalWB/arr.length;
        }

        totalA1Less15Ratio /= runs;
        totalWaitingTimeA1 /= runs;
        totalWaitingTimeA2 /= runs;
        totalWaitingTimeB /= runs;

		double [] variances = new double[4]; 
		for (int i = 0; i < runs; i++) {
			variances[0] += Math.pow((totalA1Less15Ratio-results[i][0]),2);
			variances[1] += Math.pow((totalWaitingTimeA1-results[i][1]),2);
			variances[2] += Math.pow((totalWaitingTimeA2-results[i][2]),2);
			variances[3] += Math.pow((totalWaitingTimeB-results[i][3]),2);
			
		}
		variances[0] /= (runs-1);
		variances[1] /= (runs-1);
		variances[2] /= (runs-1);
		variances[3] /= (runs-1);

		System.out.println("Confidence interval for ratio of 15 min responding time:");
		System.out.println((totalA1Less15Ratio-1.984*Math.sqrt(variances[0]/runs))+"|"+(totalA1Less15Ratio+1.984*Math.sqrt(variances[0]/runs)));
		System.out.println("Confidence interval for waiting time of a1:");
		System.out.println((totalWaitingTimeA1-1.984*Math.sqrt(variances[1]/runs))+"|"+(totalWaitingTimeA1+1.984*Math.sqrt(variances[1]/runs)));
		System.out.println("Confidence interval for waiting time of a2:");
		System.out.println((totalWaitingTimeA2-1.984*Math.sqrt(variances[2]/runs))+"|"+(totalWaitingTimeA2+1.984*Math.sqrt(variances[2]/runs)));
		System.out.println("Confidence interval for waiting time of b:");
		System.out.println((totalWaitingTimeB-1.984*Math.sqrt(variances[3]/runs))+"|"+(totalWaitingTimeB+1.984*Math.sqrt(variances[3]/runs)));

		// for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < 4; j++) {
        //         System.out.print(arr[i][j]+" ");
        //     }
        //     System.out.println();
        // }

        // try (FileWriter writer = new FileWriter("JavaOut.csv")) {
        //     for (int j = 0; j < arr.length; j++) {
        //         String row = String.valueOf(arr[j][0])+", "+String.valueOf(arr[j][1])+", "
        //         +String.valueOf(arr[j][2])+", "+String.valueOf(arr[j][3])+", "+String.valueOf(arr[j][4]);
        //         writer.append(row);
        //         writer.append("\n");
        //     }
        //     writer.close();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
}



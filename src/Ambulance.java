package src;
public class Ambulance extends Machine {

    private static int id_counter=0;
    private int id = 0;
    private double[] location;
    private int dock;
    private double start_of_work;
    private double end_of_work;
    private boolean ambulanceIsDone = false;

    public Ambulance(Queue q, ProductAcceptor s, CEventList e, int dock, double start_of_work) {
        super(q, s, e, "Ambulance "+id_counter);
        location = new double[2];
        id = id_counter;
        id_counter++;
        this.location = getCenter(dock);
        this.dock = dock;
        this.start_of_work = start_of_work;
        this.end_of_work = start_of_work+8*60;
        eventlist.add(this, "start", start_of_work);
        eventlist.add(this, "stop", end_of_work);
        //System.out.println("Ambulance "+ id + " is created");
    }

    public double[] getLocation() {
        return location;
    }
    @Override
    public void execute(String type, double tme) {
        
        if (product!=null&&!type.equals("d")) {
            // show arrival
            
            //System.out.println("Product finished at time = " + tme+" minutes");
            // Remove product from system
            product.stamp(tme, "Production complete", name);
            sink.giveProduct(product);
            product = null;
            location[0]=0;
            location[1]=0;
        }
        if(product==null){
            if(type.equals("stop")){
                ambulanceIsDone = true;
                queue.remove(this);
                Machine m = new Ambulance(queue,sink,eventlist,dock,end_of_work);
                return;
            }
        }
        // set machine status to idle
        status = 'i';
        // Ask the queue for products
        if(type.equals("d")){
            if(queue.askProduct(this,true)){ 
            }
        }
        else if (queue.askProduct(this,false)) {
        } 
        else {
            status = 'b';
            double duration = getManhattanDistance(getCenter(dock)[0], getCenter(dock)[1], 0, 0);
            location[0] = getCenter(dock)[0];
            location[1] = getCenter(dock)[1];
            eventlist.add(this, "d", eventlist.getTime() + duration);
        }
    }

    @Override
    public boolean giveProduct(Product p) {
        // Only accept something if the machine is idle
        if (status == 'i') {
            // accept the product
            product = (Patient)p;
            // mark starting time
            Patient patient = (Patient) product;
            double x_patient = patient.getLocation()[0];
            double y_patient = patient.getLocation()[1];
            double x_ambulance = location[0];
            double y_ambulance = location[1];
            double dist_pat_amb = getManhattanDistance(x_patient, y_patient, x_ambulance, y_ambulance);
            product.stamp(eventlist.getTime()+dist_pat_amb, "Pick up started", name);
            // start production
            startProduction();
            // Flag that the product has arrived
            return true;
        }
        // Flag that the product has been rejected
        else
            return false;
    }

    private void startProduction() {
        Patient patient = (Patient) product;
        double x_patient = patient.getLocation()[0];
        double y_patient = patient.getLocation()[1];
        double x_ambulance = location[0];
        double y_ambulance = location[1];
        double dist_pat_amb = getManhattanDistance(x_patient, y_patient, x_ambulance, y_ambulance);
        double dist_pat_hos = getManhattanDistance(x_patient, y_patient, 0, 0);
        double duration = dist_pat_amb + drawRamdomErlang3() + dist_pat_hos;
        // Create a new event in the eventlist
        double tme = eventlist.getTime();
        eventlist.add(this, patient.getType(), tme + duration); // target,type,time
        // set status to busy
        status = 'b';
    }

    public double getManhattanDistance(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static double drawRamdomErlang3() {
        double prod = 1;
        for (int i = 0; i < 3; i++) {
            prod *= Math.random();
        }
        // Erlang 3 with parameter lambda = 1
        return (-1) * Math.log(prod);
    }

    public double[] getCenter(int regNumber) {
        double x = 0;
        double y = 0;
        if (regNumber == 0)
            return new double[] { x, y }; // central
        else if (regNumber == 1)
            return new double[] { x, y + 5 * Math.sqrt(3) }; // north
        else if (regNumber == 2)
            return new double[] { x + 7.5, y + 5 * Math.sqrt(3) / 2 }; // north east
        else if (regNumber == 4)
            return new double[] { x, y - 5 * Math.sqrt(3) };// south east
        else if (regNumber == 3)
            return new double[] { x + 7.5, y - 5 * Math.sqrt(3) / 2 };// south
        else if (regNumber == 5)
            return new double[] { x - 7.5, y - 5 * Math.sqrt(3) / 2 };// south west
        else
            return new double[] { x - 7.5, y + 5 * Math.sqrt(3) / 2 }; // north west
    }

}

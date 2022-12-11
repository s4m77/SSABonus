package src;

public class Ambulance extends Machine {

    private int[] location = new int[2];
    private int dock;
    private int start_of_work;
    private int end_of_work;

    public Ambulance(Queue q, ProductAcceptor s, CEventList e, String n, double m,int[]location,int dock,int start_of_work) {
        super(q, s, e, n, m);
        this.location = location;
        this.dock = dock;
        this.start_of_work = start_of_work;
        int option = (int)(Math.random()*2);
        if(start_of_work!=23&&(option==0||start_of_work==21||start_of_work==20||start_of_work==19||start_of_work==18)){
            this.end_of_work = start_of_work+4;
        }
        else{
            this.end_of_work = start_of_work+8;
        }
    }
    
}

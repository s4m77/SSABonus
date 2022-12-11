package src;

import java.util.ArrayList;

/**
 *	Queue that stores products until they can be handled on a machine machine
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Queue implements ProductAcceptor
{
	/** List in which the products are kept */
	private ArrayList<Patient> row;
	/** Requests from machine that will be handling the products */
	private ArrayList<Machine> requests;
	
	/**
	*	Initializes the queue and introduces a dummy machine
	*	the machine has to be specified later
	*/
	public Queue()
	{
		row = new ArrayList<>();
		requests = new ArrayList<>();
	}

	/**
	 * Orders patients based on their level of priority
	 * @param p the patient we want to add to the queue
	 */
	public void orderInQueue(Patient patient){
		// get type of patient
		String pType = patient.getType();
		boolean toAdd = true;
		// we want to prioritise patients A1
		if (pType.equals("a1")){
			for (int i = 0; i < row.size(); i++) {
				Patient currPatient = row.get(i);
				// if it finds a patient that is not of level A1 it will be added before that
				if (!currPatient.getType().equals("a1")){
					row.add(i,patient);
					toAdd = false;
					break;
				}
			}
			// if all patients were just A1 or there is no other patient
			if (toAdd) row.add(patient);
		}
		else if (pType.equals("b")){
			for (int i = 0; i < row.size(); i++) {
				Patient currPatient = row.get(i);
				// we want to put the patient before type A2 but after type A1
				if (!(currPatient.getType().equals("b") || currPatient.getType().equals("a1"))){
					row.add(i,patient);
					toAdd = false;
					break;
				}
			}
			if (toAdd) row.add(patient);
		}else{
			// if patient is of type A2 we don't care
			row.add(patient);
		}
		
	}
	
	/**
	*	Asks a queue to give a product to a machine
	*	True is returned if a product could be delivered; false if the request is queued
	*/
	public boolean askProduct(Machine machine,boolean makeRequest)
	{
		// This is only possible with a non-empty queue
		if(row.size()>0)
		{
			// If the machine accepts the product
			if(machine.giveProduct(row.get(0)))
			{
				row.remove(0);// Remove it from the queue
				return true;
			}
			else
				return false; // Machine rejected; don't queue request
		}
		else
		{	
			if(makeRequest)
				requests.add(machine);
			return false; // queue request
		}
	}
	
	/**
	*	Offer a product to the queue
	*	It is investigated whether a machine wants the product, otherwise it is stored
	*/
	public boolean giveProduct(Product p)
	{
		
		// Check if the machine accepts it
		if(requests.size()<1)
			this.orderInQueue((Patient)p);
			//row.add((Patient)p); // Otherwise store it
		else
		{
			boolean delivered = false;
			while(!delivered & (requests.size()>0))
			{
				delivered=requests.get(0).giveProduct(p);
				// remove the request regardless of whether or not the product has been accepted
				requests.remove(0);
			}
			if(!delivered)
				this.orderInQueue((Patient)p);
				//row.add((Patient)p); // Otherwise store it
		}
		return true;
	}
}
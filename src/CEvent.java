/**
 *	Event class
 *	Events that facilitate changes in the simulation
 *	@author Joel Karel
 *	@version %I%, %G%
 */
package src;

public class CEvent
{
	/** The object involved with the event */
	private CProcess target;
	/** The type of the event */
	private String type;
	/** The time on which the event will be executed */
	private double executionTime;

	/**
	*	Constructor for objects
	*	@param dl	The object that will process the event
	*	@param tp	The type of the event
	*	@param tme	The time on which the event will be executed
	*/
	public CEvent(CProcess dl,String tp, double tme)
	{
		target=dl;
		type=tp;
		executionTime=tme;
	}
	public String getType() {
        return type;
    }
	/**
	*	Method to signal the target to execute an event
	*/
	public void execute()
	{
		target.execute(type,executionTime);
	}
	
	/**
	*	Method to ask the event at which time it will be executed
	*	@return	The time at which the event will be executed
	*/
	public double getExecutionTime()
	{
		return executionTime;
	}


}

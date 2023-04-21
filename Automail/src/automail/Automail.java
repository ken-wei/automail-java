package automail;

import java.util.LinkedList;

import automail.MailPool.Item;
import simulation.IMailDelivery;

public class Automail {
	      
    public Robot[] robots;
    public MailPool mailPool;
    public ChargeDatabase chargeDatabase;
    
    public Automail(MailPool mailPool, IMailDelivery delivery, int numRobots, ChargeDatabase chargeDatabase) {  	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
    	/** Initialize robots */
    	robots = new Robot[numRobots];
    	for (int i = 0; i < numRobots; i++) robots[i] = new Robot(delivery, mailPool, chargeDatabase, i);
    	
    	/** Initialize ChargeDatabase */
    	this.chargeDatabase = chargeDatabase;
    }
    
    /**
     * Add mails in mailPool into chargeDatabase, initialize charge for each mail
     */
    public void addToCharge() {
    	LinkedList<Item> pool = mailPool.getPool();
    	
    	for (Item element: pool) {
    		if (! chargeDatabase.checkExist(element.mailItem.id)) {
    			chargeDatabase.addToChargeDatabase(element.mailItem.id);
			}
         }
    }
    
}

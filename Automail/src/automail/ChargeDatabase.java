package automail;

import java.util.ArrayList;
import java.util.Iterator;


public class ChargeDatabase {
	private static final double ACTIVITY_UNIT_PRICE = 0.224;
	private static final double MARKUP_PERCENT = 0.059;
	private static final double CHARGE_THRESHOLD = 0;
	
	/** ArrayList of all mail items */
	private ArrayList<MailCharge> mailList;
	
	/**
	 * Charge for each mail item in the database
	 */
	private class MailCharge {
		private String mailId;
		private double serviceFee;
		private double activityUnit;
		private double activityCost;
		private double cost;
		private double charge;
		
		/**
		 * Constructor for each mail charge
		 */
		public MailCharge(String mailId) {
			this.mailId = mailId;
			this.serviceFee = 0;
			this.activityUnit = 0;
			this.activityCost = 0;
			this.cost = 0;
			this.charge = 0;
		}
	}
	
	/**
	 * Constructor for ChargeDatabse
	 */
	public ChargeDatabase() {
		mailList = new ArrayList<MailCharge>();
	}
	
	/**
	 * Adds a mail charge item to the charge database
	 * @param mailId the id of mail item being added
	 */
	public void addToChargeDatabase(String mailId) {
		MailCharge newItem = new MailCharge(mailId);
		
		mailList.add(newItem);
		System.out.printf("Add new mail id:%s entry in charge databse\n", newItem.mailId);
	}
	
	/**
	 * Print all mail charge elements in the database
	 */
	public void printDatabase() {
		for (MailCharge element:mailList) {
			System.out.printf("[Mail Item:: ID: %s | Charge: xx | Cost: xx | Fee: %.2f | ActivityUnit: %.2f | ActivityCost: %.2f]\n", element.mailId, element.serviceFee, element.activityUnit, element.activityCost);
		}
	}
	
	/**
	 * Find mail charge item in the database according to mail id
	 * @param mailId mail id
	 * @return found mail charge item
	 */
	public MailCharge findMailCharge(String mailId) {
		for (MailCharge item:mailList) {
			if (item.mailId.equals(mailId)) {
				System.out.println("Found charge item");
				return item;
			}
		}
		System.out.println("Can't Find charge item");
		return null;
	}
	
	/**
	 * Check if the mail exists in the charge database already
	 * @param mailId mail id
	 * @return result of exist or not
	 */
	public Boolean checkExist(String mailId) {
		for (MailCharge item:mailList) {
			if (item.mailId.equals(mailId)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Update move cost of robot for mail charge
	 * @param mailId mail id
	 */
	public void updateMoveCost(String mailId) {
		MailCharge item = findMailCharge(mailId);
		
		item.activityUnit += 5;
	}
	
	/**
	 * Update service fee look up cost for mail charge
	 * @param mailId mail id
	 */
	public void updateLookUpCost(String mailId) {
		MailCharge item = findMailCharge(mailId);
		
		item.activityUnit += 0.1;
	}
	
	/**
	 * Calculate and update activity cost for mail item
	 * @param mailId mail id
	 */
	public void calculateActivityCost(String mailId) {
		MailCharge item = findMailCharge(mailId);
		
		item.activityCost = item.activityUnit * ACTIVITY_UNIT_PRICE;
	}
	
	/**
	 * Calculate and update cost for mail item
	 * @param mailId mail id
	 */
	public void calculateCost(String mailId) {
		MailCharge item = findMailCharge(mailId);
		
		item.cost = item.serviceFee + item.activityCost;
	}
	
	/**
	 * Calculate and update total charge for mail item
	 * @param mailId mail id
	 */
	public void calculateTotalCharge(String mailId) {
		MailCharge item = findMailCharge(mailId);
		
		item.charge = item.cost * (1 + MARKUP_PERCENT);
	}
}

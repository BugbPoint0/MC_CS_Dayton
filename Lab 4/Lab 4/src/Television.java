
/** 
* The purpose of this class is to model a television
* David Dayton 2/23/20
*/

public class Television {

		// Declare constant vars
	private final String MANUFACTURER; // The manufacturer attribute will hold the brand name. 
	private final int SCREEN_SIZE; // The screenSize attribute will hold the size of the television screen
		// Declare vars
	private int channel; // The channel attribute will hold the value of the station that the television is showing.
	private int volume; // The volume attribute will hold a number value representing the loudness (0 being no sound).
	private boolean powerOn; //The powerOn attribute will hold the value true if the power is on, and false if the power is off. 
	
	
		// The constructor will "return" a Television object and initialize variables
	Television(String manufacturer, int screenSize)
	{
			// Initialize variables
		SCREEN_SIZE = screenSize; 
		MANUFACTURER = new String(manufacturer); 
		volume = 20;
		channel = 2;
		powerOn = false;
	}
	
		/** Getter for channel (returns channel) */
	public int getChannel() { return channel; }
	
		/** Getter for volume (returns volume) */
	public int getVolume() { return volume; }
	
		/** Getter for SCREEN_SIZE (returns SCREEN_SIZE) */
	public int getScreenSize() { return SCREEN_SIZE; }
	
		/** Getter for MANUFACTURER (returns MANUFACTURER) */
	public String getManufacturer() { return MANUFACTURER; }
	
		/** Setter for channel (sets channel to whatever int gets passed in) */
	public void setChannel(int theChannel) { channel = theChannel; }
	
		/** A function to toggle power on and off (make powerOn true and false) */
	public void power() { powerOn = !powerOn; } 
	
	/** Increments volume by one */
	public void increaseVolume() { volume++; }
	
	/** Decrements volume by one */
	public void decreaseVolume() { volume--; }

	
}

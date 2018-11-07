package Game.Model;

import java.util.ArrayList;

/**
 * This class is used to create cards according to the Risk rules. The created
 * cards can then be assigned to players, and they receive additional army for
 * exchanging cards.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class Cards {

	 
	private String name;
	
	/**
	 * Stores the type of the card.
	 */
	private String type;
	
	/**
	 * Constructor to assign value to name and type of the card.
	 * @param name Stores the name of the card.
	 * @param type Stores the type of the card.
	 */
	public Cards(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Function to get the name of the card.
	 * @return name of the card.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Function to get the type of the card.
	 * @return type of the card.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Generate a pile of 44 cards
	 * @return ArrayList containing 44 cards.
	 */
	public static ArrayList<Cards> generateCardPile() {
		ArrayList<Cards> cardPile = new ArrayList<Cards>();
		for(int i=0;i<44;i++) {
			cardPile.add(new Cards("Artillery","Normal"));
		}
		return cardPile;
	}
}

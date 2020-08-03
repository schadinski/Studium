/**@author K.Schwab
 * OOP Labor 1.3
 */
public class Dice {

	/**Roll one die.
	 * Values between 1 and 6
	 * @return Integer for the value of the die
	 */
	public static int rollDie() {
		int number = (int) (Math.random() * 5 + 1);
		return number;
	}

	/**Roll dice and save it in an array.
	 * @param n Integer for the number of dice
	 * @return Array of Integer filled with random dice values
	 */
	public static int[] rollDice(int n) {
		int[] rollResults;
		rollResults = new int[n];

		//n have to be min 2, max 6
		if(n >=2 && n <= 6) {
			for (int i = 0; i < n; i++) {
			rollResults[i] = rollDie();
		}
		}	
		else{
			System.out.println("Number of dice are wrong.");
		}

		return rollResults;
	}

	/**Roll dice until all results are equal.
	 * @param n Integer for the number of dice
	 * @return Integer for number of trials
	 */
	public static int bisZumPasch(int n) {
		int trial = 0;							//how often were the dice rolled
		boolean isPasch = false;

		int[] diceArray;
		diceArray = new int[n];

		while (isPasch == false) {
			diceArray = rollDice(n);			//fills the array with random values
			trial++;
			isPasch = isItPasch(diceArray);
		}
		return trial;
	}

	/** Find out if all results are equal.
	 * @param diceArray integer array filled with random values
	 * @return Boolean 
	 */
	public static boolean isItPasch(int[] diceArray) {
		boolean pasch = true;
		int i=0;
		int n = diceArray.length;
	
		while(pasch==true && i<n-1){
			if(diceArray[i] != diceArray[i+1]){
				return false;
			}
			else{
				i++;
			}
		}
		
		return true;
	}

	/**Main to call all methods with different numbers of dice.
	 * Outputs all results on screen.
	 * @param args
	 */
	public static void main(String[] args) {

		int numberOfRolls;
		//check all methods with number of dice between 2 and 6
		for (int n = 2; n < 7; n++) {
			numberOfRolls = bisZumPasch(n);
			System.out.println("Bei " + n + " Würfeln wurden " + numberOfRolls + " Würfe gemacht.");
		}

	}

}

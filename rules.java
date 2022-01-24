
import javax.swing.JOptionPane;

public class rules {

	rules()
	{
		JOptionPane.showMessageDialog(null, "Wheel of Fortune Game Rules (2 Player)\n"
						+ "Special Wheel Wedges:\n"
						+ "     - Lose a Turn: If a contestant spins this, they are skipped and the round moves on to the next contestant.\n"
						+ "     - Bankrupt: The contestant will lose their turn AND lose all the money they earned in the current round so far.\n\n"
						+ "Contestants have three options:\n"
						+ "     1) Spin the wheel and call a consonant (each consonant is worth the cash value of the wedge the wheel lands on).\n"
						+ "        		 Contestants can continue spinning the wheel until they miss a letter, spin a bankrupt, or lose a turn.\n"
						+ "		2) Buy a vowel for $250 (the contestant must have enough money for THAT ROUND)\n"
						+ "		3) Solve the puzzle.\n\n"
						+ "At all times, a contestant will know:\n"
						+ "     1) Which consonants/vowels are on the board and therefore apart of the phrase to be guessed\n"
						+ "     2) Which consonants/vowels are still available for guessing\n"
						+ "     3) If a contestant guesses an incorrect consonant/vowel, all contestants will know that the consonant/vowel\n"
						+ "		     is not apart of the phrase\n\n"
						+ "Note:\n"
						+ "		-  Once a consonant/vowel is guessed it cannot be re-guessed.\n"
						+ "		-  If the attempt to solve is incorrect, the round moves on to the next contestant\n"
						+ "     -  The contestant doesn't win any money by solving the correct phrase\n\n"
						+ "Winning the game:\n"
						+ "     After 3 puzzle rounds have been played, the contestant with the most cumulative points wins the main game and\n"
						+ "     moves on to the bonus round to become the 'Big Winner.'\n\n", "How to Play", JOptionPane.INFORMATION_MESSAGE);
	}

}

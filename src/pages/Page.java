package pages;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Abstract class which extends JFrame
 * @author Ebubekir Karamustafa
 *
 */

public abstract class Page extends JFrame{
	
	protected Font contentFont;
	protected ImageIcon logo;
	
	/**
	 * Page is a abstract class that has 
	 * <p> <b> protected </b> Font contentFont: To standartize the text with certain font</p>
	 * <p> <b> protected </b> ImageIcon logo: To standartize the logo with certain image </p>
	 * <p></p>
	 * @param title heading of the page
	 */
	public Page(String title) {
		super(title);
		contentFont = new Font("Times New Roman", Font.PLAIN, 12);
		logo = new ImageIcon(System.getProperty("user.dir")+ "/img/letter-e.png");
		setIconImage(logo.getImage());
		setResizable(false);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Refresh the content of the page if any changes made.
	 */
	public abstract void refreshContent();

}

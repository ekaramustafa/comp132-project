
package content;

import java.awt.Component;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pages.Page;
import pages.User;

/**
 * Concrete class for contents
 * @author Ebubekir Karamustafa
 *
 */
public class Content {
	
	/**
	 * title of the contnet
	 */
	private String title;
	/**
	 * creation time of the content
	 */
	private Timestamp creationTime;
	/**
	 * creator User of the the content
	 */
	private User creator;
	/**
	 * text content of the content
	 */
	private String content;
	/**
	 * image of the content 
	 */
	private ImageIcon icon;
	/**
	 * path of the icon
	 */
	private String path;
	/**
	 * label to show the image
	 */
	private JLabel image;
	
	/**
	 * Initilizate the field variables 
	 * @param title title of the content
	 * @param time creation time of the content
	 * @param creator creator user of the content
	 * @param content text content of the content
	 * @param path path of the image of the content
	 */
	
	public Content(String title, Timestamp time, User creator, String content, String path) {
		//init of title
		this.title = title;
		//init of creation time
		this.creationTime = time;
		//init of creator
		this.creator = creator;
		//init of text content
		this.content = content;
		
		//init of image label
		image = new JLabel();
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//init of the icon 
		//if path is null then any file is selected
		if(path != null) {
		this.path = path;
		icon = new ImageIcon(System.getProperty("user.dir") + "/img/"+path);
		image.setIcon(icon);
		}
	}

	/**
	 * returns the title of the content
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets title of the content to new string
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns the creation time of the content
	 * @return creation time
	 */
	public Timestamp getCreationTime() {
		return creationTime;
	}

	/**
	 * returns the creator of the content
	 * @return creator
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * returns the text content of the content
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * sets the text content of the content to new string value
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * returns the icon of the content
	 * @return icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * sets the icon of the content to new icon
	 * @param icon
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	/**
	 * enables user to set the icon of the content to new icon by just path of the icon
	 * @param path
	 */
	public void setIconByPath(String path) {
		icon = new ImageIcon(path);
		setIcon(icon);
	}

}

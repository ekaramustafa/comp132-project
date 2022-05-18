package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import content.Content;
import dialog.EditContentDialog;
import dialog.GroupDialog;
import dialog.ProfileDialog;

/**
 * Concrete subclass of Page for User and Profile Pages
 * @author Ebubekir Karamustafa
 *
 */
public class User extends Page implements ActionListener{
	/**
	 * nickname of the user
	 */
	private String nickname;
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * name of the user
	 */
	private String name;
	/**
	 * surname of the user
	 */
	private String surname;
	/**
	 * age of the user
	 */
	private int age;
	/**
	 * email of the user
	 */
	private String email;
	/**
	 * represent whether the user premium or not
	 */
	private boolean isPremium;
	/**
	 * default path for profile photo
	 */
	private String defaultImagePath = String.format(System.getProperty("user.dir") + "/img/loo.jpeg");
	/**
	 * hobbies of the user
	 */
	private String hobbies;
	/**
	 * the current user who is displaying the profile page
	 */
	private User currentUser;
	/**
	 * represent whether the user displaying its own page
	 */
	private boolean isOwnProfilPage;
	
	/**
	 * image for profilephoto
	 */
	private ImageIcon profilPhoto; 
	
	/**
	 * list of contents of the user
	 */
	private LinkedList<Content> contents = new LinkedList<>();
	
	/**
	 * list of the followers of the user
	 */
	
	private LinkedList<User> followers = new LinkedList<User>();
	/*8
	 * list of the following users of the user
	 * 
	 */
	private LinkedList<User> following = new LinkedList<User>();
	
	/**
	 * list of the groups which the user attended
	 */
	private ArrayList<GroupPage> groups = new ArrayList<GroupPage>();
	
	/**
	 * list of the all users that have been created so far
	 */
	public static ArrayList<User> allUsers= new ArrayList<User>();
	
	
	//GUI ELEMENTS OF THE PROFILE PAGE OF THE USER
	/**
	 * to contains profile photo
	 */
	private JLabel iconLabel;
	/**
	 * for deleting the account
	 */
	private JButton deleteButton;
	/**
	 * for loggin out
	 */
	private JButton logOutButton;
	/**
	 * for editing the account
	 */
	private JButton editButton;
	/**
	 * for returning the home page
	 */
	private JButton returnHomePage;
	/**
	 * for editing the groups
	 */
	private JButton groupManagement;
	/**
	 * for containing the contents which the user created for its profile page
	 */
	private static JScrollPane contentPane;
	/**
	 * user's fullname
	 */
	private JLabel fullName;
	/**
	 * users's email
	 */
	private JLabel emailLabel;
	/**
	 * representing if the user is premium
	 */
	private JLabel premiumLabel;
	/**
	 * for displaying the hobbies of the user
	 */
	private JTextArea hobbiesArea;
	
	/**
	 * top panel for the profile page
	 */
	private JPanel topPanel;
	/**
	 * parent for JDialogs
	 */
	private Page parent = this;
	
	
	/**
	 * This constructor is for creating the user, it is not for setting the visual profile page
	 * @param nickname nickname of the user
	 * @param password password of the user
	 * @param name name of the user
	 * @param surname surname of the user
	 * @param age age of the user
	 * @param email email of the user
	 * @param isPremium represents if the user is premium
	 * @param path path of the profile photo-optional
	 * @param hobbies hobbies of the user-optional
	 */
	public User(String nickname, String password, String name, String surname, int age, String email, boolean isPremium, String path, String hobbies) {
		super("Profil Page");
		//checks whether the path is empty
		if(path == null || !Files.exists(Paths.get(System.getProperty("user.dir")+"/img/"+path))) {
		profilPhoto = new ImageIcon(defaultImagePath);
		}
		//if no then assign the given path
		else {
			profilPhoto = new ImageIcon(System.getProperty("user.dir")+"/img/"+path);
		}
		//then set the profile photo
		setProfilPhoto(profilPhoto);
		
		//since hobbies are optional check whether it is empty or not then assign according the if statement
		if(hobbies != null) {
			this.hobbies = hobbies;
		}
		else {
			this.hobbies = "";
		}
		//inits of field variables
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age =age;
		this.email = email;
		this.isPremium = isPremium;
		
		//add created user into the all users list
		allUsers.add(this);
		
		//since we do not instansiate any frame the user does not display its own profile page
		isOwnProfilPage = false;
		
		
	}
	/**
	 * This constructor for instansiating the profile page of a user
	 * @param currentUser logged in user
	 */
	public User(User currentUser) {
		super("Profil Page");
		this.currentUser = currentUser;
		allUsers.add(this);
		
		//Setting the frame
		setSize(600,800);
		setLocationRelativeTo(null); 
		setResizable(false);
		setLayout(new BorderLayout());
		
		//since the user is displaying its own page isOwnProfilPage has to be true
		isOwnProfilPage = true;
		
		
		//init of the contentPane
		contentPane = new JScrollPane();
		contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		//init of the topPanel
		topPanel = new JPanel();
		
		//adding top panel to the frame
		currentUser.getContentPane().add(topPanel,BorderLayout.NORTH);
		
		//adding the contentPane to the frame
		add(contentPane,BorderLayout.CENTER);
		
		if(isOwnProfilPage) {
			//setting the own profile page
			setOwnProfilPage();
		}
		
		//refresh the content
		refreshContent();
		setVisible(false);
	}
	
	/**
	 * Sets the profile page of the current user.
	 */
	
	private void setOwnProfilPage() {
		//removing the top panel first
		topPanel.removeAll();
		
		//init of icon label to contains photo
		iconLabel = new JLabel();
		iconLabel.setIcon(currentUser.getProfilPhoto());
		
		//inits of the delete&edit&returnHomePage&groupManagement&logOut buttons
		deleteButton = new JButton("Delete");
		editButton = new JButton("Edit");
		returnHomePage = new JButton("Return Home");
		groupManagement = new JButton("Manage Groups");
		logOutButton = new JButton("Logout");
		
		//init of the fullname label
		fullName = new JLabel(currentUser.getName() + " " + currentUser.getSurname() + " (" + currentUser.getNickname() + ") ");
		fullName.setAlignmentX(CENTER_ALIGNMENT);
		
		//init of the email label
		emailLabel = new JLabel(currentUser.getEmail());
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		//init of the premium label represent whether the user is premium or not
		premiumLabel = new JLabel(currentUser.isPremium() ? "Premium" : "Standart");
		premiumLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		//init of the hobbies area
		hobbiesArea = new JTextArea(currentUser.getHobbies());
		hobbiesArea.setLineWrap(true);
		hobbiesArea.setEditable(false);
		
		//BORDERS
		Border generalMargin = BorderFactory.createEmptyBorder(20,20,20,20);
		Border generalBorder = BorderFactory.createCompoundBorder(generalMargin, BorderFactory.createRaisedSoftBevelBorder());
		
			//TOP PANEL SETTINGS
			topPanel.setLayout(new BorderLayout());
			topPanel.setBorder(generalBorder);
			
			topPanel.add(iconLabel,BorderLayout.WEST);
				
				JPanel topRightPanel = new JPanel();
				topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.Y_AXIS));
				
				topRightPanel.add(fullName);
				fullName.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				topRightPanel.add(emailLabel);
				emailLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
				topRightPanel.add(premiumLabel);
				premiumLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
				
				hobbiesArea.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
				topRightPanel.add(hobbiesArea);
			
	
				
				JPanel topBottomPanel = new JPanel();
				topBottomPanel.setLayout(new BoxLayout(topBottomPanel, BoxLayout.X_AXIS));
				
				//creating horizantal glue for desing purposes
				topBottomPanel.add(Box.createHorizontalGlue());
				
				//if the user not premium then do not add the groupManagement button to panel
				if(currentUser.isPremium) {
				topBottomPanel.add(groupManagement);
				groupManagement.addActionListener(this);
				}
				
				topBottomPanel.add(editButton);
				editButton.addActionListener(this);
				topBottomPanel.add(deleteButton);
				deleteButton.addActionListener(this);
				topBottomPanel.add(returnHomePage);
				returnHomePage.addActionListener(this);
				topBottomPanel.add(logOutButton);
				logOutButton.addActionListener(this);
			
			
			topPanel.add(topBottomPanel, BorderLayout.SOUTH);
			topPanel.add(topRightPanel, BorderLayout.CENTER);

		add(topPanel,BorderLayout.NORTH);
		
		
	}
	
	/**
	 * refreshing Top Panel where the users information is displayed
	 */
	private void refreshTopPanel() {
		fullName.setText(currentUser.getName() + " " + currentUser.getSurname() + " (" + currentUser.getNickname() + ") ");
		emailLabel.setText(currentUser.getEmail());
		hobbiesArea.setText(currentUser.getHobbies());
		iconLabel.setIcon(currentUser.getProfilPhoto());
	}

	/**
	 * <p>implemented to refresh the content if any changes are made.</p>
	 * <p>in order to see the details of the refreshContent see Homepage.java </p>
	 */
	public void refreshContent() {
		
		contentPane.getViewport().removeAll();
		JPanel Panel = new JPanel();
		Panel.setLayout(new BoxLayout(Panel,BoxLayout.Y_AXIS));
		
		Collections.reverse(currentUser.getContents());
		
		for(Content content : currentUser.getContents()) {
			JLabel title = new JLabel(content.getTitle(),SwingConstants.CENTER);
			title.setFont(new Font("Times New Roman",Font.BOLD,18));
			JTextArea contentText = new JTextArea(content.getContent());
			contentText.setFont(new Font("Time New Roman", Font.PLAIN, 13));
			contentText.setEditable(false);
			contentText.setLineWrap(true);
			
		
			contentText.setBorder(BorderFactory.createLineBorder(Color.gray));

			
			JButton edit = new JButton("Edit");
			JButton delete = new JButton("Delete");
		

			
			JLabel time = new JLabel(content.getCreationTime().toString(),SwingConstants.CENTER);
			time.setFont(new Font("Times New Roman", Font.PLAIN,14));
			User creator = content.getCreator();
			JLabel creatorFullName = new JLabel(creator.getName()+" "+creator.getSurname() + " (" + creator.getNickname() + ") ",SwingConstants.CENTER);
			creatorFullName.setFont(new Font("Times New Roman",Font.ITALIC,14));
			
			
			Border lines = BorderFactory.createLineBorder(Color.gray);
			Border mainPanelBorder = BorderFactory.createCompoundBorder(lines,BorderFactory.createEmptyBorder(10,10,10,10));
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.setBorder(mainPanelBorder);
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			topPanel.add(title, BorderLayout.NORTH);
			topPanel.add(time, BorderLayout.CENTER);
			topPanel.add(creatorFullName,BorderLayout.SOUTH);
			JPanel midPanel = new JPanel();
			midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
			midPanel.add(contentText);
			if(content.getIcon() != null) {
			ImageIcon icon_old = content.getIcon();
			ImageIcon icon = scaleImg(icon_old);
			JLabel image = new JLabel(icon);
			image.setAlignmentX(CENTER_ALIGNMENT);
			midPanel.add(image);
			}
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
			bottomPanel.add(edit);
			bottomPanel.add(Box.createHorizontalGlue());
			bottomPanel.add(delete);
			
			
			
			mainPanel.add(topPanel,BorderLayout.NORTH);
			mainPanel.add(midPanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.setMinimumSize(new Dimension(900,800));
			Panel.add(mainPanel);
			edit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Content current: currentUser.getContents()) {
						if(current.getTitle().equals(title.getText()) && current.getCreator().getName().equals(creator.getName()) && current.getContent().equals(contentText.getText())) {
							Content selected = current;
							new EditContentDialog(parent,selected);
							refreshContent();
							
						}
					}
					
				}	
			});
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Content current: currentUser.getContents()) {
						if(current.getTitle().equals(title.getText()) && current.getCreator().getName().equals(creator.getName()) && current.getContent().equals(contentText.getText())) {
							currentUser.getContents().remove(currentUser.getContents().indexOf(content));
							refreshContent();
						}
					}
					
				}
				
			});
		}
		contentPane.setViewportView(Panel);
		contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		contentPane.getViewport().setVisible(true);
		contentPane.setVisible(true);
		
		

		
	}
	
	/**
	 * scales the icon with some standarts and returns scaled icon
	 * @param icon icon that is going to be scaled
	 * @return icon
	 */
	private ImageIcon scaleImg(ImageIcon icon) {
		// TODO Auto-generated method stub
		Image image = icon.getImage();
		Image newImg = image.getScaledInstance(320, 240, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		return icon;
	}
	
	/**
	 * Overriden to assign tasks to buttons
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//edit button instaniate Profile Dialog
		if(arg0.getSource() == editButton) {
			new ProfileDialog(currentUser, currentUser);
			//refresh the top panel with new values
			refreshTopPanel();
			
		}
		//delete button deletes the account and returns the Login Page
		else if(arg0.getSource() == deleteButton) {
			HomePage.returnGroupPage = false;
			HomePage.returnHomePage = false;
			HomePage.returnProfilPage = false;
			HomePage.returnLoginPage = true;
			allUsers.remove(currentUser);
			
			setVisible(false);
			
			
		}
		//returns the user to homepage
		else if (arg0.getSource() == returnHomePage) {
			HomePage.returnHomePage = true;
			HomePage.returnLoginPage = false;
			HomePage.returnProfilPage = false;
			setVisible(false);
			currentUser.setVisible(false);
		}
		//instansiate GroupDialog for premium users
		else if (arg0.getSource() == groupManagement) {
			new GroupDialog(this, currentUser);
		}
		//returns the user login page
		else if (arg0.getSource() == logOutButton) {
			HomePage.returnGroupPage = false;
			HomePage.returnHomePage = false;
			HomePage.returnLoginPage = true;
			HomePage.returnProfilPage = false;
			setVisible(false);
		}
		
	}


	/**
	 * <p>follows the given user. Adds the given user to following list of the current user </p>
	 * <p>and adds the current user to follower list of the given user</p>
	 * @param other followed user
	 */
	public void follow(User other) {
		following.add(other);
		other.getFollowers().add(this);
	}
	/**
	 * <p>unfollows the given user. Removes the given user from following list of the current user </p> 
	 * <p>and removes the current user from follower list of the given user</p>
	 * @param other unfollowed user
	 */
	public void unfollow(User other) {
		following.remove(other);
		other.getFollowers().remove(this);
	}
	/**
	 * <p>joins the given group</p>
	 * <p>adds given group to group list of the current user</p>
	 * <p>adds current user to user list of the given group</p>
	 * @param group joined group
	 */
	
	public void joinGroup(GroupPage group) {
		groups.add(group);
		group.getUsers().add(this);
	}
	/**
	 * <p>leaves from the given group</p>
	 * <p>removes given group from the group list of the current user</p>
	 * <p>removes current user from the user list of the given group</p>
	 * @param group leaved group
	 */
	public void leaveGroup(GroupPage group) {
		groups.remove(group);
		group.getUsers().remove(this);
	}
	

	
	//GETTERS AND SETTERS
	/**
	 * returns nickname of user
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * sets current user's nickname to given value
	 * @param nickname new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * returns password of user
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * sets password of the user to given value
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * returns name of user
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets name of the user to given value
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * returns surname of user
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * sets surname of the user to given value
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * returns age of user
	 * @return age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * sets age of the user to given value
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * returns email of user
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * sets email of the user to given value
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * returns list of the users that have been created so far
	 * @return all users
	 */
	public static ArrayList<User> getAllUsers() {
		return allUsers;
	} 
	
	/**
	 * return the list of the followers list
	 * @return followers
	 */
	public LinkedList<User> getFollowers() {
		return followers;
	}
	/**
	 * return the list of the following user list
	 * @return following users
	 */
	public LinkedList<User> getFollowing() {
		return following;
	}

	/**
	 * returns contents of user
	 * @return contents
	 */
	public LinkedList<Content> getContents() {
		return contents;
	}

	/**
	 * returns whether user is premium or not
	 * @return isPremium
	 */
	public boolean isPremium() {
		return isPremium;
	}
	
	/**
	 * returns the profile photo of the user
	 * @return
	 */
	public ImageIcon getProfilPhoto() {
		return profilPhoto;
	}
	/**
	 * sets the profile photo of the user to new photo
	 * @param profilPhoto
	 */
	public void setProfilPhoto(ImageIcon profilPhoto) {
		this.profilPhoto = profilPhoto;
	}
	/**
	 * gets a path, create an image and sets the profile photo of the user to created image
	 * @param path
	 */
	public void setIconByPath(String path) {
		ImageIcon icon = new ImageIcon(path);
		setProfilPhoto(icon);
	}
	/**
	 * returns the default image path
	 * @return default image path
	 */
	public String getDefaultImagePath() {
		return defaultImagePath;
	}

	/**
	 * sets the default image path to given value
	 * @param defaultImagePath
	 */
	public void setDefaultImagePath(String defaultImagePath) {
		this.defaultImagePath = defaultImagePath;
	}
	/**
	 * returns the hobbies of the user
	 * @return hobbies
	 */
	public String getHobbies() {
		return hobbies;
	}
	/**
	 * sets hobbies of user to new value
	 * @param hobbies
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	/**
	 * returns joined group list of user
	 * @return groups
	 */
	public ArrayList<GroupPage> getGroups() {
		return groups;
	}

	
	
}

